/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import valetparking.entity.Pricing;
import valetparking.entity.Transvalet;

/**
 *
 * @author root
 */

public class CheckPinalty {
    
    public CheckPinalty(){
        
    }
    
    public void Validate(Transvalet transvalet, Pricing pricing){
        if(pricing.getLimittimeenable()){
            String strLimitTime = new SimpleDateFormat("HH:mm:ss").format(pricing.getLimitime());            
            String strCurrentDate = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());            
            try {
                Date pinaltyDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strCurrentDate + " " + strLimitTime);
                if(transvalet.getDatetimeout().after(pinaltyDateTime) && transvalet.getDatetimein().before(pinaltyDateTime)){
                    Short pinaltycount = 1;
                    transvalet.setPinalty(true);
                    transvalet.setPinaltycount(pinaltycount);
                    transvalet.setPinaltycharge(pricing.getLimittimecharge());
                }                
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }else{
            transvalet.setPinaltycharge(0.0);            
        }        
    }
    
}
