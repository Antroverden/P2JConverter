package converter.models;

import com.fasterxml.jackson.annotation.*;

@lombok.Data
public class Header {
    @lombok.Getter(onMethod_ = {@JsonProperty("key")})
    @lombok.Setter(onMethod_ = {@JsonProperty("key")})
    private PurpleKey key;
    @lombok.Getter(onMethod_ = {@JsonProperty("value")})
    @lombok.Setter(onMethod_ = {@JsonProperty("value")})
    private String value;
    @lombok.Getter(onMethod_ = {@JsonProperty("type")})
    @lombok.Setter(onMethod_ = {@JsonProperty("type")})
    private TypeEnum type;
    @lombok.Getter(onMethod_ = {@JsonProperty("disabled")})
    @lombok.Setter(onMethod_ = {@JsonProperty("disabled")})
    private Boolean disabled;
}
