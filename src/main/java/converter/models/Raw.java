package converter.models;

import com.fasterxml.jackson.annotation.*;

@lombok.Data
public class Raw {
    @lombok.Getter(onMethod_ = {@JsonProperty("language")})
    @lombok.Setter(onMethod_ = {@JsonProperty("language")})
    private String language;
}
