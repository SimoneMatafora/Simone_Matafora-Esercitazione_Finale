package it.tcgroup.vilear.coursemanager.entity.jsonb.course;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public class CandidateCourse implements Serializable {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("accepted")
    private Boolean accepted;

    public CandidateCourse() {
    }

    public CandidateCourse(UUID id, String name, String surname, Boolean accepted) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.accepted = accepted;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public String toString() {
        return "CandidateCourse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", accepted=" + accepted +
                '}';
    }
}
