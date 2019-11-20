package it.tcgroup.vilear.coursemanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "hibernate_lazy_initializer", "handler"})
@Table(name = "alert_settings")
public class AlertSettingsEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "type")
    private String type;

    @Column(name = "alert_description")
    private String alertDescription;

    @Column(name = "periodicy")
    private Integer periodicy;

    public AlertSettingsEntity() {
    }

    public AlertSettingsEntity(String type, String alertDescription, Integer periodicy) {
        this.type = type;
        this.alertDescription = alertDescription;
        this.periodicy = periodicy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlertDescription() {
        return alertDescription;
    }

    public void setAlertDescription(String alertDescription) {
        this.alertDescription = alertDescription;
    }

    public Integer getPeriodicy() {
        return periodicy;
    }

    public void setPeriodicy(Integer periodicy) {
        this.periodicy = periodicy;
    }

    @Override
    public String toString() {
        return "AlertSettingsEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", alertDescription='" + alertDescription + '\'' +
                ", periodicy='" + periodicy + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlertSettingsEntity that = (AlertSettingsEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getAlertDescription(), that.getAlertDescription()) &&
                Objects.equals(getPeriodicy(), that.getPeriodicy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getAlertDescription(), getPeriodicy());
    }
}
