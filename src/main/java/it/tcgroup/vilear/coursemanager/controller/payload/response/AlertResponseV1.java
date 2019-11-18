package it.tcgroup.vilear.coursemanager.controller.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.enumerated.AlertTypeEnum;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class AlertResponseV1 {

    private UUID id;

    @JsonProperty("course_code")
    private String courseCode;

    @JsonProperty("course_name")
    private String courseName;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("number_of_days")
    private String numberOfDays;

    @JsonProperty("alert_description")
    private String alertDescription;

    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("priority")
    private String priority;

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("type")
    private AlertTypeEnum type;


    public AlertResponseV1() {
    }

    public AlertResponseV1(UUID id, String courseCode, String courseName, String startDate, String numberOfDays, String alertDescription, Boolean active, String priority, Boolean status, AlertTypeEnum type) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.startDate = startDate;
        this.numberOfDays = numberOfDays;
        this.alertDescription = alertDescription;
        this.active = active;
        this.priority = priority;
        this.status = status;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(String numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getAlertDescription() {
        return alertDescription;
    }

    public void setAlertDescription(String alertDescription) {
        this.alertDescription = alertDescription;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public AlertTypeEnum getType() {
        return type;
    }

    public void setType(AlertTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AlertResponseV1{" +
                "id=" + id +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", numberOfDays='" + numberOfDays + '\'' +
                ", alertDescription='" + alertDescription + '\'' +
                ", active=" + active +
                ", priority='" + priority + '\'' +
                ", status=" + status +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlertResponseV1 that = (AlertResponseV1) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCourseCode(), that.getCourseCode()) &&
                Objects.equals(getCourseName(), that.getCourseName()) &&
                Objects.equals(getStartDate(), that.getStartDate()) &&
                Objects.equals(getNumberOfDays(), that.getNumberOfDays()) &&
                Objects.equals(getAlertDescription(), that.getAlertDescription()) &&
                Objects.equals(getActive(), that.getActive()) &&
                Objects.equals(getPriority(), that.getPriority()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCourseCode(), getCourseName(), getStartDate(), getNumberOfDays(), getAlertDescription(), getActive(), getPriority(), getStatus(), getType());
    }
}
