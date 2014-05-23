/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.services;

import valetparking.controller.ValetController;
import valetparking.db.BackingController;
import valetparking.entity.Pricing;
import valetparking.entity.Transvalet;

/**
 *
 * @author wahhid
 */
public class TransactionProcess {
    
    private ValetController controller;
    private CheckPinalty checkPinalty;
     
    public TransactionProcess(ValetController controller){
        this.controller = controller;
        this.checkPinalty = new CheckPinalty();
        
    }
 
    private BackingController getBackingController(){
        return this.controller.getBackingController();
    }
    
            
    
    public void calculateDuration(Transvalet transvalet){
        long milis1 = transvalet.getDatetimein().getTime();
        long milis2 = transvalet.getDatetimeout().getTime();
        
        
        long diff = milis2   - milis1;
        
        long diffMinutes = diff / (60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);
        
        String strMinutes = Long.toString(diffMinutes - (diffHours * 60));
        String strHours = Long.toString(diffHours);
                
        transvalet.setMinutes(Integer.parseInt(strMinutes));
        transvalet.setHours(Integer.parseInt(strHours));
        
    }
    
    
    public void calculateAmount(Transvalet transvalet){
        Pricing pricing = this.controller.getBackingController().getPricingController().getActivePricing();
        int totalHours = 0;
        if(pricing != null){
            
            if(transvalet.getMinutes() > 0){               
                totalHours = transvalet.getHours() + 1;
            }else{
                totalHours = transvalet.getHours();
            }
            
            transvalet.setPricingid(pricing.getPricingid());            
            //Calculate Parking Charge
            if(pricing.getPricingenable()){                
                if(transvalet.getBoothout().equals("02")){
                    transvalet.setParkingcharge(totalHours * 0);
                }else{
                    transvalet.setParkingcharge(totalHours * pricing.getPricinghourly());
                }                
            }
            
            
            //Calculate Valet Charge
            if(pricing.getPricingservicesenable()){
                Short valetcount = 1;
                transvalet.setValet(true);
                transvalet.setValetcount(valetcount);
                transvalet.setValetcharge(pricing.getPricingservices());
            }
                                                
            //Calculate Voucher
            if(transvalet.getVoucher() == true){                                
                if(transvalet.getVouchertype() == 1){
                    if(pricing.getPricingenable()){                        
                        transvalet.setVouchercharge(transvalet.getParkingcharge() * transvalet.getVouchervalue()  / 100);                                                
                    }
                }
                
                if(transvalet.getVouchertype() == 2){
                    if(pricing.getPricingenable()){               
                        Double diffValue = transvalet.getParkingcharge() - transvalet.getVouchervalue();
                        if(diffValue < 0){
                            transvalet.setVouchercharge(transvalet.getParkingcharge());
                        }else{
                            transvalet.setVouchercharge(transvalet.getVouchervalue());
                        }                        
                    }
                }                                
            }                                                                        
            
            //Calculate Missing Ticket
            if(transvalet.getReciept()){
                if(pricing.getMissingticketenable()){
                    Short value=1;
                    transvalet.setReceiptcount(value);
                    transvalet.setReceiptcharge(pricing.getMissingticketcharge());                    
                }else{
                    transvalet.setReceiptcharge(0.0);
                }
            }
        }
        //Calculate Pinalty
        this.checkPinalty.Validate(transvalet, pricing);                     
        transvalet.setTotalcharge(transvalet.getParkingcharge() + transvalet.getValetcharge() + transvalet.getReceiptcharge() +transvalet.getPinaltycharge() - transvalet.getVouchercharge());
        
    }
    
}
