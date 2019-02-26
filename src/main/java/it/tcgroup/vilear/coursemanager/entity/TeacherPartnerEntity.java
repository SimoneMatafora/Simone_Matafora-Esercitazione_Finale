package it.tcgroup.vilear.coursemanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.tcgroup.vilear.coursemanager.entity.enumerated.StatusTeacherPartnerEnum;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name ="teacher_partner")
public class TeacherPartnerEntity {

    @Embeddable
    private class TeacherPartnerId{

        @Column(name = "partner_id")
        @Type(type = "org.hibernate.type.PostgresUUIDType")
        private UUID partnerId;

        @Column(name = "teacher_id")
        @Type(type = "org.hibernate.type.PostgresUUIDType")
        private UUID teacherId;

        public TeacherPartnerId() {
        }

        public TeacherPartnerId(UUID partnerId, UUID teacherId) {
            this.partnerId = partnerId;
            this.teacherId = teacherId;
        }

        public UUID getPartnerId() {
            return partnerId;
        }

        public void setPartnerId(UUID partnerId) {
            this.partnerId = partnerId;
        }

        public UUID getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(UUID teacherId) {
            this.teacherId = teacherId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TeacherPartnerId that = (TeacherPartnerId) o;
            return Objects.equals(partnerId, that.partnerId) &&
                    Objects.equals(teacherId, that.teacherId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(partnerId, teacherId);
        }
    }

    @EmbeddedId
    private TeacherPartnerId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("partnerId")
    private PartnerEntity partnerEntityTeacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("teacherId")
    private TeacherEntity teacherEntity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusTeacherPartnerEnum status;

    public TeacherPartnerEntity() {
    }

    public TeacherPartnerEntity(PartnerEntity partnerEntity, TeacherEntity teacherEntity) {
        this.id = new TeacherPartnerId(partnerEntity.getId(), teacherEntity.getId());
        this.partnerEntityTeacher = partnerEntity;
        this.teacherEntity = teacherEntity;
    }

    public TeacherPartnerEntity(PartnerEntity partnerEntity, TeacherEntity teacherEntity, StatusTeacherPartnerEnum status) {
        this.id = new TeacherPartnerId(partnerEntity.getId(), teacherEntity.getId());
        this.partnerEntityTeacher = partnerEntity;
        this.teacherEntity = teacherEntity;
        this.status = status;
    }

    public TeacherPartnerId getId() {
        return id;
    }

    public void setId(TeacherPartnerId id) {
        this.id = id;
    }

    @JsonIgnore
    public PartnerEntity getPartnerEntity() {
        return partnerEntityTeacher;
    }

    public void setPartnerEntity(PartnerEntity partnerEntity) {
        this.partnerEntityTeacher = partnerEntity;
    }

    public TeacherEntity getTeacherEntity() {
        return teacherEntity;
    }

    public void setTeacherEntity(TeacherEntity teacherEntity) {
        this.teacherEntity = teacherEntity;
    }

    public StatusTeacherPartnerEnum getStatus() {
        return status;
    }

    public void setStatus(StatusTeacherPartnerEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TeacherPartnerEntity{" +
                "id=" + id +
                ", partnerEntity=" + partnerEntityTeacher +
                ", teacherEntity=" + teacherEntity +
                ", status=" + status +
                '}';
    }
}
