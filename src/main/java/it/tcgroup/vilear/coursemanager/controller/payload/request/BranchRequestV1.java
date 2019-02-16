package it.tcgroup.vilear.coursemanager.controller.payload.request;

import  it.tcgroup.vilear.coursemanager.controller.payload.request.TeacherRequestV1.*;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BranchRequestV1 {

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
    private AddressRequest address;

    public BranchRequestV1() {
    }

    public BranchRequestV1(String username, String name, String email, Boolean superBranch, String rightOfAccessToTheCourses, AddressRequest address) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.superBranch = superBranch;
        this.rightOfAccessToTheCourses = rightOfAccessToTheCourses;
        this.address = address;
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

    public AddressRequest getAddress() {
        return address;
    }

    public void setAddress(AddressRequest address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "BranchRequestV1{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", superBranch=" + superBranch +
                ", rightOfAccessToTheCourses='" + rightOfAccessToTheCourses + '\'' +
                ", address=" + address +
                '}';
    }
}
