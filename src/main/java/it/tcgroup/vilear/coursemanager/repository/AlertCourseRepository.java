package it.tcgroup.vilear.coursemanager.repository;

import it.tcgroup.vilear.coursemanager.entity.AlertCourseEntity;
import it.tcgroup.vilear.coursemanager.entity.AlertSettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlertCourseRepository extends JpaRepository<AlertCourseEntity, UUID> {

    @Query("SELECT a FROM AlertCourseEntity a WHERE a.active = FALSE AND a.status = FALSE")
    List<AlertCourseEntity> getAllAlertToBeActive();

    @Query("SELECT a FROM AlertCourseEntity a WHERE a.active = TRUE AND a.status = FALSE AND UPPER(a.priority) = 'BASSA'")
    List<AlertCourseEntity> getAllAlertToBeChangedPriority();
}
