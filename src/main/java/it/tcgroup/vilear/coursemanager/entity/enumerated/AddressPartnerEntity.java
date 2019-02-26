package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.tcgroup.vilear.coursemanager.entity.AddressEntity;
import it.tcgroup.vilear.coursemanager.entity.PartnerEntity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name ="address_partner")
public class AddressPartnerEntity {

    @Embeddable
    private class AddressPartnerId{

        @Column(name = "partner_id")
        @Type(type = "org.hibernate.type.PostgresUUIDType")
        private UUID partnerId;

        @Column(name = "address_id")
        @Type(type = "org.hibernate.type.PostgresUUIDType")
        private UUID addressId;

        public AddressPartnerId() {
        }

        public AddressPartnerId(UUID partnerId, UUID addressId) {
            this.partnerId = partnerId;
            this.addressId = addressId;
        }

        public UUID getPartnerId() {
            return partnerId;
        }

        public void setPartnerId(UUID partnerId) {
            this.partnerId = partnerId;
        }

        public UUID getAddressId() {
            return addressId;
        }

        public void setAddressId(UUID addressId) {
            this.addressId = addressId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AddressPartnerId that = (AddressPartnerId) o;
            return Objects.equals(partnerId, that.partnerId) &&
                    Objects.equals(addressId, that.addressId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(partnerId, addressId);
        }
    }

    @EmbeddedId
    private AddressPartnerId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("partnerId")
    private PartnerEntity partnerEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("addressId")
    private AddressEntity addressEntity;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_address")
    private TypeAddressPartnerEnum typeAddress;

    public AddressPartnerEntity() {
    }

    public AddressPartnerEntity(PartnerEntity partnerEntity, AddressEntity addressEntity) {
        this.id = new AddressPartnerId(partnerEntity.getId(), addressEntity.getId());
        this.partnerEntity = partnerEntity;
        this.addressEntity = addressEntity;
    }

    public AddressPartnerEntity(PartnerEntity partnerEntity, AddressEntity addressEntity, TypeAddressPartnerEnum typeAddress) {
        this.id = new AddressPartnerId(partnerEntity.getId(), addressEntity.getId());
        this.partnerEntity = partnerEntity;
        this.addressEntity = addressEntity;
        this.typeAddress = typeAddress;
    }

    public AddressPartnerId getId() {
        return id;
    }

    public void setId(AddressPartnerId id) {
        this.id = id;
    }

    @JsonIgnore
    public PartnerEntity getPartnerEntity() {
        return partnerEntity;
    }

    public void setPartnerEntity(PartnerEntity partnerEntity) {
        this.partnerEntity = partnerEntity;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }

    public TypeAddressPartnerEnum getTypeAddress() {
        return typeAddress;
    }

    public void setTypeAddress(TypeAddressPartnerEnum typeAddress) {
        this.typeAddress = typeAddress;
    }

    @Override
    public String toString() {
        return "AddressPartnerEntity{" +
                "id=" + id +
                ", partnerEntity=" + partnerEntity +
                ", addressEntity=" + addressEntity +
                ", typeAddress=" + typeAddress +
                '}';
    }
}
