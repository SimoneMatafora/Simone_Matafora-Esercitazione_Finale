package it.tcgroup.vilear.coursemanager.entity.jsonb.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.LearnerEntity;
import it.tcgroup.vilear.coursemanager.entity.dto.LearnerDto;

import java.io.Serializable;
import java.util.Objects;
import java.util.*;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PlacementCourse implements Serializable {

    @JsonProperty("learner")
    private LearnerDto learner;

    @JsonProperty("hiring_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    private Date hiringDate;

    @JsonProperty("mission_hours")
    private Double missionHours;

    @JsonProperty("bonus_amount")
    private Double bonusAmount;

    @JsonProperty("expired_placement_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    private Date expiredPlacementDate;

    @JsonProperty("sended_eletronic_placement_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    private Date sendedEletronicPlacementDate;

    @JsonProperty("coherence")
    private Boolean coherence;

    @JsonProperty("note")
    private String note;

    public PlacementCourse() {
    }

    public PlacementCourse(LearnerDto learner, Date hiringDate, Double missionHours, Double bonusAmount, Date expiredPlacementDate, Date sendedEletronicPlacementDate, Boolean coherence, String note) {
        this.learner = learner;
        this.hiringDate = hiringDate;
        this.missionHours = missionHours;
        this.bonusAmount = bonusAmount;
        this.expiredPlacementDate = expiredPlacementDate;
        this.sendedEletronicPlacementDate = sendedEletronicPlacementDate;
        this.coherence = coherence;
        this.note = note;
    }

    public LearnerDto getLearner() {
        return learner;
    }

    public void setLearner(LearnerDto learner) {
        this.learner = learner;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    public Double getMissionHours() {
        return missionHours;
    }

    public void setMissionHours(Double missionHours) {
        this.missionHours = missionHours;
    }

    public Double getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(Double bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public Date getExpiredPlacementDate() {
        return expiredPlacementDate;
    }

    public void setExpiredPlacementDate(Date expiredPlacementDate) {
        this.expiredPlacementDate = expiredPlacementDate;
    }

    public Date getSendedEletronicPlacementDate() {
        return sendedEletronicPlacementDate;
    }

    public void setSendedEletronicPlacementDate(Date sendedEletronicPlacementDate) {
        this.sendedEletronicPlacementDate = sendedEletronicPlacementDate;
    }

    public Boolean getCoherence() {
        return coherence;
    }

    public void setCoherence(Boolean coherence) {
        this.coherence = coherence;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "PlacementCourse{" +
                "learner=" + learner +
                ", hiringDate=" + hiringDate +
                ", missionHours=" + missionHours +
                ", bonusAmount=" + bonusAmount +
                ", expiredPlacementDate=" + expiredPlacementDate +
                ", sendedEletronicPlacementDate=" + sendedEletronicPlacementDate +
                ", coherence=" + coherence +
                ", note='" + note + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlacementCourse that = (PlacementCourse) o;
        return Objects.equals(learner, that.learner) &&
                Objects.equals(hiringDate, that.hiringDate) &&
                Objects.equals(missionHours, that.missionHours) &&
                Objects.equals(bonusAmount, that.bonusAmount) &&
                Objects.equals(expiredPlacementDate, that.expiredPlacementDate) &&
                Objects.equals(sendedEletronicPlacementDate, that.sendedEletronicPlacementDate) &&
                Objects.equals(coherence, that.coherence) &&
                Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(learner, hiringDate, missionHours, bonusAmount, expiredPlacementDate, sendedEletronicPlacementDate, coherence, note);
    }
}
