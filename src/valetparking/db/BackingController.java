/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.db;

import javax.persistence.EntityManager;
import valetparking.PersistenceConnection;

/**
 *
 * @author wahhid
 */
public class BackingController extends PersistenceConnection{
                
    private EmployeeController employeeController;
    private BoothController boothController;
    private PricingController pricingController;
    private ShiftController shiftController;
    private TransvaletController transvaletController;   
    private TransreprintController transreprintController;
    private IdseqController idseqController;
    private VoucherController voucherController;
    private VoucherdetailController voucherdetailController;
    private ConfigurationController configurationController;
    
    public BackingController(){
        this.employeeController = new EmployeeController();
        this.boothController = new BoothController();
        this.pricingController = new PricingController();
        this.shiftController = new ShiftController();
        this.transvaletController = new TransvaletController();
        this.transreprintController = new TransreprintController();
        this.idseqController = new IdseqController();
        this.voucherController = new VoucherController();
        this.voucherdetailController = new VoucherdetailController();
        this.configurationController = new ConfigurationController();
        
    }

    public BoothController getBoothController() {
        return boothController;
    }

    public EmployeeController getEmployeeController() {
        return employeeController;
    }

    public PricingController getPricingController() {
        return pricingController;
    }

    public ShiftController getShiftController() {
        return shiftController;
    }

    public TransvaletController getTransvaletController() {
        return transvaletController;
    }

    public IdseqController getIdseqController() {
        return idseqController;
    }

    public VoucherController getVoucherController() {
        return voucherController;
    }

    public void setVoucherController(VoucherController voucherController) {
        this.voucherController = voucherController;
    }

    public VoucherdetailController getVoucherdetailController() {
        return voucherdetailController;
    }

    public void setVoucherdetailController(VoucherdetailController voucherdetailController) {
        this.voucherdetailController = voucherdetailController;
    }

    public TransreprintController getTransreprintController() {
        return transreprintController;
    }

    public ConfigurationController getConfigurationController() {
        return configurationController;
    }


    
    
    @Override
    public EntityManager getEntityManager(){
        return this.emf.createEntityManager();
    }
}
