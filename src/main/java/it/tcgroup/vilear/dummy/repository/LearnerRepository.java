package it.tcgroup.vilear.dummy.repository;

import it.tcgroup.vilear.dummy.entity.LearnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LearnerRepository extends JpaRepository<LearnerEntity, UUID> {
}
