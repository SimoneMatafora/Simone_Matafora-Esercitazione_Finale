package it.tcgroup.vilear.coursemanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.tcgroup.vilear.coursemanager.entity.jsonb.dataType.JsonDataAddressCourseType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "hibernate_lazy_initializer", "handler"})
@Table(name = "branch")
@TypeDefs({
        @TypeDef(name = "JsonDataAddressType", typeClass = JsonDataAddressCourseType.class)
})
public class BranchEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "super")
    private Boolean superBranch;

    @Column(name = "right_of_access_to_the_courses")
    private String rightOfAccessToTheCourses;

    @Type(type = "JsonDataAddressType")
    @Column(name = "address")
    private AddressEntity address;

    public BranchEntity() {
    }

    public BranchEntity(UUID id, String username, String name, String email, Boolean superBranch, String rightOfAccessToTheCourses, AddressEntity address) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.superBranch = superBranch;
        this.rightOfAccessToTheCourses = rightOfAccessToTheCourses;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSuperBranch() {
        return superBranch;
    }

    public void setSuperBranch(Boolean superBranch) {
        this.superBranch = superBranch;
    }

    public String getRightOfAccessToTheCourses() {
        return rightOfAccessToTheCourses;
    }

    public void setRightOfAccessToTheCourses(String rightOfAccessToTheCourses) {
        this.rightOfAccessToTheCourses = rightOfAccessToTheCourses;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "BranchEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", superBranche=" + superBranch +
                ", rightOfAccessToTheCourses='" + rightOfAccessToTheCourses + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BranchEntity that = (BranchEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(superBranch, that.superBranch) &&
                Objects.equals(rightOfAccessToTheCourses, that.rightOfAccessToTheCourses) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, name, email, superBranch, rightOfAccessToTheCourses, address);
    }
}
