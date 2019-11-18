package it.tcgroup.vilear.coursemanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.tcgroup.vilear.coursemanager.entity.enumerated.AlertTypeEnum;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Address;
import it.tcgroup.vilear.coursemanager.entity.jsonb.dataType.JsonDataAddresType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "hibernate_lazy_initializer", "handler"})
@Table(name = "alert")
public class AlertEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "course_name")
    private String courseName;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "start_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant startDate;

    @Column(name = "number_of_days")
    private String numberOfDays;

    @Column(name = "alert_description")
    private String alertDescription;

    @Column(name = "active")
    private Boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private String priority;

    @Column(name = "status")
    private Boolean status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AlertTypeEnum type;

    public AlertEntity() {
    }

    public AlertEntity(String courseCode, String courseName, Instant startDate, String numberOfDays, String alertDescription, Boolean active, String priority, Boolean status, AlertTypeEnum type) {
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

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
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
        return "AlertEntity{" +
                "id=" + id +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", startDate=" + startDate +
                ", numberOfDays='" + numberOfDays + '\'' +
                ", alertDescription='" + alertDescription + '\'' +
                ", active=" + active +
                ", priority='" + priority + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlertEntity that = (AlertEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCourseCode(), that.getCourseCode()) &&
                Objects.equals(getCourseName(), that.getCourseName()) &&
                Objects.equals(getStartDate(), that.getStartDate()) &&
                Objects.equals(getNumberOfDays(), that.getNumberOfDays()) &&
                Objects.equals(getAlertDescription(), that.getAlertDescription()) &&
                Objects.equals(getActive(), that.getActive()) &&
                Objects.equals(getPriority(), that.getPriority()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCourseCode(), getCourseName(), getStartDate(), getNumberOfDays(), getAlertDescription(), getActive(), getPriority(), getStatus(), getType());
    }
}
