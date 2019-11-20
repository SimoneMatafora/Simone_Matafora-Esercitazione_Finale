package it.tcgroup.vilear.coursemanager.controller.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.enumerated.AlertPriorityEnum;

import java.util.Date;
import java.util.UUID;

public class AlertCourseResponseV1 {

    private UUID id;

    @JsonProperty("id_alert")
    private UUID idAlert;

    @JsonProperty("id_course")
    private UUID idCourse;

    @JsonProperty("course_name")
    private String courseName;

    @JsonProperty("course_code")
    private String courseCode;

    @JsonProperty("alert_description")
    private String alertDescription;

    @JsonProperty("date_start_alert")
    private Date dateStartAlert;

    @JsonProperty("date_end_alert")
    private Date dateEndAlert;

    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("priority")
    private AlertPriorityEnum priority;

    public AlertCourseResponseV1() {
    }

    public AlertCourseResponseV1(UUID id, UUID idAlert, UUID idCourse, String courseName, String courseCode, String alertDescription, Date dateStartAlert, Date dateEndAlert, Boolean active, Boolean status, AlertPriorityEnum priority) {
        this.id = id;
        this.idAlert = idAlert;
        this.idCourse = idCourse;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.alertDescription = alertDescription;
        this.dateStartAlert = dateStartAlert;
        this.dateEndAlert = dateEndAlert;
        this.active = active;
        this.status = status;
        this.priority = priority;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdAlert() {
        return idAlert;
    }

    public void setIdAlert(UUID idAlert) {
        this.idAlert = idAlert;
    }

    public UUID getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(UUID idCourse) {
        this.idCourse = idCourse;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getAlertDescription() {
        return alertDescription;
    }

    public void setAlertDescription(String alertDescription) {
        this.alertDescription = alertDescription;
    }

    public Date getDateStartAlert() {
        return dateStartAlert;
    }

    public void setDateStartAlert(Date dateStartAlert) {
        this.dateStartAlert = dateStartAlert;
    }

    public Date getDateEndAlert() {
        return dateEndAlert;
    }

    public void setDateEndAlert(Date dateEndAlert) {
        this.dateEndAlert = dateEndAlert;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public AlertPriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(AlertPriorityEnum priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "AlertCourseResponseV1{" +
                "id=" + id +
                ", idAlert=" + idAlert +
                ", idCourse=" + idCourse +
                ", courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", alertDescription='" + alertDescription + '\'' +
                ", dateStartAlert=" + dateStartAlert +
                ", dateEndAlert=" + dateEndAlert +
                ", active=" + active +
                ", status=" + status +
                ", priority=" + priority +
                '}';
    }
}
