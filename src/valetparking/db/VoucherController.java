/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import valetparking.entity.Voucher;
import valetparking.PersistenceConnection;

/**
 *
 * @author wahhid
 */
public class VoucherController extends PersistenceConnection{
    
    public VoucherController(){
        
    }
    
    public Voucher getByID(int voucherid){
        Voucher voucher;
        EntityManager em = this.getEntityManager();
        try{
            Query query = em.createNamedQuery("Voucher.findByVoucherid")
                    .setParameter("voucherid", voucherid);
            query.setHint("eclipselink.refresh", "true");
            voucher = (Voucher) query.getSingleResult();
        }catch(NoResultException ex){
            voucher = null;            
        }finally{
            em.close();
        }
        return voucher;
    }
}
