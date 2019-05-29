package it.tcgroup.vilear.coursemanager.entity.jsonb.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.LearnerEntity;
import it.tcgroup.vilear.coursemanager.entity.dto.LearnerDto;
import it.tcgroup.vilear.coursemanager.entity.enumerated.ReasonWithdrawnLearnerCourseEnum;
import it.tcgroup.vilear.coursemanager.entity.enumerated.RecipientTypeLearnerCourseEnum;
import it.tcgroup.vilear.coursemanager.entity.enumerated.SecurityExonerateLearnerCourseEnum;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Attachment;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipientManagmentCourse implements Serializable {

    @JsonProperty("learner")
    private LearnerDto learner;

    @JsonProperty("recipient_code")
    private RecipientTypeLearnerCourseEnum recipientType;

    @JsonProperty("exoneration_general_security")
    private Boolean exonerationGeneralSecurity;

    @JsonProperty("exoneration_rights_and_duties")
    private Boolean exonerationRightsAndDuties;

    @JsonProperty("necessary_hours")
    private Double necessaryHours;

    @JsonProperty("specification_security_exonerate")
    private SecurityExonerateLearnerCourseEnum specificationSsecurityExonerate;

    @JsonProperty("accepted")
    private Boolean accepted;

    @JsonProperty("rejected")
    private Boolean rejected;

    @JsonProperty("withdrawn")
    private Boolean withdrawn;

    @JsonProperty("withdrawn_with_reason")
    private ReasonWithdrawnLearnerCourseEnum withdrawnReason;

    @JsonProperty("withdrawn_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    private Date withdrawnDate;

    @JsonProperty("withdrawn_form")
    private Attachment withdrawnForm;

    @JsonProperty("num_issued_tickets")
    private Integer numIssuedTickets;

    public RecipientManagmentCourse() {
    }

    public RecipientManagmentCourse(LearnerDto learner, RecipientTypeLearnerCourseEnum recipientType, Boolean exonerationGeneralSecurity, Boolean exonerationRightsAndDuties, Double necessaryHours, SecurityExonerateLearnerCourseEnum specificationSsecurityExonerate, Boolean accepted, Boolean rejected, Boolean withdrawn, ReasonWithdrawnLearnerCourseEnum withdrawnReason, Date withdrawnDate, Attachment withdrawnForm, Integer numIssuedTickets) {
        this.learner = learner;
        this.recipientType = recipientType;
        this.exonerationGeneralSecurity = exonerationGeneralSecurity;
        this.exonerationRightsAndDuties = exonerationRightsAndDuties;
        this.necessaryHours = necessaryHours;
        this.specificationSsecurityExonerate = specificationSsecurityExonerate;
        this.accepted = accepted;
        this.rejected = rejected;
        this.withdrawn = withdrawn;
        this.withdrawnReason = withdrawnReason;
        this.withdrawnDate = withdrawnDate;
        this.withdrawnForm = withdrawnForm;
        this.numIssuedTickets = numIssuedTickets;
    }

    public LearnerDto getLearner() {
        return learner;
    }

    public void setLearner(LearnerDto learner) {
        this.learner = learner;
    }

    public RecipientTypeLearnerCourseEnum getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(RecipientTypeLearnerCourseEnum recipientType) {
        this.recipientType = recipientType;
    }

    public Boolean getExonerationGeneralSecurity() {
        return exonerationGeneralSecurity;
    }

    public void setExonerationGeneralSecurity(Boolean exonerationGeneralSecurity) {
        this.exonerationGeneralSecurity = exonerationGeneralSecurity;
    }

    public Boolean getExonerationRightsAndDuties() {
        return exonerationRightsAndDuties;
    }

    public void setExonerationRightsAndDuties(Boolean exonerationRightsAndDuties) {
        this.exonerationRightsAndDuties = exonerationRightsAndDuties;
    }

    public Double getNecessaryHours() {
        return necessaryHours;
    }

    public void setNecessaryHours(Double necessaryHours) {
        this.necessaryHours = necessaryHours;
    }

    public SecurityExonerateLearnerCourseEnum getSpecificationSsecurityExonerate() {
        return specificationSsecurityExonerate;
    }

    public void setSpecificationSsecurityExonerate(SecurityExonerateLearnerCourseEnum specificationSsecurityExonerate) {
        this.specificationSsecurityExonerate = specificationSsecurityExonerate;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Boolean getRejected() {
        return rejected;
    }

    public void setRejected(Boolean rejected) {
        this.rejected = rejected;
    }

    public Boolean getWithdrawn() {
        return withdrawn;
    }

    public void setWithdrawn(Boolean withdrawn) {
        this.withdrawn = withdrawn;
    }

    public ReasonWithdrawnLearnerCourseEnum getWithdrawnReason() {
        return withdrawnReason;
    }

    public void setWithdrawnReason(ReasonWithdrawnLearnerCourseEnum withdrawnReason) {
        this.withdrawnReason = withdrawnReason;
    }

    public Date getWithdrawnDate() {
        return withdrawnDate;
    }

    public void setWithdrawnDate(Date withdrawnDate) {
        this.withdrawnDate = withdrawnDate;
    }

    public Attachment getWithdrawnForm() {
        return withdrawnForm;
    }

    public void setWithdrawnForm(Attachment withdrawnForm) {
        this.withdrawnForm = withdrawnForm;
    }

    public Integer getNumIssuedTickets() {
        return numIssuedTickets;
    }

    public void setNumIssuedTickets(Integer numIssuedTickets) {
        this.numIssuedTickets = numIssuedTickets;
    }

    @Override
    public String toString() {
        return "RecipientManagmentCourse{" +
                "learner=" + learner +
                ", recipientType=" + recipientType +
                ", exonerationGeneralSecurity=" + exonerationGeneralSecurity +
                ", exonerationRightsAndDuties=" + exonerationRightsAndDuties +
                ", necessaryHours=" + necessaryHours +
                ", specificationSsecurityExonerate=" + specificationSsecurityExonerate +
                ", accepted=" + accepted +
                ", rejected=" + rejected +
                ", withdrawn=" + withdrawn +
                ", withdrawnReason=" + withdrawnReason +
                ", withdrawnDate=" + withdrawnDate +
                ", withdrawnForm=" + withdrawnForm +
                ", numIssuedTickets=" + numIssuedTickets +
                '}';
    }
}
