package converter.models;

import com.fasterxml.jackson.annotation.*;
import java.util.List;

@lombok.Data
public class Script {
    @lombok.Getter(onMethod_ = {@JsonProperty("exec")})
    @lombok.Setter(onMethod_ = {@JsonProperty("exec")})
    private List<String> exec;
    @lombok.Getter(onMethod_ = {@JsonProperty("type")})
    @lombok.Setter(onMethod_ = {@JsonProperty("type")})
    private Type type;
}
