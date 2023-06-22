package converter.models;

import com.fasterxml.jackson.annotation.*;
import java.util.List;

@lombok.Data
public class URL {
    @lombok.Getter(onMethod_ = {@JsonProperty("raw")})
    @lombok.Setter(onMethod_ = {@JsonProperty("raw")})
    private String raw;
    @lombok.Getter(onMethod_ = {@JsonProperty("host")})
    @lombok.Setter(onMethod_ = {@JsonProperty("host")})
    private List<Host> host;
    @lombok.Getter(onMethod_ = {@JsonProperty("path")})
    @lombok.Setter(onMethod_ = {@JsonProperty("path")})
    private List<String> path;
    @lombok.Getter(onMethod_ = {@JsonProperty("variable")})
    @lombok.Setter(onMethod_ = {@JsonProperty("variable")})
    private List<Variable> variable;
    @lombok.Getter(onMethod_ = {@JsonProperty("query")})
    @lombok.Setter(onMethod_ = {@JsonProperty("query")})
    private List<Variable> query;
}
