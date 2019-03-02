package it.tcgroup.vilear.coursemanager.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CourseEMRepository {

    @PersistenceContext
    private EntityManager em;
}
