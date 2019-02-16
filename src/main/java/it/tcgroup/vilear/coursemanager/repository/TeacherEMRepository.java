package it.tcgroup.vilear.coursemanager.repository;

import it.tcgroup.vilear.coursemanager.entity.TeacherEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

@Repository
public class TeacherEMRepository {

    @PersistenceContext
    private EntityManager em;

    public List<TeacherEntity> getTeachersForPagination(String username, String name, String surname, String phone, String fiscalCode, String dateOfBirth,
                                                       String birthPlace, String email, String professionalArea, Boolean publicEmployee, Boolean accreditedFt, String accreditedFtCode,
                                                       Boolean authorized, Boolean professionalOrderRegistration, String register, Boolean vatHolder, String sector, String city, String region, String province){


        String sql = "SELECT d FROM TeacherEntity d INNER JOIN d.address i ";

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
        if( professionalArea != null){
            whereCondition.add("d.professionalArea = '" + professionalArea + "' ");
        }
        if( publicEmployee != null){
            whereCondition.add("d.publicEmployee = " + publicEmployee + " ");
        }
        if( accreditedFt != null){
            whereCondition.add("d.accreditedFt = " + accreditedFt +  " ");
        }
        if( accreditedFtCode != null){
            whereCondition.add("d.accreditedFtCode = '" + accreditedFtCode + "' ");
        }
        if( authorized != null){
            whereCondition.add("d.authorized = " + authorized + " ");
        }
        if( professionalOrderRegistration != null){
            whereCondition.add("d.professionalOrderRegistration = " + professionalOrderRegistration + " ");
        }
        if( register != null){
            whereCondition.add("d.register = '" + register + "'");
        }
        if( vatHolder != null){
            whereCondition.add("d.vatHolder = " + vatHolder + " ");
        }
        if( sector != null){
            whereCondition.add("d.sector = '" + sector + "'");
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
        Query query = em.createQuery(sql, TeacherEntity.class);

        return query.getResultList();

    }

}
