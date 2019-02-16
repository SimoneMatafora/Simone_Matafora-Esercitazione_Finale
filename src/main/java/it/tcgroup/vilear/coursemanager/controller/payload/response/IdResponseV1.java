package it.tcgroup.vilear.coursemanager.controller.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class IdResponseV1 {

    @JsonProperty("id")
    private UUID id;

    public IdResponseV1() {
    }

    public IdResponseV1(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IdResponseV1{" +
                "id=" + id +
                '}';
    }
}
