package converter.models;

import java.io.IOException;
import com.fasterxml.jackson.annotation.*;

public enum PurpleKey {
    ACCEPT, CONTENT_TYPE, X_SHARER_USER_ID;

    @JsonValue
    public String toValue() {
        switch (this) {
            case ACCEPT: return "Accept";
            case CONTENT_TYPE: return "Content-Type";
            case X_SHARER_USER_ID: return "X-Sharer-User-Id";
        }
        return null;
    }

    @JsonCreator
    public static PurpleKey forValue(String value) throws IOException {
        if (value.equals("Accept")) return ACCEPT;
        if (value.equals("Content-Type")) return CONTENT_TYPE;
        if (value.equals("X-Sharer-User-Id")) return X_SHARER_USER_ID;
        throw new IOException("Cannot deserialize PurpleKey");
    }
}
