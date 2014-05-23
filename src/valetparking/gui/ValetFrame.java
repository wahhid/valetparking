/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ValetFrame.java
 *
 * Created on Jun 30, 2011, 3:08:21 PM
 */
package valetparking.gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import valetparking.controller.ChangeDriverController;
import valetparking.controller.ValetController;
import valetparking.entity.Employee;
import valetparking.entity.Shift;
import valetparking.entity.Transreprint;
import valetparking.entity.Transvalet;
import valetparking.entity.Voucherdetail;
import valetparking.listener.clock.ClockListener;
import valetparking.listener.clock.NextidActionListener;
import valetparking.services.FindShift;
import valetparking.services.TransactionProcess;
import valetparking.services.VoucherProcess;
import valetparking.util.GenerateTransactionID;

/**
 *
 * @author wahhid
 */
public class ValetFrame extends javax.swing.JFrame {
    
    private ValetController controller;
    private Timer t;
    private Timer tnextid;
    private boolean carInArea = false;
    private Transvalet transvalet = new Transvalet();
    private GenerateTransactionID generateTransID = new GenerateTransactionID();  
    private Transvalet lastTransvalet;    
    private boolean useVoucher = false;
    private boolean missingTicket = false;
    
    
    /** Creates new form ValetFrame */
    public ValetFrame(ValetController controller) {
        
        this.controller = controller;
                
        this.setUndecorated(true);
        initComponents();
                
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = (int) tk.getScreenSize().getWidth();
        int ySize = (int) tk.getScreenSize().getHeight();
        this.setSize(1024,768);
        this.lblBackground.setSize(1024,768);        

        this.txtVoucher.setVisible(false);
        this.lblVoucher.setVisible(false);                    
        this.txtplatnumber.addKeyListener(new TextPlatNumberKeyListener());                
        
        this.txtdriver.addKeyListener(new TextDriverKeyListener());
        this.txtVoucher.addKeyListener(new TextVoucherKeyListener());
        this.txtplatnumber.requestFocus();
        this.txtdriver.setEnabled(false);
        this.lblMissing.setText("Missing Ticket : No");
        
        this.lbloperator.setText(this.controller.getAuth().getNik() + " (" + this.controller.getAuth().getEmployeename() + ")");
        this.lblbooth.setText(this.controller.getAuth().getBooth() + " (" + this.controller.getAuth().getBoothname() + ")");
        this.startClock();
        this.NextidActionListener();
    }

    private void closeFrame(){
        tnextid.stop();
        t.stop();
        this.dispose();
    }
    
    private void NextidActionListener(){
        this.tnextid = new Timer(1000,new NextidActionListener(this.lblnextid,this.controller.getAuth().getBooth()));
        tnextid.start();
    }
    
    private void startClock(){
        t = new Timer(1000, new ClockListener(this));
        t.start();        
    }
    
    private void startTransactionStatus(){
        
    }
    
    public JLabel getLblClock() {
        return lblClock;
    }
    
