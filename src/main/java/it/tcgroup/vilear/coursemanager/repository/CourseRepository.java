package it.tcgroup.vilear.coursemanager.repository;

import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
}
