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

    public List<CourseEntity> getCoursesForPagination(String courseTitle, ContentsAreaCourseEnum contentsArea, LearnerTypeCourseEnum learnerType, SupplyModalityCourseEnum supplyModality,
                                                      PaymentModalityEnum paymentModality, FoundsTypeCourseEnum foundsType, String courseStartDate, PartFullTimeCourseEnum partFullTime, String courseCode, String businessName,
                                                      CourseTypeEnum courseType, SpecialInitiativesCourseEnum specialInitiatives){


        String sql = "SELECT c FROM CourseEntity c ";

        List<String> whereCondition = new LinkedList<>();

        if( courseTitle != null){
            whereCondition.add("c.courseTitle = '" + courseTitle + "' ");
        }
        if( contentsArea != null){
            whereCondition.add("c.contentsArea = '" + contentsArea + "' ");
        }
        if( learnerType != null){
            whereCondition.add("c.learnerType = '" + learnerType + "' ");
        }
        if( supplyModality != null){
            whereCondition.add("c.supplyModality = '" + supplyModality + "' ");
        }
        if( paymentModality != null){
            whereCondition.add("c.paymentModality = '" + paymentModality + "' ");
        }
        if( foundsType != null){
            whereCondition.add("c.foundsType = '" + foundsType + "' ");
        }
        if( courseStartDate != null){
            whereCondition.add("c.courseStartDate = '" + courseStartDate + "' ");
        }
        if( partFullTime != null){
            whereCondition.add("c.partFullTime = '" + partFullTime + "'");
        }
        if( courseCode != null){
            whereCondition.add("c.courseCode = '" + courseCode + "' ");
        }
        if( businessName != null){
            whereCondition.add("c.businessName= '" + businessName + "' ");
        }
        if( courseType != null){
            whereCondition.add("c.courseType = '" + courseType + "' ");
        }
        if( specialInitiatives != null){
            whereCondition.add("c.specialInitiatives = '" + specialInitiatives + "' ");
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
