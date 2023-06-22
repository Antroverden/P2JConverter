package converter.models;

import com.fasterxml.jackson.annotation.*;
import java.util.List;

@lombok.Data
public class Request {
    @lombok.Getter(onMethod_ = {@JsonProperty("method")})
    @lombok.Setter(onMethod_ = {@JsonProperty("method")})
    private Method method;
    @lombok.Getter(onMethod_ = {@JsonProperty("header")})
    @lombok.Setter(onMethod_ = {@JsonProperty("header")})
    private List<Header> header;
    @lombok.Getter(onMethod_ = {@JsonProperty("body")})
    @lombok.Setter(onMethod_ = {@JsonProperty("body")})
    private Body body;
    @lombok.Getter(onMethod_ = {@JsonProperty("url")})
    @lombok.Setter(onMethod_ = {@JsonProperty("url")})
    private URL url;
}
