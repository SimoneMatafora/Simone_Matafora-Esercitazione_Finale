package it.tcgroup.vilear.coursemanager.repository;

import it.tcgroup.vilear.coursemanager.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.UUID;

public interface PartnerRepository extends JpaRepository<PartnerEntity, UUID> {


    @Modifying
    @Transactional
    @Query("UPDATE PartnerEntity p SET p.id = :userId WHERE p.email = :email")
    void updatePartnerIdByEmail(@Param("email") String email, @Param("userId") UUID userId);
}
