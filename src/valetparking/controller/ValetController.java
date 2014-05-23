/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.controller;


import java.util.Date;
import valetparking.db.BackingController;
import valetparking.entity.Auth;
import valetparking.entity.Employee;
import valetparking.entity.Shift;
import valetparking.entity.Transvalet;
import valetparking.entity.Voucherdetail;
import valetparking.gui.LoginFrame;
import valetparking.gui.ValetFrame;
import valetparking.services.FindShift;
import valetparking.services.TransactionProcess;
import valetparking.services.VoucherProcess;

/**
 *
 * @author wahhid
 */
public class ValetController{
    private BackingController backingController;
    private ValetFrame valetFrame;
    private Auth auth;    
    private LoginFrame loginFrame;
    private Transvalet transvalet;
    private Employee employee;
    private Employee driver;
    private Shift shift;    
    private Date transdate;
    private boolean vouchervalid;
    
    
    private FindShift findShift;
    private TransactionProcess transactionProcess;
    private VoucherProcess voucherProcess;
    
    
    public ValetController(LoginFrame loginFrame,Auth auth){
        this.auth = auth;
        this.backingController = new BackingController();
        
        this.findShift = new FindShift(this);
        this.transactionProcess = new TransactionProcess(this);
        this.voucherProcess = new VoucherProcess(this);
        
        this.loginFrame = loginFrame;
        this.valetFrame = new ValetFrame(this);
        this.valetFrame.show();                                
        this.loginFrame.hide();
    }

    
    public void ValidateVoucher(String voucherdetailid){        
        Short valetcount = 1;
        vouchervalid = voucherProcess.Validate(voucherdetailid);        
        if(vouchervalid){
            transvalet.setVoucher(true);                     
            transvalet.setValetcount(valetcount);
            transvalet.setVoucherdetailid(voucherdetailid);
            transvalet.setVouchertype(voucherProcess.getVoucher().getVouchertype());
            transvalet.setVouchervalue(voucherProcess.getVoucher().getVouchervalue());            
        }else{
            this.valetFrame.getLblMessage().setText(voucherProcess.getMessage());
        }
    }
    
    public void CarExist(String carnumber){
        transvalet = this.backingController.getTransvaletController().isCarExist(carnumber);
    }
    
    public void DriverExist(String nik){
        driver = this.backingController.getEmployeeController().getByNik(nik);
    }
    
    public void FindShift(){
        shift = findShift.Find();
        System.out.println(shift.getShiftid());
        this.transdate = findShift.getTransdate();
    }
    
    public void add(Transvalet o){
        transvalet = o;
        this.backingController.getTransvaletController().add(transvalet);
    }
    
    public void update(){        
        this.transactionProcess.calculateDuration(transvalet);        
        this.transactionProcess.calculateAmount(transvalet);        
        this.backingController.getTransvaletController().update(transvalet);                
    }
    
    public Auth getAuth() {          
        return auth;
    }

    public LoginFrame getLoginFrame() {
        return loginFrame;
    }

    public BackingController getBackingController() {
        return backingController;
    }

    public Transvalet getTransvalet() {
        return transvalet;
    }

    public Employee getDriver() {
        return driver;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Shift getShift() {
        return shift;
    }

    public Date getTransdate() {
        return transdate;
    }

    public boolean isVouchervalid() {
        return vouchervalid;
    }
    
    
    
    
    
    
            
}
