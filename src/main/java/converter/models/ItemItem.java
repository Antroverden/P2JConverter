package converter.models;

import com.fasterxml.jackson.annotation.*;
import java.util.List;

@lombok.Data
public class ItemItem {
    @lombok.Getter(onMethod_ = {@JsonProperty("name")})
    @lombok.Setter(onMethod_ = {@JsonProperty("name")})
    private String name;
    @lombok.Getter(onMethod_ = {@JsonProperty("event")})
    @lombok.Setter(onMethod_ = {@JsonProperty("event")})
    private List<Event> event;
    @lombok.Getter(onMethod_ = {@JsonProperty("request")})
    @lombok.Setter(onMethod_ = {@JsonProperty("request")})
    private Request request;
    @lombok.Getter(onMethod_ = {@JsonProperty("response")})
    @lombok.Setter(onMethod_ = {@JsonProperty("response")})
    private List<Object> response;
}
