package converter.models;

import java.io.IOException;
import com.fasterxml.jackson.annotation.*;

public enum TypeEnum {
    APPROVED, BASE_URL, BOOKING_ID, FROM, ITEM_ID, SIZE, STATE, TEXT, USER_ID;

    @JsonValue
    public String toValue() {
        switch (this) {
            case APPROVED: return "approved";
            case BASE_URL: return "baseUrl";
            case BOOKING_ID: return "bookingId";
            case FROM: return "from";
            case ITEM_ID: return "itemId";
            case SIZE: return "size";
            case STATE: return "state";
            case TEXT: return "text";
            case USER_ID: return "userId";
        }
        return null;
    }

    @JsonCreator
    public static TypeEnum forValue(String value) throws IOException {
        if (value.equals("approved")) return APPROVED;
        if (value.equals("baseUrl")) return BASE_URL;
        if (value.equals("bookingId")) return BOOKING_ID;
        if (value.equals("from")) return FROM;
        if (value.equals("itemId")) return ITEM_ID;
        if (value.equals("size")) return SIZE;
        if (value.equals("state")) return STATE;
        if (value.equals("text")) return TEXT;
        if (value.equals("userId")) return USER_ID;
        throw new IOException("Cannot deserialize TypeEnum");
    }
}
