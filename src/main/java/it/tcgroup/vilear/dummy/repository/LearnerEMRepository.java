package it.tcgroup.vilear.dummy.repository;

import it.tcgroup.vilear.dummy.entity.LearnerEntity;
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

    public List<LearnerEntity> getLearnersForPagination(String username, String name, String surname, String phone, String fiscalCode, String dateOfBirth,
                                                        String birthPlace, String email, String degreeOfStudies, String courseOfStudy, String city, String region, String province){


        String sql = "SELECT d FROM LearnerEntity d INNER JOIN d.indirizzo i ";

        List<String> whereCondition = new LinkedList<>();

        if( username != null){
            whereCondition.add("d.username = '" + username + "' ");
        }
        if( name != null){
            whereCondition.add("d.name = '" + name + "' ");
        }
        if( surname != null){
            whereCondition.add("d.surname = '" + surname + "' ");
        }
        if( phone != null){
            whereCondition.add("d.phone = '" + phone + "' ");
        }
        if( fiscalCode != null){
            whereCondition.add("d.fiscalCode = '" + fiscalCode + "' ");
        }
        if( dateOfBirth != null){
            whereCondition.add("d.dateOfBirth = '" + dateOfBirth + "' ");
        }
        if( birthPlace != null){
            whereCondition.add("d.birthPlace = '" + birthPlace + "' ");
        }
        if( email != null){
            whereCondition.add("d.email = '" + email + "'");
        }
        if( degreeOfStudies != null){
            whereCondition.add("d.degreeOfStudies = '" + degreeOfStudies + "' ");
        }
        if( courseOfStudy != null){
            whereCondition.add("d.courseOfStudy= '" + courseOfStudy + "' ");
        }
        if( city != null){
            whereCondition.add("i.city = '" + city + "' ");
        }
        if( region != null){
            whereCondition.add("i.region = '" + region + "' ");
        }
        if( province != null){
            whereCondition.add("i.province = '" + province + "' ");
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
        Query query = em.createQuery(sql, LearnerEntity.class);

        return query.getResultList();

    }
}
