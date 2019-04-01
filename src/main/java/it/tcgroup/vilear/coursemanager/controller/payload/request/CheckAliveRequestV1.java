package it.tcgroup.vilear.coursemanager.controller.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CheckAliveRequestV1 implements Serializable {

    @JsonProperty("email")
    private String email;

    public CheckAliveRequestV1() {
    }

    public CheckAliveRequestV1(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
