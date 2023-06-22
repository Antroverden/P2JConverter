package converter.models;

import com.fasterxml.jackson.annotation.*;
import java.util.UUID;

@lombok.Data
public class Info {
    @lombok.Getter(onMethod_ = {@JsonProperty("_postman_id")})
    @lombok.Setter(onMethod_ = {@JsonProperty("_postman_id")})
    private UUID postmanID;
    @lombok.Getter(onMethod_ = {@JsonProperty("name")})
    @lombok.Setter(onMethod_ = {@JsonProperty("name")})
    private String name;
    @lombok.Getter(onMethod_ = {@JsonProperty("schema")})
    @lombok.Setter(onMethod_ = {@JsonProperty("schema")})
    private String schema;
    @lombok.Getter(onMethod_ = {@JsonProperty("_exporter_id")})
    @lombok.Setter(onMethod_ = {@JsonProperty("_exporter_id")})
    private String exporterID;
}
