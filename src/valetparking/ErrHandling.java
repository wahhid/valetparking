/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking;

/**
 *
 * @author wahhid
 */
public class ErrHandling {

    
    protected String ClassName="";
    protected String MethodName="";
    protected boolean ErrStatus=false;
    protected String ErrMsg="";
    
    public ErrHandling(){
        
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public boolean isErrStatus() {
        return ErrStatus;
    }

    public void setErrStatus(boolean ErrStatus) {
        this.ErrStatus = ErrStatus;
    }
    

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String ClassName) {
        this.ClassName = ClassName;
    }

    public String getMethodName() {
        return MethodName;
    }

    public void setMethodName(String MethodName) {
        this.MethodName = MethodName;
    }
    
    
            
}
