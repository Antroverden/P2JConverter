package converter.models;

import java.io.IOException;
import com.fasterxml.jackson.annotation.*;

public enum Method {
    DELETE, GET, PATCH, POST;

    @JsonValue
    public String toValue() {
        switch (this) {
            case DELETE: return "DELETE";
            case GET: return "GET";
            case PATCH: return "PATCH";
            case POST: return "POST";
        }
        return null;
    }

    @JsonCreator
    public static Method forValue(String value) throws IOException {
        if (value.equals("DELETE")) return DELETE;
        if (value.equals("GET")) return GET;
        if (value.equals("PATCH")) return PATCH;
        if (value.equals("POST")) return POST;
        throw new IOException("Cannot deserialize Method");
    }
}
