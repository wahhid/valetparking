/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.controller;


import valetparking.db.BackingController;
import valetparking.entity.Booth;
import valetparking.entity.Configuration;
import valetparking.gui.LoginFrame;
import valetparking.services.UserAuthentication;

/**
 *
 * @author wahhid
 */
public class LoginController{
     
    private BackingController backingController;
    private UserAuthentication userAuthentication;    
    private Booth booth;    
    private Configuration configuration;
    private LoginFrame loginFrame;
    
    public LoginController(){  
        this.userAuthentication = new UserAuthentication(this);
        this.backingController = new BackingController();        
        this.readConfiguration();
        this.loginFrame = new LoginFrame(this);
        this.showLoginFrame();
    }
       
    
    private void readConfiguration(){
        this.configuration = this.backingController.getConfigurationController().getByID("001");        
    }
    
    public void CheckBooth(String boothid){
        booth = this.backingController.getBoothController().getByBoothID(boothid);
    }
    
    public void Authenticate(String nik,String password){
        this.userAuthentication.Authenticate(nik, password);
    }
    
    public void showLoginFrame(){
        this.loginFrame.show();
    }
    
    public void hideLoginFrame(){
        this.loginFrame.show(false);
    }
        
    public BackingController getBackingController() {
        return backingController;
    }

    public Booth getBooth() {
        return booth;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
    
    
}