    private class TextPlatNumberKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {            
            int iKey = e.getKeyCode();                             
            if(iKey == KeyEvent.VK_ENTER){
                if(txtplatnumber.getText().length()>0){               
                    //completePlatNumber();
                    txtplatnumber.setText(txtplatnumber.getText().trim());
                    
                    if(txtplatnumber.getText().substring(0,1).matches("[0-9]")){                       
                        txtplatnumber.setText("b" + txtplatnumber.getText());
                        lblshow.setText(txtplatnumber.getText().toUpperCase());
                    }                      
                    
                    controller.CarExist(txtplatnumber.getText().toUpperCase().trim());                    
                    if(controller.getTransvalet() != null){
                        carInArea = true;
                        lblMessage.setText("Car already valet with Transaction ID  : " + controller.getTransvalet().getTransid());                               
                        lblplatnumber.setText(controller.getTransvalet().getCarnumber());
                        lbldatetimein.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(controller.getTransvalet().getDatetimein()));
                        lblboothin.setText(controller.getTransvalet().getBoothin());                        
                        lbldriverin.setText(controller.getTransvalet().getDriverin());
                        lbldatetimeout.setText("-");
                        lbldriverout.setText("-");
                        lblboothout.setText("-");
                        lblduration.setText("-");
                        lblservicecharge.setText("-");
                        lblparkingcharge.setText("-");                        
                        txtdriver.setEnabled(true);
                        txtdriver.requestFocus();                        
                    }else{                        
                        clearAll();
                        txtdriver.setEnabled(true);                        
                        txtdriver.requestFocus();                        
                    }                    
                }else{
                    lblMessage.setText("Enter Car Number or F10 to Exit !");
                }
            }               
                
            
            if(iKey == KeyEvent.VK_ESCAPE){                
                if(txtplatnumber.getText().length()>0){
                    txtplatnumber.setText("");
                    lblMessage.setText("Enter Car Number or Esc to Exit !");                    
                }else{                    
                    controller.getAuth().clear();
                    controller.getLoginFrame().show();                    
                    closeFrame();                    
                }
            }   
                           
            //Change Driver
            if(iKey == KeyEvent.VK_F3){
                if(txtplatnumber.getText().length()>0){
                    lblMessage.setText("Plat Number wasn't empty");
                }else{
                    processChangeDriver(0);
                }
            }
            
            //Change Driver
            if(iKey == KeyEvent.VK_F4){
                if(txtplatnumber.getText().length()>0){
                    lblMessage.setText("Plat Number wasn't empty");
                }else{
                    processChangeDriver(1);
                }
            }
            
