/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.services;

import java.util.Date;
import valetparking.ErrHandling;
import valetparking.controller.ValetController;
import valetparking.db.VoucherController;
import valetparking.db.VoucherdetailController;
import valetparking.entity.Voucher;
import valetparking.entity.Voucherdetail;

/**
 *
 * @author wahhid
 */
public class VoucherProcess { 
    private ValetController controller;    
    
    private Voucher voucher;
    private Voucherdetail voucherdetail;    
    
    private String message;
    private ErrHandling errHanling;
    
    public VoucherProcess(ValetController controller){
        this.controller = controller;
    }
    
    private VoucherController getVoucherController(){
        return this.controller.getBackingController().getVoucherController();
    }
    private VoucherdetailController getVoucherdetailController(){
        return this.controller.getBackingController().getVoucherdetailController();
    }
    
    public boolean Validate(String voucherdetailid){
        boolean isvalid = false;        
        Date currentDate = new Date(System.currentTimeMillis());
        voucherdetail = this.getVoucherdetail(voucherdetailid);
        if(voucherdetail != null && voucherdetail.getStatus() == false){
            voucher = this.getVoucher(voucherdetail.getVoucherid());
            if(voucher != null && voucher.getStatus()==true){
                if(currentDate.after(voucher.getVoucherstartdate()) && currentDate.before(voucher.getVoucherenddate())){
                    isvalid = true;
                    voucherdetail.setStatus(true);
                    voucherdetail.setVoucherusedate(new Date(System.currentTimeMillis()));
                    this.controller.getBackingController().getVoucherdetailController().updateStatus(voucherdetail);
                }else{
                    this.message = "Voucher Expired";
                }
            }else{
                this.message = "Voucher Not Found";
            }
        }else{
            this.message = "Voucher Not Found or Expired";
        }
        return isvalid;
    }
    
    

    public boolean Validate(String voucherdetailid,Date currentDate){
        boolean isvalid = false;                
        voucherdetail = this.getVoucherdetail(voucherdetailid);
        if(voucherdetail != null && voucherdetail.getStatus() == false){
            voucher = this.getVoucher(voucherdetail.getVoucherid());
            if(voucher != null && voucher.getStatus()==true){
                if(currentDate.after(voucher.getVoucherstartdate()) && currentDate.before(voucher.getVoucherenddate())){
                    isvalid = true;
                }else{
                    this.message = "Voucher Expired";
                }
            }else{
                this.message = "Voucher Not Found";
            }
        }else{
            this.message = "Voucher Not Found or Expired";
        }
        return isvalid;
    }
    
    public void updateVoucherDetail(Voucherdetail voucherdetail){
        this.getVoucherdetailController().updateStatus(voucherdetail);
        if(this.getVoucherdetailController().getErrHandling().isErrStatus()){
            this.getVoucherdetailController().getErrHandling().setErrStatus(false);
            this.message = this.getVoucherdetailController().getErrHandling().getErrMsg();            
        }
    }
    
    private Voucherdetail getVoucherdetail(String voucherdetailid){
        Voucherdetail o;
        o = this.getVoucherdetailController().getByID(voucherdetailid);
        return o;
    }
    
    private Voucher getVoucher(int voucher){
        Voucher o;
        o = this.getVoucherController().getByID(voucher);
        return o;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public Voucherdetail getVoucherdetail() {
        return voucherdetail;
    }

    public String getMessage() {
        return message;
    }

    public ErrHandling getErrHanling() {
        return errHanling;
    }
           
    
    
}
