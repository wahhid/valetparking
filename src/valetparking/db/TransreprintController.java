/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.db;

import javax.persistence.EntityManager;
import valetparking.PersistenceConnection;
import valetparking.entity.Transreprint;

/**
 *
 * @author root
 */
public class TransreprintController extends PersistenceConnection {
    
    public TransreprintController(){
        
    }
    
    public void insert(Transreprint transreprint){
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(transreprint);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();                    
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
    }
}
