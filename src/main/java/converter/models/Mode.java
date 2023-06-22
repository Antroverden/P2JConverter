package converter.models;

import java.io.IOException;
import com.fasterxml.jackson.annotation.*;

public enum Mode {
    RAW;

    @JsonValue
    public String toValue() {
        switch (this) {
            case RAW: return "raw";
        }
        return null;
    }

    @JsonCreator
    public static Mode forValue(String value) throws IOException {
        if (value.equals("raw")) return RAW;
        throw new IOException("Cannot deserialize Mode");
    }
}
