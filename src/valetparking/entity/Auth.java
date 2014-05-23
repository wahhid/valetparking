/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.entity;

/**
 *
 * @author wahhid
 */
public class Auth {
 
    private String nik;
    private String employeename;
    private String initial;
    private String booth;
    private String boothname;
    private boolean login;
    private boolean printin;
    private boolean printout;
    private String printerport;
    private String reportpath;
    private boolean driver; 
    private int sleeptime;
    
    public Auth(){
                
    }

    public String getBooth() {
        return booth;
    }

    public void setBooth(String booth) {
        this.booth = booth;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getBoothname() {
        return boothname;
    }

    public void setBoothname(String boothname) {
        this.boothname = boothname;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public boolean isPrintin() {
        return printin;
    }

    public void setPrintin(boolean printin) {
        this.printin = printin;
    }

    public boolean isPrintout() {
        return printout;
    }

    public void setPrintout(boolean printout) {
        this.printout = printout;
    }

    public int getSleeptime() {
        return sleeptime;
    }

    public void setSleeptime(int sleeptime) {
        this.sleeptime = sleeptime;
    }

    public boolean isDriver() {
        return driver;
    }

    public void setDriver(boolean driver) {
        this.driver = driver;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getPrinterport() {
        return printerport;
    }

    public void setPrinterport(String printerport) {
        this.printerport = printerport;
    }

    public String getReportpath() {
        return reportpath;
    }

    public void setReportpath(String reportpath) {
        this.reportpath = reportpath;
    }


    
    
    
    public void clear(){
        this.booth = "";
        this.boothname = "";
        this.employeename = "";
        this.initial = "";
        this.nik = "";
        this.login = false;
        this.driver = false;
        this.printin = false;
        this.printout = false;
        this.printerport = "";
        this.reportpath = "";
    }            
}
