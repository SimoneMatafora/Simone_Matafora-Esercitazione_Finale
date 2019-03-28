package it.tcgroup.vilear.coursemanager.repository;

import it.tcgroup.vilear.coursemanager.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, UUID> {

    @Query("SELECT t FROM TeacherEntity t WHERE t.name IS NOT NULL AND t.surname IS NOT NULL AND t.dateOfBirth IS NOT NULL " +
            "AND t.birthPlace IS NOT NULL AND t.fiscalCode IS NOT NULL AND t.email IS NOT NULL AND " +
            "(t.vatHolder = FALSE OR (t.vatHolder = TRUE AND t.vatNumber IS NOT NULL)) AND " +
            "(t.professionalOrderRegistration = FALSE OR (t.professionalOrderRegistration = TRUE AND t.sector IS NOT NULL)) AND " +
            "(t.accreditedFt = FALSE OR (t.accreditedFt = TRUE AND t.accreditedFtCode IS NOT NULL))")
    List<TeacherEntity> searchCandidateTeacher();

}
