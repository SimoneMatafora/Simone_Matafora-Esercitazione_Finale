package it.tcgroup.vilear.coursemanager.repository;

import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.enumerated.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.*;

@Repository
public class CourseEMRepository {

    @PersistenceContext
    private EntityManager em;

    public List<CourseEntity> getCoursesForPagination(String courseTitle, String contentsArea, String learnerType, String supplyModality,
                                                      String paymentModality, String foundsType, String courseStartDate, String partFullTime, String courseCode, String businessName,
                                                      String courseType, String specialInitiatives){


        String sql = "SELECT * FROM course c ";

        List<String> whereCondition = new LinkedList<>();

        if( courseTitle != null){
            whereCondition.add("c.course_title = '" + courseTitle + "' ");
        }
        if( contentsArea != null){
            whereCondition.add("c.contents_area = '" + ContentsAreaCourseEnum.create(contentsArea) + "' ");
        }
        if( learnerType != null){
            whereCondition.add("c.learner_type = '" + LearnerTypeCourseEnum.create(learnerType) + "' ");
        }
        if( supplyModality != null){
            whereCondition.add("c.supply_modality = '" + SupplyModalityCourseEnum.create(supplyModality) + "' ");
        }
        if( paymentModality != null){
            whereCondition.add("c.payment_modality = '" + PaymentModalityEnum.create(paymentModality)+ "' ");
        }
        if( foundsType != null){
            whereCondition.add("c.founds_type = '" + FoundsTypeCourseEnum.create(foundsType) + "' ");
        }
        if( courseStartDate != null){
            whereCondition.add("c.course_start_date\\:\\:DATE = '"+ courseStartDate +"'\\:\\:DATE ");
        }
        if( partFullTime != null){
            whereCondition.add("c.part_full_time = '" + PartFullTimeCourseEnum.create(partFullTime) + "' " );
        }
        if( courseCode != null){
            whereCondition.add("c.course_code = '" + courseCode + "' ");
        }
        if( businessName != null){
            whereCondition.add("upper(c.business_name) = upper('" + businessName + "') ");
        }
        if( courseType != null){
            whereCondition.add("c.course_type = '" + CourseTypeEnum.create(courseType) + "' ");
        }
        if( specialInitiatives != null){
            whereCondition.add("c.special_initiatives = '" + SpecialInitiativesCourseEnum.create(specialInitiatives) + "' ");
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
        Query query = em.createNativeQuery(sql, CourseEntity.class);

        return query.getResultList();

    }
}
