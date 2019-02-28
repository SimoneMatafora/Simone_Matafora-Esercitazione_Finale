package it.tcgroup.vilear.coursemanager.entity.jsonb.partner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.TeacherEntity;
import it.tcgroup.vilear.coursemanager.entity.enumerated.StatusTeacherPartnerEnum;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeacherPartner implements Serializable {

    @JsonProperty("teacher")
    private TeacherEntity teacher;

    @JsonProperty("status")
    private StatusTeacherPartnerEnum status;

    public TeacherPartner() {
    }

    public TeacherPartner(TeacherEntity teacher, StatusTeacherPartnerEnum status) {
        this.teacher = teacher;
        this.status = status;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public StatusTeacherPartnerEnum getStatus() {
        return status;
    }

    public void setStatus(StatusTeacherPartnerEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TeacherPartner{" +
                "teacher=" + teacher +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherPartner that = (TeacherPartner) o;
        return Objects.equals(teacher, that.teacher) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacher, status);
    }
}
