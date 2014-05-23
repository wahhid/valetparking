/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.services;


import valetparking.controller.LoginController;
import valetparking.db.BackingController;
import valetparking.db.BoothController;
import valetparking.db.EmployeeController;
import valetparking.entity.Auth;
import valetparking.entity.Booth;
import valetparking.entity.Employee;

/**
 *
 * @author wahhid
 */
public class UserAuthentication {
 
    private LoginController controller;
    private boolean loginstatus = false;
    private Auth auth = new Auth();
    
    public UserAuthentication(LoginController controller){                
        this.controller = controller;
    }
    
    private EmployeeController getEmployeeController(){
        return this.controller.getBackingController().getEmployeeController();
    }
    
    public void Authenticate(String nik,String password){        
        Employee employee = this.getEmployeeController().getByNik(nik);            
        if(employee != null){
            if(employee.getPassword().equals(password)){
                auth.setNik(nik);                
                auth.setEmployeename(employee.getFullname());
                auth.setInitial(employee.getInitial());
                auth.setSleeptime(1000);
                loginstatus = true;
            }else{
                loginstatus = false;
            }
        }
    }

    public void Logout(){
                
    }
    
    public Auth getAuth() {
        return auth;
    }

    public boolean isLoginstatus() {
        return loginstatus;
    }
    
    
    
}
