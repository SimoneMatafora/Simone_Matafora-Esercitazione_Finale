package it.tcgroup.vilear.coursemanager.repository;

import it.tcgroup.vilear.coursemanager.common.util.DateUtil;
import it.tcgroup.vilear.coursemanager.entity.AlertCourseEntity;
import it.tcgroup.vilear.coursemanager.entity.BranchEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Repository
public class AlertCourseEMRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private DateUtil dateUtil;

    public List<AlertCourseEntity> getAlertCourseForPagination(String courseName, String courseCode, String startDate, Boolean status, Boolean active){

        String sql = "SELECT * FROM alert_course a";

        List<String> whereCondition = new LinkedList<>();

        if( courseName != null){
            whereCondition.add("upper(a.course_name) = upper('" + courseName + "') ");
        }
        if( courseCode != null){
            whereCondition.add("upper(a.course_code) = upper('" + courseCode + "') ");
        }
        if( startDate != null){

            Date dateStartDate = dateUtil.convertIS08601StringToUTCDate(startDate);
            whereCondition.add("a.date_start_alert\\:\\:DATE = '" + dateStartDate + "'\\:\\:DATE ");
        }
        if( status != null){
            whereCondition.add("a.status = " + status + " ");
        }
        if( active != null){
            whereCondition.add("a.active = " + active + " ");
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
        String orderBy = "ORDER BY a.priority ASC, a.active ASC, a.status DESC";

        sql += orderBy;
        Query query = em.createNativeQuery(sql, AlertCourseEntity.class);

        return query.getResultList();

    }
}
