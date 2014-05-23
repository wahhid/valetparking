/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.db;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import valetparking.PersistenceConnection;
import valetparking.entity.Configuration;

/**
 *
 * @author root
 */
public class ConfigurationController extends PersistenceConnection {

    public ConfigurationController(){
        
    }
    
    public List<Configuration> getAll(){
        EntityManager em = this.getEntityManager();
        List<Configuration> o = new ArrayList();
        try{
            Query query = em.createNamedQuery("Configuration.findAll");
            query.setHint("eclipselink.refresh", "true");
            o = query.getResultList();
        }catch(Exception ex){
            o = null;            
        }finally{
            em.close();
        }
        return o;
    }
    
    public Configuration getByID(String companyid){
        EntityManager em = this.getEntityManager();
        Configuration o;
        try{
            Query query = em.createNamedQuery("Configuration.findByCompanyid").setParameter("companyid", companyid);
            query.setHint("eclipselink.refresh", "true");
            o = (Configuration) query.getSingleResult();           
        }catch(Exception ex){
            o = null;
        }finally{
            em.close();
        }
        return o;
    }
}
