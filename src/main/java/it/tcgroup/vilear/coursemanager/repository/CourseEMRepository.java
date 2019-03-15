package it.tcgroup.vilear.coursemanager.repository;

import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.enumerated.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CourseEMRepository {

    @PersistenceContext
    private EntityManager em;

    public List<CourseEntity> getCoursesForPagination(String courseTitle, String contentsArea, String learnerType, String supplyModality,
                                                      String paymentModality, String foundsType, String courseStartDate, String partFullTime, String courseCode, String businessName,
                                                      String courseType, String specialInitiatives){


        String sql = "SELECT c FROM CourseEntity c ";

        List<String> whereCondition = new LinkedList<>();

        if( courseTitle != null){
            whereCondition.add("c.courseTitle = '" + courseTitle + "' ");
        }
        if( contentsArea != null){
            whereCondition.add("c.contentsArea = '" + ContentsAreaCourseEnum.create(contentsArea) + "' ");
        }
        if( learnerType != null){
            whereCondition.add("c.learnerType = '" + LearnerTypeCourseEnum.create(learnerType) + "' ");
        }
        if( supplyModality != null){
            whereCondition.add("c.supplyModality = '" + SupplyModalityCourseEnum.create(supplyModality) + "' ");
        }
        if( paymentModality != null){
            whereCondition.add("c.paymentModality = '" + PaymentModalityEnum.create(paymentModality)+ "' ");
        }
        if( foundsType != null){
            whereCondition.add("c.foundsType = '" + FoundsTypeCourseEnum.create(foundsType) + "' ");
        }
        if( courseStartDate != null){
            whereCondition.add("c.courseStartDate\\:\\:DATE = ('" + courseStartDate + "')\\:\\:DATE ");
        }
        if( partFullTime != null){
            whereCondition.add("c.partFullTime = '" + PartFullTimeCourseEnum.create(partFullTime) + "'");
        }
        if( courseCode != null){
            whereCondition.add("c.courseCode = '" + courseCode + "' ");
        }
        if( businessName != null){
            whereCondition.add("upper(c.businessName) = upper('" + businessName + "') ");
        }
        if( courseType != null){
            whereCondition.add("c.courseType = '" + CourseTypeEnum.create(courseType) + "' ");
        }
        if( specialInitiatives != null){
            whereCondition.add("c.specialInitiatives = '" + SpecialInitiativesCourseEnum.create(specialInitiatives) + "' ");
        }

        int i = 1;
        String where="";
        if(whereCondition.size() > 0)
            where = " WHERE ";
        for( String attCondition : whereCondition){
            where += attCondition;
            if(i < whereCondition.size())
                where += " AND ";
            i++;
        }

        sql += where;
        Query query = em.createQuery(sql, CourseEntity.class);

        return query.getResultList();

    }
}
