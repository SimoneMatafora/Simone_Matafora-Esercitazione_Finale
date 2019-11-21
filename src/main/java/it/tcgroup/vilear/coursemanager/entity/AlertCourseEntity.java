package it.tcgroup.vilear.coursemanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.tcgroup.vilear.coursemanager.entity.enumerated.AlertPriorityEnum;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "hibernate_lazy_initializer", "handler"})
@Table(name = "alert_course")
public class AlertCourseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "id_alert")
    private UUID idAlert;

    @Column(name = "id_course")
    private UUID idCourse;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "alert_description")
    private String alertDescription;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "date_start_alert", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date dateStartAlert;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "date_end_alert", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date dateEndAlert;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "status")
    private Boolean status;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private AlertPriorityEnum priority;

    public AlertCourseEntity() {
    }

    public AlertCourseEntity(UUID idAlert, UUID idCourse, String courseName, String courseCode, String alertDescription, Date dateStartAlert, Date dateEndAlert, Boolean active, Boolean status, AlertPriorityEnum priority) {
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

    public String getAlertDescription() {
        return alertDescription;
    }

    public void setAlertDescription(String alertDescription) {
        this.alertDescription = alertDescription;
    }

    @Override
    public String toString() {
        return "AlertCourseEntity{" +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlertCourseEntity that = (AlertCourseEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getIdAlert(), that.getIdAlert()) &&
                Objects.equals(getIdCourse(), that.getIdCourse()) &&
                Objects.equals(getCourseName(), that.getCourseName()) &&
                Objects.equals(getCourseCode(), that.getCourseCode()) &&
                Objects.equals(getAlertDescription(), that.getAlertDescription()) &&
                Objects.equals(getDateStartAlert(), that.getDateStartAlert()) &&
                Objects.equals(getDateEndAlert(), that.getDateEndAlert()) &&
                Objects.equals(getActive(), that.getActive()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                getPriority() == that.getPriority();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdAlert(), getIdCourse(), getCourseName(), getCourseCode(), getAlertDescription(), getDateStartAlert(), getDateEndAlert(), getActive(), getStatus(), getPriority());
    }
}
