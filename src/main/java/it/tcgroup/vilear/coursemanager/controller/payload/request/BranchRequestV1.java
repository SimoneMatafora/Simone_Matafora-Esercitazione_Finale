package it.tcgroup.vilear.coursemanager.controller.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class BranchRequestV1 {

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("super")
    private Boolean superBranch;

    @JsonProperty("right_of_access_to_the_courses")
    private String rightOfAccessToTheCourses;

    @JsonProperty("address")
    private AddressRequestV1 address;

    public BranchRequestV1() {
    }

    public BranchRequestV1( String name, String email, Boolean superBranch, String rightOfAccessToTheCourses,AddressRequestV1 address) {
        this.name = name;
        this.email = email;
        this.superBranch = superBranch;
        this.rightOfAccessToTheCourses = rightOfAccessToTheCourses;
        this.address = address;
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

    public AddressRequestV1 getAddress() {
        return address;
    }

    public void setAddress(AddressRequestV1 address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "BranchRequestV1{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", superBranch=" + superBranch +
                ", rightOfAccessToTheCourses='" + rightOfAccessToTheCourses + '\'' +
                ", address=" + address +
                '}';
    }
}