            //Reprint Receipt
            if(iKey == KeyEvent.VK_F5){
                carInArea = true;
                PrintReceipt(lastTransvalet);
                carInArea = false;                
            }
            

        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(txtplatnumber.getText().length() <= 20){
                lblshow.setText(txtplatnumber.getText().toUpperCase());
            }
        }
        
    }
    
    
    private class TextDriverKeyListener implements KeyListener{
                
        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {           
           int iKey = e.getKeyCode();            
           if(iKey == KeyEvent.VK_F6 && carInArea == true){
                if(useVoucher == false){
                    useVoucher = true;
                    lblVoucher.setVisible(true);
                    txtVoucher.setVisible(true);
                }else{
                    useVoucher = false;
                    lblVoucher.setVisible(false);
                    txtVoucher.setVisible(false);                    
                }                
            }
 
           
            if(iKey == KeyEvent.VK_ENTER){
                if(txtdriver.getText().length() > 0){                    
                    controller.DriverExist(txtdriver.getText());
                    if(controller.getDriver() != null){
                        if(controller.getDriver().getDriver() == true){
                            if(useVoucher == true){
                                txtVoucher.requestFocus(true);                    
                            }else{
                                if(carInArea == false){
                                    addTransvalet();
                                }else{
                                    updateTransvalet();
                                }                                                            
                            }                            
                        }else{
                            lblMessage.setText(txtdriver.getText().toUpperCase() + " is not driver!");    
                        }
                    }else{
                        lblMessage.setText(txtdriver.getText().toUpperCase() + " is not driver!");    
                        txtdriver.setText("");                                                        
                    }                                                                                   
                }else{
                    lblMessage.setText("Driver was empty!");
                }                
            }
            
            if(iKey == KeyEvent.VK_ESCAPE){
                if(carInArea == true){
                    carInArea = false;
                    txtVoucher.setText("");
                    txtVoucher.setVisible(false);
                    lblVoucher.setVisible(false);
                   
                }else{
                    txtplatnumber.setEnabled(true);
                    txtplatnumber.requestFocus();                    
                    txtdriver.setText("");
                    txtdriver.setEnabled(false);                                        
                }
            }
            
            //Missing Ticket
            if(iKey == KeyEvent.VK_F7){
                if(carInArea){
                    if(missingTicket == false){
                        missingTicket = true;
                        lblMissing.setText("Missing Ticket : Yes");     
                        controller.getTransvalet().setReciept(true);                        
                    }else{
                        missingTicket = false;
                        lblMissing.setText("Missing Ticket : No");
                        controller.getTransvalet().setReciept(true);  
                    }                                        
                }
            }            
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
        
    }    
    
    private class TextVoucherKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int iKey = e.getKeyCode();
            
            if(iKey == KeyEvent.VK_ESCAPE){
                txtdriver.requestFocus(true);
                txtVoucher.setText("");
            }
               
            if(iKey == KeyEvent.VK_ENTER){
                if(txtVoucher.getText().length() > 0){
                    controller.ValidateVoucher(txtVoucher.getText());                    
                    if(controller.isVouchervalid()){                                                                        
                        System.out.println("Voucher Valid");
                        updateTransvalet();                       
                    }else{
                        txtVoucher.setText("");                       
                    }
                }else{
                    lblMessage.setText("Voucher was empty!");
                }                
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }        
    }
    
    
    private void processChangeDriver(int drivertype){
        new ChangeDriverController(this,drivertype);    
    }
    
    private void addTransvalet(){
        this.controller.FindShift();
        if(this.controller.getShift() != null){
            Transvalet tv = new Transvalet();
            tv.setTransid(this.generateTransID.generateSeq(this.controller.getAuth().getBooth(),7));
            tv.setCarnumber(this.txtplatnumber.getText().toUpperCase().trim());
            tv.setDatetimein(new Date(System.currentTimeMillis()));              
            tv.setOprin(controller.getAuth().getNik());
            tv.setDriverin(this.txtdriver.getText());
            tv.setBoothin(controller.getAuth().getBooth());        
            tv.setCreateddate(new Date(System.currentTimeMillis()));
            tv.setCreatedby(controller.getAuth().getNik());                   
            tv.setShiftin(controller.getShift().getShiftid());
            tv.setDatein(this.controller.getTransdate());
            this.controller.add(tv);
            if(tv != null){            
                lblMessage.setText("Transaction " + tv.getTransid() + " Save Successfully");
                if(this.controller.getAuth().isPrintin()){
                    PrintReceipt(tv);                
                }            
                txtplatnumber.setText("");
                txtdriver.setText("");
                txtVoucher.setText("");
                txtdriver.setEnabled(false);
                txtplatnumber.setEnabled(true);
                txtplatnumber.requestFocus();            
             
                //Thread waitThread = new Thread(new waitForCleaning(this.controller.getAuth().getSleeptime()));            
                //waitThread.start();
            }            
        }else{
            lblMessage.setText("Shift Not Found");
        }
    }       
    
    
    private void updateTransvalet(){        
        this.controller.FindShift();
        if(this.controller.getShift() != null){
            this.controller.getTransvalet().setBoothout(controller.getAuth().getBooth());
            this.controller.getTransvalet().setDatetimeout(new Date(System.currentTimeMillis()));
            this.controller.getTransvalet().setDateout(this.controller.getTransdate());
            this.controller.getTransvalet().setOprout(controller.getAuth().getNik());
            this.controller.getTransvalet().setDriverout(txtdriver.getText());            
            this.controller.getTransvalet().setShiftout(this.controller.getShift().getShiftid());                       
            this.controller.getTransvalet().setStatus(1);
            this.controller.getTransvalet().setUpdateddate(new Date(System.currentTimeMillis()));
            this.controller.getTransvalet().setUpdatedby(controller.getAuth().getNik());
            
            if(this.controller.getTransvalet() != null){
                this.controller.update();
                lastTransvalet = this.controller.getTransvalet();                                
                //Print Receipt
                if(this.controller.getAuth().isPrintout()){
                    this.PrintReceipt(this.controller.getTransvalet());
                }            
                
                //Reset View
                txtplatnumber.setText("");
                txtdriver.setText("");
                txtVoucher.setText("");
                lblVoucher.setVisible(false);
                txtVoucher.setVisible(false);
                useVoucher = false;
                txtdriver.setEnabled(false);
                txtplatnumber.setEnabled(true);
                txtplatnumber.requestFocus();  
                
                lbldatetimeout.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(this.controller.getTransvalet().getDatetimeout()));
                lbldriverout.setText(this.controller.getTransvalet().getDriverout());
                lblboothout.setText(this.controller.getAuth().getBooth());
                lblduration.setText(this.controller.getTransvalet().getHours() + " Hours " + this.controller.getTransvalet().getMinutes() + " Minutes");
                lblservicecharge.setText(Double.toString(this.controller.getTransvalet().getValetcharge()));
                lblparkingcharge.setText(Double.toString(this.controller.getTransvalet().getParkingcharge()));
                lblticketcharge.setText(Double.toString(this.controller.getTransvalet().getReceiptcharge()));
                lbltotal.setText(Double.toString(this.controller.getTransvalet().getTotalcharge()));                                
                carInArea = false;
                missingTicket = false;
                lblMissing.setText("Missing Ticket : No");                   
                //Thread waitThread = new Thread(new waitForCleaning(this.controller.getAuth().getSleeptime()));            
                //waitThread.start();
            }                        
        }else{
            lblMessage.setText("Shift Not Found");
        }        
    }   
    
    private void PrintExitRecieptWithDriver(Transvalet transvalet){
        try{
           
            FileWriter out = new FileWriter(this.controller.getAuth().getPrinterport());
            out.write("");
            
            out.write(0x1B);
            out.write(0x4B);
            out.write(0x05);
            
            //Center Position
            out.write(0x1B);
            out.write(0x61);
            out.write(0x01);
            
            //Start Double Height Font
            out.write(0x1B);
            out.write(0x21);
            out.write(0x11);            
            out.write("TAMAN ANGGREK MALL");                    
            //Stop Double Height Font
            out.write(0x1B);
            out.write(0x21);
            out.write(0x00);
            
            //Print adn 3 Line Feed
            out.write(0x1B);
            out.write(0x64);
            out.write(0x01);
            out.write(transvalet.getCarnumber().toUpperCase());                    
            out.write(0x0A);            
            out.write(transvalet.getTransid().toUpperCase());
            out.write(0x0A);
            
            //Left Align
            out.write(0x1B);
            out.write(0x61);
            out.write(0x00);            
            
            //Font B
            out.write(0x1B);
            out.write(0x21);
            out.write(0x01);
            
            out.write("Masuk        : " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(transvalet.getDatetimein()));
            out.write(0x0A);
            Employee oprin = this.controller.getBackingController().getEmployeeController().getByNik(transvalet.getOprin());
            String oprininitial="";
            if(oprin != null){
               oprininitial = oprin.getInitial();
            }
            out.write("              (" + oprininitial + ") " + transvalet.getOprin());            
            out.write(0x0A);
            Employee driverin = this.controller.getBackingController().getEmployeeController().getByNik(transvalet.getDriverin());
            String driverininitial="";
            if(driverin != null){
               driverininitial = driverin.getInitial();
            }            
            out.write("              (" + driverininitial + ") " + transvalet.getDriverin());            
            out.write(0x0A);                         
            out.write("Keluar       : " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(transvalet.getDatetimeout()));
            out.write(0x0A);
            Employee oprout = this.controller.getBackingController().getEmployeeController().getByNik(transvalet.getOprout());
            String oproutinitial="";
            if(oprout != null){
               oproutinitial = oprout.getInitial();
            }            
            out.write("              (" + oproutinitial + ") " + transvalet.getOprout());            
            out.write(0x0A);
            
            Employee driverout = this.controller.getBackingController().getEmployeeController().getByNik(transvalet.getDriverout());
            String driveroutinitial="";
            if(driverout != null){
               driveroutinitial = driverout.getInitial();
            }                        
            out.write("              (" + driveroutinitial + ") " + transvalet.getDriverout());            
            out.write(0x0A);
            out.write("Lama         : " + transvalet.getHours() + " Hours " + transvalet.getMinutes() + " Minutes");            
            out.write(0x0A);            
            
            if(transvalet.getValet()){
                out.write("Valet        : " + transvalet.getValetcharge());            
                out.write(0x0A);
            }
            
            out.write("Parkir       : " + transvalet.getParkingcharge());            
            out.write(0x0A);
            
            if(transvalet.getVoucher()){
                out.write("Voucher       : (" + transvalet.getVouchercharge() + ") - (" + transvalet.getVoucherdetailid() + ")");            
                out.write(0x0A);
            }
                        
            if(transvalet.getPinalty()){
                out.write("Pinalty        : " + transvalet.getPinaltycharge());            
                out.write(0x0A);            
            }
            
            if(transvalet.getReciept()){
                out.write("Ticket       : " + transvalet.getReceiptcharge());            
                out.write(0x0A);                   
            }
            
            out.write("Total        : " + transvalet.getTotalcharge());                        
            out.write(0x0A);            
            out.write(0x0A);
            out.write(0x0A);
            out.write(0x0A);
            out.write(0x0A);
            out.write(0x0A);
            out.write(0x0A);
            out.write(0x0A);
            out.write(0x0A);
            out.write(0x0A);
            out.write(0x0A);
            out.write(0x0A);            
            out.write(0x1D);
            out.write(0x56);
            out.write(0x00);
            out.close();
            System.out.println("Done");                
        }catch(Exception ex){
            lblMessage.setText(ex.getMessage());
        }        
    }    
    
    private void PrintEntryRecieptWithDriver(Transvalet transvalet){
        try{
            FileWriter out = new FileWriter(this.controller.getAuth().getPrinterport());
            
            //Initial Printer
            out.write(0x1B);
            out.write(0x40);
            
            out.write("");
            
            //Reverse Feed
            out.write(0x1B);
            out.write(0x65);
            out.write(0x20);
            
            //Center Position
            out.write(0x1B);
            out.write(0x61);
            out.write(0x01);
            
            //Font A
            out.write(0x1B);
            out.write(0x21);
            out.write(0x00);
            
            //Start Double Height Font
            out.write(0x1B);
            out.write(0x21);
            out.write(0x11);            
            out.write("TAMAN ANGGREK MALL");                    
            //Stop Double Height Font
            out.write(0x1B);
            out.write(0x21);
            out.write(0x00);
            
            //Print adn 3 Line Feed
            out.write(0x1B);
            out.write(0x64);
            out.write(0x01);
            
            //Left Align
            out.write(0x1B);
            out.write(0x61);
            out.write(0x00);            

            //Font B
            out.write(0x1B);
            out.write(0x21);
            out.write(0x01);            
            out.write("ID           : " + transvalet.getTransid().toUpperCase());
            out.write(0x0A);
            out.write("Car Number   : " + transvalet.getCarnumber().toUpperCase());
            out.write(0x0A);            
            out.write("Entry Date   : " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(transvalet.getDatetimein()));
            out.write(0x0A);
            Employee driverin = this.controller.getBackingController().getEmployeeController().getByNik(transvalet.getDriverin());
            String driverininitial="";
            if(driverin != null){
               driverininitial = driverin.getInitial();
            }               
            out.write("Driver       : (" + driverininitial + ") " + transvalet.getDriverin());
            out.write(0x0A);
            out.write(0x0A);
            out.write(0x0A);
            out.write(0x0A);
            out.write(0x0A);
            out.write(0x0A);
            out.write(0x0A);
            out.write(0x0A);
            out.write(0x0A);
            out.write(0x0A);
            out.write(0x0A);            
            out.write(0x1D);
            out.write(0x56);
            out.write(0x00);
            out.close();
            System.out.println("Done");                
        }catch(Exception ex){
            lblMessage.setText(ex.getMessage());
        }                
    }   
    
    private void PrintReceipt(Transvalet transvalet){
        if(carInArea == true){
            this.PrintExitRecieptWithDriver(transvalet);
            Transreprint transreprint = new Transreprint();
            transreprint.setOpr(this.controller.getAuth().getNik());
            transreprint.setTransid(transvalet.getTransid());
            transreprint.setReprintdate(new Date(System.currentTimeMillis()));
            this.controller.getBackingController().getTransreprintController().insert(transreprint);            
        }else{
            this.PrintEntryRecieptWithDriver(transvalet);                 
        }                
    }
        
    private void clearAll(){         
        lblplatnumber.setText("-");
        lbldatetimein.setText("-");
        lblboothin.setText("-");                        
        lbldriverin.setText("-");
        lbldatetimeout.setText("-");
        lbldriverout.setText("-");
        lblboothout.setText("-");
        lblduration.setText("-");
        lblservicecharge.setText("-");
        lblparkingcharge.setText("-");                        
        lblticketcharge.setText("-");
        lbltotal.setText("");
    }
    
    private class waitForCleaning implements Runnable{       
        private int Sleeptime;
        public waitForCleaning(int Sleeptime){
            this.Sleeptime = Sleeptime;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(Sleeptime);
            } catch (InterruptedException ex) {
                lblMessage.setText(ex.getMessage());
            }
            clearAll();                    
        }        
    }

    public JTextField getTxtplatnumber() {
        return txtplatnumber;
    }

    public JTextField getTxtVoucher() {
        return txtVoucher;
    }
    
    private void completePlatNumber(){        
        try{
            String firstChar = txtplatnumber.getText().substring(0, 1);        
            int ifirstChar = Integer.parseInt(firstChar);
            if(ifirstChar >=0 || ifirstChar <= 9){
                txtplatnumber.setText("b" + txtplatnumber.getText());            
                this.lblshow.setText(txtplatnumber.getText());
            }
        }catch(Exception ex){
            this.lblMessage.setText(ex.getMessage());
        }
    }

    public JLabel getLblMessage() {
        return lblMessage;
    }
    
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblbooth = new javax.swing.JLabel();
        lbloperator = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblnextid = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblClock = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblshow = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtplatnumber = new javax.swing.JTextField();
        lblDriver = new javax.swing.JLabel();
        txtdriver = new javax.swing.JTextField();
        lblVoucher = new javax.swing.JLabel();
        txtVoucher = new javax.swing.JTextField();
        lblMissing = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblplatnumber = new javax.swing.JLabel();
        lbldatetimein = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbldriverin = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblboothin = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbldatetimeout = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lbldriverout = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblboothout = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblduration = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblservicecharge = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lblparkingcharge = new javax.swing.JLabel();
        lbltotal = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lblticketcharge = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblMessage = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel7.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 102));
        jLabel1.setText("Booth");

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 102));
        jLabel2.setText("Operator");

        lblbooth.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        lblbooth.setForeground(new java.awt.Color(102, 0, 102));
        lblbooth.setText("-");

        lbloperator.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        lbloperator.setForeground(new java.awt.Color(102, 0, 102));
        lbloperator.setText("-");

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 102));
        jLabel3.setText("Next ID");

        lblnextid.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        lblnextid.setForeground(new java.awt.Color(102, 0, 102));
        lblnextid.setText("-");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblnextid, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblbooth, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                            .addComponent(lbloperator, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(lblbooth))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbloperator)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblnextid)
                    .addComponent(jLabel3))
                .addGap(9, 9, 9))
        );

        jPanel1.add(jPanel7);

        jPanel8.setOpaque(false);

        lblClock.setFont(new java.awt.Font("DejaVu Sans", 1, 30)); // NOI18N
        lblClock.setForeground(new java.awt.Color(102, 0, 102));
        lblClock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClock.setText("-");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblClock, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblClock, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel8);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 11, 1010, 90);

        jPanel2.setOpaque(false);

        lblshow.setFont(new java.awt.Font("DejaVu Sans", 1, 96)); // NOI18N
        lblshow.setForeground(new java.awt.Color(102, 0, 102));
        lblshow.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblshow.setText("-");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblshow, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblshow, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(10, 120, 1010, 190);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(1, 2, 10, 10));

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jPanel9.setOpaque(false);

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 0, 102));
        jLabel7.setText("Enter : Next Process");

        jLabel16.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 0, 102));
        jLabel16.setText("Esc    : Back or Exit");

        jLabel18.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 0, 102));
        jLabel18.setText("F5      : Reprint");

        jLabel19.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 0, 102));
        jLabel19.setText("F3      : Change Driver In");

        jLabel23.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 0, 102));
        jLabel23.setText("F4      : Change Driver Out");

        jLabel24.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 0, 102));
        jLabel24.setText("F6      : Voucher");

        jLabel25.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 0, 102));
        jLabel25.setText("F7      : Missing Ticket");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
                        .addGap(5, 5, 5))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel9);

        jPanel10.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 102));
        jLabel6.setText("Plat Number");

        txtplatnumber.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        txtplatnumber.setForeground(new java.awt.Color(102, 0, 102));
        txtplatnumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102)));
        txtplatnumber.setOpaque(false);
        txtplatnumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtplatnumberActionPerformed(evt);
            }
        });

        lblDriver.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        lblDriver.setForeground(new java.awt.Color(102, 0, 102));
        lblDriver.setText("Driver");

        txtdriver.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        txtdriver.setForeground(new java.awt.Color(102, 0, 102));
        txtdriver.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102)));
        txtdriver.setOpaque(false);

        lblVoucher.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        lblVoucher.setForeground(new java.awt.Color(102, 0, 102));
        lblVoucher.setText("Voucher #");

        txtVoucher.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        txtVoucher.setForeground(new java.awt.Color(102, 0, 102));
        txtVoucher.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102)));
        txtVoucher.setOpaque(false);

        lblMissing.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        lblMissing.setForeground(new java.awt.Color(102, 0, 102));
        lblMissing.setText("Missing Ticket : ");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDriver, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(txtdriver, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(txtplatnumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(lblVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(txtVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(lblMissing, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtplatnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDriver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtdriver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVoucher)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMissing)
                .addContainerGap(161, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel10);

        jPanel3.add(jPanel4);

        jPanel5.setOpaque(false);

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 0, 102));
        jLabel8.setText("Plat Number");

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 0, 102));
        jLabel9.setText("Date and Time In");

        lblplatnumber.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        lblplatnumber.setForeground(new java.awt.Color(102, 0, 102));
        lblplatnumber.setText("-");

        lbldatetimein.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        lbldatetimein.setForeground(new java.awt.Color(102, 0, 102));
        lbldatetimein.setText("-");

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 0, 102));
        jLabel10.setText("Driver In");

        lbldriverin.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        lbldriverin.setForeground(new java.awt.Color(102, 0, 102));
        lbldriverin.setText("-");

        jLabel11.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 0, 102));
        jLabel11.setText("Booth In");

        lblboothin.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        lblboothin.setForeground(new java.awt.Color(102, 0, 102));
        lblboothin.setText("-");

        jLabel13.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 0, 102));
        jLabel13.setText("Date and Time Out");

        lbldatetimeout.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        lbldatetimeout.setForeground(new java.awt.Color(102, 0, 102));
        lbldatetimeout.setText("-");

        jLabel14.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 0, 102));
        jLabel14.setText("Driver Out");

        lbldriverout.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        lbldriverout.setForeground(new java.awt.Color(102, 0, 102));
        lbldriverout.setText("-");

        jLabel15.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 0, 102));
        jLabel15.setText("Booth Out");

        lblboothout.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        lblboothout.setForeground(new java.awt.Color(102, 0, 102));
        lblboothout.setText("-");

        jLabel20.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 0, 102));
        jLabel20.setText("Duration");

        lblduration.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        lblduration.setForeground(new java.awt.Color(102, 0, 102));
        lblduration.setText("-");

        jLabel21.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 0, 102));
        jLabel21.setText("Service Charge");

        lblservicecharge.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        lblservicecharge.setForeground(new java.awt.Color(102, 0, 102));
        lblservicecharge.setText("-");

        jLabel22.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 0, 102));
        jLabel22.setText("Parking Charge");

        lblparkingcharge.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        lblparkingcharge.setForeground(new java.awt.Color(102, 0, 102));
        lblparkingcharge.setText("-");

        lbltotal.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        lbltotal.setForeground(new java.awt.Color(102, 0, 102));
        lbltotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltotal.setText("-");

        jLabel26.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 0, 102));
        jLabel26.setText("Ticket Charge");

        lblticketcharge.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        lblticketcharge.setForeground(new java.awt.Color(102, 0, 102));
        lblticketcharge.setText("-");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbltotal, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblparkingcharge, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(lblservicecharge, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(lblduration, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(lblboothout, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(lbldatetimein, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(lbldriverin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(lblboothin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(lbldatetimeout, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(lbldriverout, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(lblplatnumber, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(lblticketcharge, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblplatnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbldatetimein, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbldriverin, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblboothin, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbldatetimeout, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbldriverout, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblboothout, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblduration, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblservicecharge, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblparkingcharge, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(lblticketcharge, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.add(jPanel5);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(10, 340, 1010, 360);

        jPanel6.setOpaque(false);

        lblMessage.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(13, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel6);
        jPanel6.setBounds(10, 720, 1000, 49);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/valetparking/resource/login.jpg"))); // NOI18N
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1024, 770);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-1038)/2, (screenSize.height-805)/2, 1038, 805);
    }// </editor-fold>//GEN-END:initComponents

    
    private class TextPlatNumberChangeListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
    
    
    private void txtplatnumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtplatnumberActionPerformed
        // TODO add your handling code here:
        System.out.println(txtplatnumber.getText().substring(0, 1));
        if(txtplatnumber.getText().substring(0,1).matches("[0-9]")){
            txtplatnumber.setText("b" + txtplatnumber.getText());
        }else{

        }                       
    }//GEN-LAST:event_txtplatnumberActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblClock;
    private javax.swing.JLabel lblDriver;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblMissing;
    private javax.swing.JLabel lblVoucher;
    private javax.swing.JLabel lblbooth;
    private javax.swing.JLabel lblboothin;
    private javax.swing.JLabel lblboothout;
    private javax.swing.JLabel lbldatetimein;
    private javax.swing.JLabel lbldatetimeout;
    private javax.swing.JLabel lbldriverin;
    private javax.swing.JLabel lbldriverout;
    private javax.swing.JLabel lblduration;
    private javax.swing.JLabel lblnextid;
    private javax.swing.JLabel lbloperator;
    private javax.swing.JLabel lblparkingcharge;
    private javax.swing.JLabel lblplatnumber;
    private javax.swing.JLabel lblservicecharge;
    private javax.swing.JLabel lblshow;
    private javax.swing.JLabel lblticketcharge;
    private javax.swing.JLabel lbltotal;
    private javax.swing.JTextField txtVoucher;
    private javax.swing.JTextField txtdriver;
    private javax.swing.JTextField txtplatnumber;
    // End of variables declaration//GEN-END:variables
}
