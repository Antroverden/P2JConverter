package converter.models;

import com.fasterxml.jackson.annotation.*;
import java.util.List;

@lombok.Data
public class PostCollectionItem {
    @lombok.Getter(onMethod_ = {@JsonProperty("name")})
    @lombok.Setter(onMethod_ = {@JsonProperty("name")})
    private String name;
    @lombok.Getter(onMethod_ = {@JsonProperty("item")})
    @lombok.Setter(onMethod_ = {@JsonProperty("item")})
    private List<ItemItem> item;
}
