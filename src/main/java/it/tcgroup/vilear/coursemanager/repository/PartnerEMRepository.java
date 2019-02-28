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

    public List<PartnerEntity> getPartnerssForPagination(String businessName, String company, String managerName, String accreditedFt, String teacherName, String teacherSurname,
                                                         String teacherProfessionalArea, String teacherPublicEmployee, String citta, String comune, String cap){


        //QUI BISOGNA FARE LA QUERY NATIVA PER NAVIGARE ED INTERROGARE ANCHE I FILE JSON!!!!!!!!!!!!!!!

        String sql = "SELECT p FROM PartnerEntity d INNER JOIN d.address i ";

        List<String> whereCondition = new LinkedList<>();

        if( businessName != null){
            whereCondition.add("p.businessName = '" + businessName + "' ");
        }
        if( company != null){
            whereCondition.add("d.company = '" + company + "' ");
        }
        if( managerName != null){
            whereCondition.add("d.managerName = '" + managerName + "' ");
        }
        if( accreditedFt != null){
            whereCondition.add("d.accreditedFt = '" + accreditedFt + "' ");
        }
        if( teacherName != null){
            whereCondition.add("d.teacherName = '" + teacherName + "' ");
        }
        if( teacherSurname != null){
            whereCondition.add("d.teacherSurname = '" + teacherSurname + "' ");
        }
        if( teacherProfessionalArea != null){
            whereCondition.add("d.teacherProfessionalArea = '" + teacherProfessionalArea + "' ");
        }
        if( teacherPublicEmployee != null){
            whereCondition.add("d.teacherPublicEmployee = '" + teacherPublicEmployee + "'");
        }
        if( citta != null){
            whereCondition.add("d.citta = '" + citta + "' ");
        }
        if( comune != null){
            whereCondition.add("d.comune = " + comune + " ");
        }
        if( accreditedFt != null){
            whereCondition.add("d.accreditedFt = " + accreditedFt +  " ");
        }
        if( cap != null) {
            whereCondition.add("d.cap = '" + cap + "' ");
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
        Query query = em.createQuery(sql, PartnerEntity.class);

        return query.getResultList();

    }

}
