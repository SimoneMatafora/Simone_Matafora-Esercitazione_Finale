package it.tcgroup.vilear.dummy.repository;

import it.tcgroup.vilear.dummy.entity.BranchEntity;
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

        String sql = "SELECT d FROM BranchEntity d INNER JOIN d.address i ";

        List<String> whereCondition = new LinkedList<>();

        if( username != null){
            whereCondition.add("d.username = '" + username + "' ");
        }
        if( name != null){
            whereCondition.add("d.name = '" + name + "' ");
        }
        if( rightOfAccessToTheCourses != null){
            whereCondition.add("d.rightOfAccessToTheCourses = '" + rightOfAccessToTheCourses + "' ");
        }
        if( superFiliale != null){
            whereCondition.add("d.superFiliale = " + superFiliale + " ");
        }
        if( email != null){
            whereCondition.add("d.email = '" + email + "'");
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

        System.out.println("sql prima della where "+sql);
        sql += where;
        System.out.println(sql);

        Query query = em.createQuery(sql, BranchEntity.class);

        return query.getResultList();

    }
}
