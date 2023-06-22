package converter.models;

import com.fasterxml.jackson.annotation.*;

@lombok.Data
public class Options {
    @lombok.Getter(onMethod_ = {@JsonProperty("raw")})
    @lombok.Setter(onMethod_ = {@JsonProperty("raw")})
    private Raw raw;
}
