package it.tcgroup.vilear.coursemanager.repository;


import it.tcgroup.vilear.coursemanager.adapter.AddressAdapter;
import it.tcgroup.vilear.coursemanager.entity.LearnerEntity;
import it.tcgroup.vilear.coursemanager.entity.enumerated.DegreeOfStudiesEnum;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Address;
import org.bouncycastle.crypto.util.Pack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Repository
public class LearnerEMRepository {

    @PersistenceContext
    private EntityManager em;


    public List<LearnerEntity> getFilialiForPagination(int page, int pageSize, String name, String surname,
                                                       String fiscalCode, String dateOfBirth, String birthPlace, String email, String phone,
                                                       String note, String degreeOfStudies, String courseOfStudy, String residentialNation,
                                                       String residentialRegion, String residentialProvince, String residentialCity,
                                                       String residentialStreet, String residentialNumber, String domicileNation,
                                                       String domicileRegion, String domicileProvince, String domicileCity,
                                                       String domicileStreet, String domicileNumber, Boolean domicileEqualsResidential)throws ParseException {


        String sql = "SELECT * FROM learner l";

        List<String> whereCondition = new LinkedList<>();

        if( name != null){
            whereCondition.add("upper(l.name) = upper('" + name + "') ");
        }

        if( surname != null){
            whereCondition.add("upper(l.surname) = upper('" + surname + "') ");
        }

        if( fiscalCode != null){
            whereCondition.add("upper(l.fiscal_code) = upper('" + fiscalCode + "') ");
        }
        /*-------------------------------------Date----------------------------------------------------------------------------------------------*/
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date date=df.parse(dateOfBirth);
        if( dateOfBirth != null){
            whereCondition.add("l.date_of_birth = '" + date + "' ");
        }
        /*------------------------------------------------------------------------------------------------------------------------------------------*/
        if( birthPlace != null){
            whereCondition.add("upper(l.birth_place) = upper('" + birthPlace + "') ");
        }
        if( phone != null){
            whereCondition.add("upper(l.phone) = upper('" + phone + "') ");
        }

        if( email != null){
            whereCondition.add("upper(l.email) = upper('" + email + "') ");
        }

        if( note != null){
            whereCondition.add("upper(l.note) = upper('" + note + "') ");
        }

        if( degreeOfStudies != null){
            whereCondition.add("upper(l.degree_of_studies) = upper('" + degreeOfStudies + "') ");
        }

        if( courseOfStudy != null){
            whereCondition.add("upper(l.course_of_study) = upper('" + courseOfStudy + "') ");
        }

        if( domicileEqualsResidential != null){
            whereCondition.add("l.domicile_equals_residential = '" + domicileEqualsResidential + "'");
        }
        /*---------------------------------residential address--------------------------------------------------------------*/
        if( residentialNation != null){
            whereCondition.add("upper(l.residential_address ->> 'nation') = upper('" + residentialNation + "') ");
        }

        if( residentialRegion != null){
            whereCondition.add("upper(l.residential_address ->> 'region') = upper('" + residentialRegion + "') ");
        }

        if( residentialProvince != null){
            whereCondition.add("upper(l.residential_address ->> 'province') = upper('" + residentialProvince + "') ");
        }

        if( residentialCity != null){
            whereCondition.add("upper(l.residential_address ->> 'city') = upper('" + residentialCity + "') ");
        }

        if( residentialStreet != null){
            whereCondition.add("upper(l.residential_address ->> 'street') = upper('" + residentialStreet + "') ");
        }

        if( residentialNumber != null){
            whereCondition.add("upper(l.residential_address ->> 'number') = upper('" + residentialNumber + "') ");
        }

        /*----------------------------------------domicile address-------------------------------------------------------------*/
        if( domicileNation != null){
            whereCondition.add("upper(l.domicile_address ->> 'nation') = upper('" + domicileNation + "') ");
        }
        if( domicileRegion != null){
            whereCondition.add("upper(l.domicile_address ->> 'region') = upper('" + domicileRegion + "') ");
        }
        if( domicileProvince != null){
            whereCondition.add("upper(l.domicile_address ->> 'province') = upper('" + domicileProvince + "') ");
        }
        if( domicileCity != null){
            whereCondition.add("upper(l.domicile_address ->> 'city') = upper('" + domicileCity + "') ");
        }
        if( domicileStreet != null){
            whereCondition.add("upper(l.domicile_address ->> 'street') = upper('" + domicileStreet + "') ");
        }
        if( domicileNumber != null){
            whereCondition.add("upper(l.domicile_address ->> 'number') = upper('" + domicileNumber + "') ");
        }


        /*----------------------------Generazione stringa query---------------------------------------------*/


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
