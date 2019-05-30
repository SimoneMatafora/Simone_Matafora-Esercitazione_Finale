package it.tcgroup.vilear.coursemanager.entity.jsonb.course;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.tcgroup.vilear.coursemanager.entity.TeacherEntity;
import it.tcgroup.vilear.coursemanager.entity.dto.TeacherDto;
import it.tcgroup.vilear.coursemanager.entity.enumerated.*;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeacherCourse implements Serializable {

    @JsonProperty("teacher")
    private TeacherDto teacher;

    @JsonProperty("role")
    private RoleTeacherCourseEnum role;

    @JsonProperty("working_position")
    private WorkingPositionEnum workingPosition;

    @JsonProperty("gross_hourly_cost")
    private Double grossHourlyCost;

    @JsonProperty("payment_modality")
    private PaymentModalityEnum paymentModality;

    @JsonProperty("net_hourly_cost")
    private Double netHourlyCost;

    @JsonProperty("main")
    private Boolean main;

    @JsonProperty("hours_teaching_letter_assignment")
    private Double hoursTeachingLetterAssignment;

    @JsonProperty("total_hours_performed")
    private Double totalHoursPerformed;

    @JsonProperty("trade_unino_teaching_letter")
    private String tradeUninoTeachingLetter;

    @JsonProperty("learner_judgement_form")
    private String learnerJudgementForm;

    @JsonProperty("acronym_trade_unino")
    private AcronymTradeUninoEnum acronymTradeUnino;

    @JsonProperty("payment_modality_trade_union_teaching")
    private PaymentModalityTradeUnionEnum paymentModalityTradeUnion;

    public TeacherCourse() {
    }

    public TeacherCourse(TeacherDto teacher, RoleTeacherCourseEnum role, WorkingPositionEnum workingPosition, Double grossHourlyCost, PaymentModalityEnum paymentModality, Double netHourlyCost, Boolean main, Double hoursTeachingLetterAssignment, Double totalHoursPerformed, String tradeUninoTeachingLetter, String learnerJudgementForm, AcronymTradeUninoEnum acronymTradeUnino, PaymentModalityTradeUnionEnum paymentModalityTradeUnion) {
        this.teacher = teacher;
        this.role = role;
        this.workingPosition = workingPosition;
        this.grossHourlyCost = grossHourlyCost;
        this.paymentModality = paymentModality;
        this.netHourlyCost = netHourlyCost;
        this.main = main;
        this.hoursTeachingLetterAssignment = hoursTeachingLetterAssignment;
        this.totalHoursPerformed = totalHoursPerformed;
        this.tradeUninoTeachingLetter = tradeUninoTeachingLetter;
        this.learnerJudgementForm = learnerJudgementForm;
        this.acronymTradeUnino = acronymTradeUnino;
        this.paymentModalityTradeUnion = paymentModalityTradeUnion;
    }

    public TeacherDto getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDto teacher) {
        this.teacher = teacher;
    }

    public RoleTeacherCourseEnum getRole() {
        return role;
    }

    public void setRole(RoleTeacherCourseEnum role) {
        this.role = role;
    }

    public Double getGrossHourlyCost() {
        return grossHourlyCost;
    }

    public void setGrossHourlyCost(Double grossHourlyCost) {
        this.grossHourlyCost = grossHourlyCost;
    }

    public PaymentModalityEnum getPaymentModality() {
        return paymentModality;
    }

    public void setPaymentModality(PaymentModalityEnum paymentModality) {
        this.paymentModality = paymentModality;
    }

    public Double getNetHourlyCost() {
        return netHourlyCost;
    }

    public void setNetHourlyCost(Double netHourlyCost) {
        this.netHourlyCost = netHourlyCost;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public Double getHoursTeachingLetterAssignment() {
        return hoursTeachingLetterAssignment;
    }

    public void setHoursTeachingLetterAssignment(Double hoursTeachingLetterAssignment) {
        this.hoursTeachingLetterAssignment = hoursTeachingLetterAssignment;
    }

    public Double getTotalHoursPerformed() {
        return totalHoursPerformed;
    }

    public void setTotalHoursPerformed(Double totalHoursPerformed) {
        this.totalHoursPerformed = totalHoursPerformed;
    }

    public String getTradeUninoTeachingLetter() {
        return tradeUninoTeachingLetter;
    }

    public void setTradeUninoTeachingLetter(String tradeUninoTeachingLetter) {
        this.tradeUninoTeachingLetter = tradeUninoTeachingLetter;
    }

    public String getLearnerJudgementForm() {
        return learnerJudgementForm;
    }

    public void setLearnerJudgementForm(String learnerJudgementForm) {
        this.learnerJudgementForm = learnerJudgementForm;
    }

    public AcronymTradeUninoEnum getAcronymTradeUnino() {
        return acronymTradeUnino;
    }

    public void setAcronymTradeUnino(AcronymTradeUninoEnum acronymTradeUnino) {
        this.acronymTradeUnino = acronymTradeUnino;
    }

    public PaymentModalityTradeUnionEnum getPaymentModalityTradeUnion() {
        return paymentModalityTradeUnion;
    }

    public void setPaymentModalityTradeUnion(PaymentModalityTradeUnionEnum paymentModalityTradeUnion) {
        this.paymentModalityTradeUnion = paymentModalityTradeUnion;
    }

    public WorkingPositionEnum getWorkingPosition() {
        return workingPosition;
    }

    public void setWorkingPosition(WorkingPositionEnum workingPosition) {
        this.workingPosition = workingPosition;
    }

    @Override
    public String toString() {
        return "TeacherCourse{" +
                "teacher=" + teacher +
                ", role=" + role +
                ", workingPosition=" + workingPosition +
                ", grossHourlyCost=" + grossHourlyCost +
                ", paymentModality=" + paymentModality +
                ", netHourlyCost=" + netHourlyCost +
                ", main=" + main +
                ", hoursTeachingLetterAssignment=" + hoursTeachingLetterAssignment +
                ", totalHoursPerformed=" + totalHoursPerformed +
                ", tradeUninoTeachingLetter='" + tradeUninoTeachingLetter + '\'' +
                ", learnerJudgementForm='" + learnerJudgementForm + '\'' +
                ", acronymTradeUnino=" + acronymTradeUnino +
                ", paymentModalityTradeUnion=" + paymentModalityTradeUnion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherCourse that = (TeacherCourse) o;
        return Objects.equals(teacher, that.teacher) &&
                role == that.role &&
                workingPosition == that.workingPosition &&
                Objects.equals(grossHourlyCost, that.grossHourlyCost) &&
                paymentModality == that.paymentModality &&
                Objects.equals(netHourlyCost, that.netHourlyCost) &&
                Objects.equals(main, that.main) &&
                Objects.equals(hoursTeachingLetterAssignment, that.hoursTeachingLetterAssignment) &&
                Objects.equals(totalHoursPerformed, that.totalHoursPerformed) &&
                Objects.equals(tradeUninoTeachingLetter, that.tradeUninoTeachingLetter) &&
                Objects.equals(learnerJudgementForm, that.learnerJudgementForm) &&
                acronymTradeUnino == that.acronymTradeUnino &&
                paymentModalityTradeUnion == that.paymentModalityTradeUnion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacher, role, workingPosition, grossHourlyCost, paymentModality, netHourlyCost, main, hoursTeachingLetterAssignment, totalHoursPerformed, tradeUninoTeachingLetter, learnerJudgementForm, acronymTradeUnino, paymentModalityTradeUnion);
    }
}
