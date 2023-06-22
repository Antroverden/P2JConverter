package converter.models;

import com.fasterxml.jackson.annotation.*;

@lombok.Data
public class Event {
    @lombok.Getter(onMethod_ = {@JsonProperty("listen")})
    @lombok.Setter(onMethod_ = {@JsonProperty("listen")})
    private Listen listen;
    @lombok.Getter(onMethod_ = {@JsonProperty("script")})
    @lombok.Setter(onMethod_ = {@JsonProperty("script")})
    private Script script;
}
