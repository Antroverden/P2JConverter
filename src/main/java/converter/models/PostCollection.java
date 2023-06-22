package converter.models;

import com.fasterxml.jackson.annotation.*;
import java.util.List;

@lombok.Data
public class PostCollection {
    @lombok.Getter(onMethod_ = {@JsonProperty("info")})
    @lombok.Setter(onMethod_ = {@JsonProperty("info")})
    private Info info;
    @lombok.Getter(onMethod_ = {@JsonProperty("item")})
    @lombok.Setter(onMethod_ = {@JsonProperty("item")})
    private List<PostCollectionItem> item;
    @lombok.Getter(onMethod_ = {@JsonProperty("variable")})
    @lombok.Setter(onMethod_ = {@JsonProperty("variable")})
    private List<Variable> variable;
}
