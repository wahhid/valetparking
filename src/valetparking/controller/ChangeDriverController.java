/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.controller;


import valetparking.db.BackingController;
import valetparking.entity.Employee;
import valetparking.entity.Transvalet;
import valetparking.gui.ChangeDriverDialog;
import valetparking.gui.ValetFrame;


public class ChangeDriverController{

    private BackingController backingController;
    private ChangeDriverDialog dialog;
    private Transvalet transvalet;
    private Employee employee;
    private int drivertype;
        
    public ChangeDriverController(ValetFrame parentFrame,int drivertype){        
        this.backingController = new BackingController();
        this.drivertype = drivertype;
        this.dialog = new ChangeDriverDialog(parentFrame,this,true);
        this.dialog.show();
    }

    public BackingController getBackingController() {
        return backingController;
    }
        
    public void getTransaction(String transid){        
        transvalet = this.backingController.getTransvaletController().getByTransID(transid);        
    }

    public void getDriver(String employeeid){
        employee = this.backingController.getEmployeeController().getByNik(employeeid);
    }

    public void save(){        
        this.backingController.getTransvaletController().update(transvalet);
    }

    public Transvalet getTransvalet() {
        return transvalet;
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getDrivertype() {
        return drivertype;
    }
    
    
    
    
    
    
    
    
    
}
