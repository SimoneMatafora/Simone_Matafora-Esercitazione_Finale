package it.tcgroup.vilear.coursemanager.controller.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentifierResponseV1 implements Serializable {
    private static final long serialVersionUID = -8759023542062705548L;

    public IdentifierResponseV1() {
    }

    public IdentifierResponseV1(String id) {
        this.id = id;
    }

    @JsonProperty(value = "id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
