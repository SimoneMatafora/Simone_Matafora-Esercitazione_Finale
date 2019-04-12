package it.tcgroup.vilear.coursemanager.repository;

import it.tcgroup.vilear.coursemanager.entity.LearnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface LearnerRepository extends JpaRepository<LearnerEntity, UUID> {

    @Query("SELECT l FROM LearnerEntity l WHERE l.email = :email")
    LearnerEntity getLearnerByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("UPDATE LearnerEntity l SET l.id = :userId WHERE l.email = :email")
    void updateLearnerIdByEmail(@Param("email") String email, @Param("userId") UUID userId);
}
