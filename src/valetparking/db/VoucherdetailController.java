/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.db;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import valetparking.ErrHandling;
import valetparking.entity.Voucherdetail;
import valetparking.PersistenceConnection;

/**
 *
 * @author wahhid
 */
public class VoucherdetailController extends PersistenceConnection{
    
    public VoucherdetailController(){
        
    }
    
    public void updateStatus(Voucherdetail voucherdetail){
        EntityManager em = this.getEntityManager();
        em.getTransaction().begin();
        try{
            em.merge(voucherdetail);
            em.getTransaction().commit();
        }catch(Exception ex){            
            em.getTransaction().rollback();
            this.getErrHandling().setErrStatus(true);
            this.getErrHandling().setErrMsg(ex.getMessage());
        }finally{
            em.close();
        }
    }
    
    public Voucherdetail getByID(String voucherdetailid){
        Voucherdetail voucherdetail;
        EntityManager em = this.getEntityManager();
        try{
            Query query = em.createNamedQuery("Voucherdetail.findByVouchdetailid")
                    .setParameter("vouchdetailid", voucherdetailid);
            query.setHint("eclipselink.refresh", "true");
            voucherdetail = (Voucherdetail) query.getSingleResult();
        }catch(NoResultException ex){
            voucherdetail = null;
            this.getErrHandling().setErrStatus(true);
            this.getErrHandling().setErrMsg(ex.getMessage());
        }finally{
            em.close();
        }
        return voucherdetail;        
    }
    
    
    public ErrHandling getErrHandling(){
        return this.getErrHandling();
    }

       
    
    
}
