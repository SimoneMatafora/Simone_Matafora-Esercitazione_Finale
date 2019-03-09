package it.tcgroup.vilear.coursemanager.repository;

import it.tcgroup.vilear.coursemanager.entity.PartnerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

@Repository
public class PartnerEMRepository {

    @PersistenceContext
    private EntityManager em;

    public List<PartnerEntity> getPartnersForPagination(String businessName, Boolean company, String managerName, String accreditedFt, String teacherName, String teacherSurname,
                                                         String teacherProfessionalArea, String teacherPublicEmployee, String citta, String comune, String cap){


        String withAddress = "WITH ADDRESS AS (SELECT id, jsonb_array_elements(address) AS jsonAddress FROM partner ), ";
        String withTeacher = " TEACHER AS (SELECT id, jsonb_array_elements(teacher_list) AS jsonTeacher FROM partner ) ";
        String sql = "SELECT * FROM partner p WHERE p.id IN ( ";

        String subQuery = "SELECT DISTINCT p.id FROM partner p INNER JOIN ADDRESS USING(id) INNER JOIN TEACHER USING(id) " ;

        List<String> whereCondition = new LinkedList<>();

        if( businessName != null){
            whereCondition.add("p.business_name = '" + businessName + "' ");
        }
        if( company != null){
            whereCondition.add("d.company = '" + company + "' ");
        }
        if( managerName != null){
            whereCondition.add("d.manager_name = '" + managerName + "' ");
        }
        if( accreditedFt != null){
            whereCondition.add("d.accredited_ft = '" + accreditedFt + "' ");
        }
        if( accreditedFt != null){
            whereCondition.add("d.accredited_ft = " + accreditedFt +  " ");
        }
        if( teacherName != null){
            whereCondition.add("((jsonTeacher->>'teacher')\\:\\:jsonb)->>'name' = '" + teacherName + "' ");
        }
        if( teacherSurname != null){
            whereCondition.add("((jsonTeacher->>'teacher')\\:\\:jsonb)->>'surname' = '" + teacherSurname + "' ");
        }
        if( teacherProfessionalArea != null){
            whereCondition.add("((jsonTeacher->>'teacher')\\:\\:jsonb)->>'professional_area' = '" + teacherProfessionalArea + "' ");
        }
        if( teacherPublicEmployee != null){
            whereCondition.add("((jsonTeacher->>'teacher')\\:\\:jsonb)->>)'public_employee' = '" + teacherPublicEmployee + "' ");
        }
        if( citta != null){
            whereCondition.add("((jsonAddress ->> 'address')\\:\\:jsonb)->>'province' = '" + citta + "' ");
        }
        if( comune != null){
            whereCondition.add("(jsonAddress ->> 'address')\\:\\:jsonb ->> 'city' = " + comune + " ");
        }
        if( cap != null) {
            whereCondition.add("(jsonAddress ->> 'address')//://:jsonb ->> 'zip_code' = '" + cap + "' ");
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

        sql = withAddress + withTeacher + sql + subQuery + where + ")";

        System.out.println(sql);

        Query query = em.createNativeQuery(sql, PartnerEntity.class);

        return query.getResultList();

    }

}
