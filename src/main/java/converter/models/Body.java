package converter.models;

import com.fasterxml.jackson.annotation.*;

@lombok.Data
public class Body {
    @lombok.Getter(onMethod_ = {@JsonProperty("mode")})
    @lombok.Setter(onMethod_ = {@JsonProperty("mode")})
    private Mode mode;
    @lombok.Getter(onMethod_ = {@JsonProperty("raw")})
    @lombok.Setter(onMethod_ = {@JsonProperty("raw")})
    private String raw;
    @lombok.Getter(onMethod_ = {@JsonProperty("options")})
    @lombok.Setter(onMethod_ = {@JsonProperty("options")})
    private Options options;
}
