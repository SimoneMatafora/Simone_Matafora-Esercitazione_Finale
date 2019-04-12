package it.tcgroup.vilear.coursemanager.repository;

import it.tcgroup.vilear.coursemanager.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, UUID> {

    @Modifying
    @Transactional
    @Query("UPDATE BranchEntity b SET b.id = :userId WHERE b.email = :email")
    void updateBranchIdByEmail(@Param("email") String email, @Param("userId") UUID userId);
}
