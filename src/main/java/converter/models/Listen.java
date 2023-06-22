package converter.models;

import java.io.IOException;
import com.fasterxml.jackson.annotation.*;

public enum Listen {
    PREREQUEST, TEST;

    @JsonValue
    public String toValue() {
        switch (this) {
            case PREREQUEST: return "prerequest";
            case TEST: return "test";
        }
        return null;
    }

    @JsonCreator
    public static Listen forValue(String value) throws IOException {
        if (value.equals("prerequest")) return PREREQUEST;
        if (value.equals("test")) return TEST;
        throw new IOException("Cannot deserialize Listen");
    }
}
