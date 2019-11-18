package it.tcgroup.vilear.coursemanager.common.controller.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class PingResponse {

    @JsonProperty("pong")
    private String pong;

    public PingResponse(Date date) {
        this.pong = date == null ? String.valueOf(new Date().getTime()) : String.valueOf(date.getTime());
    }

    public String getPong() {
        return pong;
    }

    public void setPong(String pong) {
        this.pong = pong;
    }
}
