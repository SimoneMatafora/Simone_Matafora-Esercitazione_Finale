package it.tcgroup.vilear.coursemanager.controller.payload.response;

import it.tcgroup.vilear.coursemanager.controller.payload.response.TeacherResponseV1.*;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BranchResponseV1 {

    @JsonProperty("id")
    private String id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("super")
    private Boolean superBranch;

    @JsonProperty("right_of_access_to_the_courses")
    private String rightOfAccessToTheCourses;

    @JsonProperty("address")
    private AddressResponse address;

    public BranchResponseV1() {
    }

    public BranchResponseV1(String id, String username, String name, String email, Boolean superBranch, String rightOfAccessToTheCourses, AddressResponse address) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.superBranch = superBranch;
        this.rightOfAccessToTheCourses = rightOfAccessToTheCourses;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSuperBranch() {
        return superBranch;
    }

    public void setSuperBranch(Boolean superBranch) {
        this.superBranch = superBranch;
    }

    public String getRightOfAccessToTheCourses() {
        return rightOfAccessToTheCourses;
    }

    public void setRightOfAccessToTheCourses(String rightOfAccessToTheCourses) {
        this.rightOfAccessToTheCourses = rightOfAccessToTheCourses;
    }

    public AddressResponse getAddress() {
        return address;
    }

    public void setAddress(AddressResponse address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "BranchResponseV1{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", superBranch=" + superBranch +
                ", rightOfAccessToTheCourses='" + rightOfAccessToTheCourses + '\'' +
                ", address=" + address +
                '}';
    }
}
