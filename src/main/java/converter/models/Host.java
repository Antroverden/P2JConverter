package converter.models;

import java.io.IOException;
import com.fasterxml.jackson.annotation.*;

public enum Host {
    BASE_URL;

    @JsonValue
    public String toValue() {
        switch (this) {
            case BASE_URL: return "{{baseUrl}}";
        }
        return null;
    }

    @JsonCreator
    public static Host forValue(String value) throws IOException {
        if (value.equals("{{baseUrl}}")) return BASE_URL;
        throw new IOException("Cannot deserialize Host");
    }
}
