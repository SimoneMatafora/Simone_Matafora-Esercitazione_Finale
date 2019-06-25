package it.tcgroup.vilear.coursemanager.repository;

import it.tcgroup.vilear.coursemanager.entity.LearnerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

@Repository
public class LearnerEMRepository {

    @PersistenceContext
    private EntityManager em;

    public List<LearnerEntity> getLearnersForPagination( String name, String surname, String phone, String fiscalCode, String dateOfBirth,
                                                        String birthPlace, String email, String degreeOfStudies, String courseOfStudy, String city, String region, String province){


        String sql = "SELECT * FROM learner l";

        List<String> whereCondition = new LinkedList<>();

        if( name != null){
            whereCondition.add("upper(l.name) = upper('" + name + "') ");
        }
        if( surname != null){
            whereCondition.add("upper(l.surname) = upper('" + surname + "') ");
        }
        if( phone != null){
            whereCondition.add("l.phone = '" + phone + "' ");
        }
        if( fiscalCode != null){
            whereCondition.add("upper(l.fiscal_code) = upper('" + fiscalCode + "') ");
        }
        if( dateOfBirth != null){
            whereCondition.add("l.date_of_birth\\:\\:DATE = ('" + dateOfBirth + "')\\:\\:DATE ");
        }
        if( birthPlace != null){
            whereCondition.add("upper(l.birth_place) = upper('" + birthPlace + "') ");
        }
        if( email != null){
            whereCondition.add("l.email = '" + email + "'");
        }
        if( degreeOfStudies != null){
            whereCondition.add("l.degree_of_studies = upper('" + degreeOfStudies + "') ");
        }
        if( courseOfStudy != null){
            whereCondition.add("upper(l.course_of_study)= upper('" + courseOfStudy + "') ");
        }
        if( city != null){
            whereCondition.add("upper(l.domicile_address ->> 'city') = upper('" + city + "') ");
        }
        if( region != null){
            whereCondition.add("upper(l.domicile_address ->> 'region') = upper('" + region + "') ");
        }
        if( province != null){
            whereCondition.add("upper(l.domicile_address->> 'province') = upper('" + province + "') ");
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
        Query query = em.createNativeQuery(sql, LearnerEntity.class);

        return query.getResultList();
    }




}
