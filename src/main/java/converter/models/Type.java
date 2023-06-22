package converter.models;

import java.io.IOException;
import com.fasterxml.jackson.annotation.*;

public enum Type {
    TEXT_JAVASCRIPT;

    @JsonValue
    public String toValue() {
        switch (this) {
            case TEXT_JAVASCRIPT: return "text/javascript";
        }
        return null;
    }

    @JsonCreator
    public static Type forValue(String value) throws IOException {
        if (value.equals("text/javascript")) return TEXT_JAVASCRIPT;
        throw new IOException("Cannot deserialize Type");
    }
}
