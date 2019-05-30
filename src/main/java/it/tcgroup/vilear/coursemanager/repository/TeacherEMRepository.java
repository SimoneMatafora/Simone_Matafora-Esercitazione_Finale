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

    public List<TeacherEntity> getTeachersForPagination( String name, String surname, String phone, String fiscalCode, String dateOfBirth,
                                                       String birthPlace, String email, String professionalArea, Boolean publicEmployee, Boolean accreditedFt, String accreditedFtCode,
                                                       Boolean authorized, Boolean professionalOrderRegistration, String register, Boolean vatHolder, String sector, String city, String region, String province){

        String sql = "SELECT * FROM teacher t";

        List<String> whereCondition = new LinkedList<>();

        if( name != null){
            whereCondition.add("upper(t.name) = upper('" + name + "') ");
        }
        if( surname != null){
            whereCondition.add("upper(t.surname) = upper('" + surname + "') ");
        }
        if( phone != null){
            whereCondition.add("upper(t.phone) = upper('" + phone + "') ");
        }
        if( fiscalCode != null){
            whereCondition.add("upper(t.fiscal_code) = upper('" + fiscalCode + "') ");
        }
        if( dateOfBirth != null){
            whereCondition.add("t.date_of_birth\\:\\:DATE = ('" + dateOfBirth + "')\\:\\:DATE ");
        }
        if( birthPlace != null){
            whereCondition.add("upper(t.birth_place) = upper('" + birthPlace + "') ");
        }
        if( email != null){
            whereCondition.add("t.email = '" + email + "'");
        }
        if( professionalArea != null){
            whereCondition.add("upper(t.professional_area) = upper('" + professionalArea + "') ");
        }
        if( publicEmployee != null){
            whereCondition.add("t.public_employee = " + publicEmployee + " ");
        }
        if( accreditedFt != null){
            whereCondition.add("t.accredited_ft = " + accreditedFt +  " ");
        }
        if( accreditedFtCode != null){
            whereCondition.add("t.accredited_ft_code = '" + accreditedFtCode + "' ");
        }
        if( authorized != null){
            whereCondition.add("t.authorized = " + authorized + " ");
        }
        if( professionalOrderRegistration != null){
            whereCondition.add("t.professional_order_registration = " + professionalOrderRegistration+ " ");
        }
        if( register != null){
            whereCondition.add("upper(t.register) = upper('" + register + "') ");
        }
        if( vatHolder != null){
            whereCondition.add("t.vat_holder = " + vatHolder + " ");
        }
        if( sector != null){
            whereCondition.add("upper(t.domicile_address) = upper('" + sector + "') ");
        }
        if( province != null){
            whereCondition.add("upper(t.domicile_address ->> 'province') = upper('" + province + "') ");
        }
        if( city != null){
            whereCondition.add("upper(t.domicile_address ->> 'city') = upper('" + city + "') ");
        }
        if( region != null) {
            whereCondition.add("upper(t.domicile_address ->> 'region') = upper('" + region + "') ");
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
        Query query = em.createNativeQuery(sql, TeacherEntity.class);

        return query.getResultList();

    }

}
