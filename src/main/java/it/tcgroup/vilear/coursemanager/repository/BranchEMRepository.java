package it.tcgroup.vilear.coursemanager.repository;

import it.tcgroup.vilear.coursemanager.entity.BranchEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

@Repository
public class BranchEMRepository {

    @PersistenceContext
    private EntityManager em;

    public List<BranchEntity> getFilialiForPagination(String username, String name, String email, String rightOfAccessToTheCourses, Boolean superFiliale, String city, String region, String province){

        String sql = "SELECT * FROM branch b";

        List<String> whereCondition = new LinkedList<>();

        if( username != null){
            whereCondition.add("upper(b.username) = upper('" + username + "') ");
        }
        if( name != null){
            whereCondition.add("upper(b.name) = upper('" + name + "') ");
        }
        if( rightOfAccessToTheCourses != null){
            whereCondition.add("b.rightOfAccessToTheCourses = '" + rightOfAccessToTheCourses + "' ");
        }
        if( superFiliale != null){
            whereCondition.add("b.superFiliale = " + superFiliale + " ");
        }
        if( email != null){
            whereCondition.add("b.email = '" + email + "'");
        }
        if( city != null){
            whereCondition.add("upper(b.address ->> 'city') = upper('" + city + "') ");
        }
        if( region != null){
            whereCondition.add("upper(b.address ->> 'region') = upper('" + region + "') ");
        }
        if( province != null){
            whereCondition.add("upper(b.address ->> 'province') = upper('" + province + "') ");
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

        System.out.println("sql prima della where "+sql);
        sql += where;
        System.out.println(sql);

        Query query = em.createNativeQuery(sql, BranchEntity.class);

        return query.getResultList();

    }
}
