package converter;

import converter.models.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class App {

    public static void main(String[] args) {
        String inputPath = "/Users/username/PostCollection.txt";
        String outputPath = "/Users/username/JavaTests.txt";
        parsePostmanCollection(inputPath, outputPath);
        System.out.print("Java tests successfully generated and saved in " + outputPath);
    }

    private static void parsePostmanCollection(String inputPath, String outputPath) {
        try (BufferedReader in = new BufferedReader(new FileReader(inputPath));
             PrintWriter out = new PrintWriter(outputPath)) {
            out.print(METHOD_HEADER);
            PostCollection data = Converter.fromJsonString(readCollectionToString(in));
            int testNumber = 1;
            List<PostCollectionItem> collectionsOfTests = data.getItem();
            StringBuilder javaMethods = new StringBuilder();
            for (PostCollectionItem testCollection : collectionsOfTests) {
                List<ItemItem> tests = testCollection.getItem();
                for (ItemItem test : tests) {
                    String javaMethodName = constructMethodName(test, testNumber);
                    String statusCode = parseStatusCode(test);

                    String pathVariable = "";
                    if (test.getRequest().getUrl().getVariable() != null) {
                        pathVariable = test.getRequest().getUrl().getVariable().get(0).getValue();
                    } else if (test.getRequest().getUrl().getPath().size() > 1 && !test.getRequest().getUrl().getPath()
                            .get(1).contains(":")) {
                        pathVariable = test.getRequest().getUrl().getPath().get(1);
                    }

                    String request = test.getRequest().getMethod().toString().toLowerCase();
                    String url = "/" + test.getRequest().getUrl().getPath().get(0);
                    if (request.equals("patch") || request.equals("delete") || (request.equals("get"))) {
                        url = url + "/" + pathVariable;
                    }

                    List<Header> headers = test.getRequest().getHeader();
                    StringBuilder header = parseHeaders(headers);
                    String contentType = parseHeadersForContentType(headers);

                    List<Variable> query = test.getRequest().getUrl().getQuery();
                    StringBuilder params = new StringBuilder();

                    String paramValue;
                    if (query != null) {
                        for (Variable v : query) {
                            String paramKey = v.getKey().toString().toLowerCase();
                            paramValue = v.getValue();
                            params.append(".param(\"").append(paramKey).append("\", \"").append(paramValue).append("\")");
                        }
                    }

                    String jsonObject = parseBodyToJsonObject(test);

                    String expect = parseExpectJsonPath(test);

                    javaMethods.append(javaMethodName)
                            .append("        mockMvc.perform(")
                            .append(request).append("(\"").append(url).append("\")\n")
                            .append(header)
                            .append(params)
                            .append(contentType)
                            .append(jsonObject)
                            .append(")\n")
                            .append(statusCode)
                            .append(expect)
                            .append(";\n}\n\n");
                    testNumber++;
                }
            }
            javaMethods.setCharAt(javaMethods.length() - 1, '}');
            out.print(javaMethods);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String parseExpectJsonPath(ItemItem test) {
        List<String> execs = test.getEvent().get(0).getScript().getExec();
        String key = "";
//        int value = 0; TODO Unstable
        for (String s : execs) {
            if (s.contains("jsonData[0].id")) {
                key = "[0].id";
//                value = Integer.parseInt(s.substring(s.indexOf("eql(") + 4, s.indexOf("eql(") + 5));
                break;
            }
            if (s.contains("jsonData.id")) {
                key = ".id";
//                value = Integer.parseInt(s.substring(s.indexOf("eql(") + 4, s.indexOf("eql(") + 5));
                break;
            }
        }
        if (key.equals("")) {
            return "";
        }
        return ".andExpect(jsonPath(\"$" + key + "\").exists())";
    }

    private static String readCollectionToString(BufferedReader in) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line = in.readLine();
        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = in.readLine();
        }
        return sb.toString();
    }

    private static String constructMethodName(ItemItem test, int testNumber) {
        String[] wordsInMethod = test.getName().toLowerCase().split(" ");
        if (wordsInMethod.length > 1) {
            for (int i = 1; i < wordsInMethod.length; i++) {
                if (wordsInMethod[i].length() > 0) {
                    wordsInMethod[i] = wordsInMethod[i].substring(0, 1).toUpperCase() + wordsInMethod[i].substring(1);
                }
            }
        }
        String words = String.join("", wordsInMethod);
        String methodName = words.replaceAll("\\W", "").replaceAll("\\d", "") + testNumber;
        return "    @Test\n" +
                "    @Order(" +
                testNumber + ")\n" +
                "    public void " +
                methodName + "() throws Exception {\n";
    }

    private static StringBuilder parseHeaders(List<Header> headers) {
        StringBuilder header = new StringBuilder();
        for (Header h : headers) {
            if (h.getKey() == PurpleKey.X_SHARER_USER_ID) {
                String headerKey = "X-Sharer-User-Id";
                String headerValue = h.getValue();
                if (h.getDisabled() != null) {
                    headerValue = "";
                }
                header.append(".header(\"").append(headerKey).append("\", \"").append(headerValue).append("\")\n");
            }
        }
        return header;
    }

    private static String parseHeadersForContentType(List<Header> headers) {
        StringBuilder header = new StringBuilder();
        for (Header h : headers) {
            if (h.getDisabled() == null) {
                String headerValue = "MediaType.APPLICATION_JSON";
                header.append(".contentType(").append(headerValue).append(")");
                break;
            }
        }
        return header.toString();
    }

    private static String parseBodyToJsonObject(ItemItem test) {
        Body body = test.getRequest().getBody();
        StringBuilder jsonObject = new StringBuilder();
        if (body != null) {
            String jsonObjectStr = "\"" + body.getRaw().replace(" ", "")
                    .replace("\n", "").replace("\"", "\\\"") + "\"";
            if (jsonObjectStr.contains("start")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                List<String> exec = test.getEvent().get(1).getScript().getExec();
                int day1 = 0;
                int day2 = 0;
                int hour1 = 0;
                int hour2 = 0;
                int second1 = 0;
                int second2 = 0;
                for (String s : exec) {
                    if (s.contains("start") && s.contains("add(")) {
                        day1 = getDay(s);
                        hour1 = getHours(s);
                        second1 = getSeconds(s);
                    } else if (s.contains("end") && s.contains("add(")) {
                        day2 = getDay(s);
                        hour2 = getHours(s);
                        second2 = getSeconds(s);
                    }
                }
                String withDates = jsonObjectStr.substring(jsonObjectStr.indexOf("start"));
                withDates = withDates.replaceAll("\\d", "").replace("{{start}}",
                        LocalDateTime.now().plusDays(day1).plusHours(hour1).plusSeconds(second1).format(formatter));
                withDates = withDates.replace("{{end}}", LocalDateTime.now().plusDays(day2).plusHours(hour2)
                        .plusSeconds(second2).format(formatter));
                jsonObjectStr = jsonObjectStr.substring(0, jsonObjectStr.indexOf("start")) + withDates;
            }
            jsonObject.append(".content(").append(jsonObjectStr).append(")");
        }
        return jsonObject.toString();
    }

    private static String parseStatusCode(ItemItem test) {
        String statusFromJson = test.getEvent().get(0).getScript().getExec().get(0)
                + test.getEvent().get(0).getScript().getExec().get(1);
        String statusCode;
        if (statusFromJson.contains("201")) {
            statusCode = "isCreated()";
        } else if (statusFromJson.contains("200")) {
            statusCode = "isOk()";
        } else if (statusFromJson.contains("409")) {
            statusCode = "isConflict()";
        } else if (statusFromJson.contains("404")) {
            statusCode = "isNotFound()";
        } else if (statusFromJson.contains("400")) {
            statusCode = "isBadRequest()";
        } else if (statusFromJson.contains("500")) {
            statusCode = "isInternalServerError()";
        } else statusCode = null;
        return "                .andExpect(status()." + statusCode + ")";
    }

    private static int getDay(String s) {
        int day = 0;
        String[] times = s.split("add\\(");
        for (String time : times) {
            if (time.contains("'d'")) {
                if (time.charAt(0) == '-') {
                    day = Integer.parseInt(time.substring(0, 2));
                } else {
                    day = Integer.parseInt(time.substring(0, 1));
                }
            }
        }
        return day;
    }

    private static int getHours(String s) {
        int hour = 0;
        String[] times = s.split("add\\(");
        for (String time : times) {
            if (time.contains("'h'")) {
                if (time.charAt(0) == '-') {
                    hour = Integer.parseInt(time.substring(0, 2));
                } else {
                    hour = Integer.parseInt(time.substring(0, 1));
                }
            }
        }
        return hour;
    }

    private static int getSeconds(String s) {
        int second = 0;
        String[] times = s.split("add\\(");
        for (String time : times) {
            if (time.contains("'s'")) {
                if (time.charAt(0) == '-') {
                    second = Integer.parseInt(time.substring(0, 2));
                } else {
                    second = Integer.parseInt(time.substring(0, 1));
                }
            }
        }
        return second;
    }

    private static final String METHOD_HEADER = "import org.junit.jupiter.api.MethodOrderer;\n" +
            "import org.junit.jupiter.api.Order;\n" +
            "import org.junit.jupiter.api.Test;\n" +
            "import org.junit.jupiter.api.TestMethodOrder;\n" +
            "import org.springframework.beans.factory.annotation.Autowired;\n" +
            "import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;\n" +
            "import org.springframework.boot.test.context.SpringBootTest;\n" +
            "import org.springframework.http.MediaType;\n" +
            "import org.springframework.test.web.servlet.MockMvc;\n" +
            "\n" +
            "import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;\n" +
            "import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;\n" +
            "import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;\n" +
            "\n" +
            "@SpringBootTest\n" +
            "@AutoConfigureMockMvc\n" +
            "@TestMethodOrder(MethodOrderer.OrderAnnotation.class)\n" +
            "public class IntegrationTests {\n" +
            "\n" +
            "    @Autowired\n" +
            "    private MockMvc mockMvc;\n\n";
}
