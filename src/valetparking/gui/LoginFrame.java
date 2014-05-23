/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LoginFrame.java
 *
 * Created on Jun 30, 2011, 2:40:40 PM
 */
package valetparking.gui;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import valetparking.controller.LoginController;
import valetparking.controller.ValetController;
import valetparking.services.UserAuthentication;

/**
 *
 * @author wahhid
 */
public class LoginFrame extends javax.swing.JFrame {

    private LoginController controller;
    private UserAuthentication userAuthentication;
    
    /** Creates new form LoginFrame */
    public LoginFrame(LoginController controller) {
        this.controller = controller;
        this.userAuthentication = new UserAuthentication(this.controller);
        this.setUndecorated(true);
        initComponents();
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = (int) tk.getScreenSize().getWidth();
        int ySize = (int) tk.getScreenSize().getHeight();
        this.setSize(1024,768);
        this.lblbackground.setSize(1024,768);
                        
        this.txtusername.addKeyListener(new TextUsernameKeyListener());
        this.txtpassword.addKeyListener(new TextPasswordKeyListener());
        this.txtbooth.addKeyListener(new TextBoothKeyListener());
     
        
        this.txtusername.requestFocus();
        this.txtpassword.setEnabled(false);
        this.txtbooth.setEnabled(false);               
    }


    public LoginFrame getLoginFrame(){
        return this;
    }
    
    private class TextUsernameKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int iKey = e.getKeyCode();
            if(iKey == KeyEvent.VK_ENTER){
                if(txtusername.getText().length() != 0){
                    txtpassword.setEnabled(true);
                    txtpassword.requestFocus();
                    txtusername.setEnabled(false);
                }else{
                    
                }
            }       
            
            if(iKey == KeyEvent.VK_F10){
                if(txtusername.getText().length() == 0){
                    System.exit(0);
                }else{
                    lblErrorMsg.setText("Username not empty !");
                }   
            }
            
            if(iKey == KeyEvent.VK_ESCAPE){
                txtusername.setText("");
                txtusername.requestFocus();
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
        
    }
    
    
    private class TextPasswordKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int iKey = e.getKeyCode();
            if(iKey == KeyEvent.VK_ENTER){
                if(txtpassword.getText().length() != 0){
                    txtbooth.setEnabled(true);
                    txtbooth.requestFocus();
                    txtpassword.setEnabled(false);
                }else{
                    
                }                
            }    
            
            if(iKey == KeyEvent.VK_ESCAPE){                
                txtusername.setEnabled(true);
                txtusername.requestFocus();
                txtpassword.setText("");
                txtpassword.setEnabled(false);
            }                                                
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
        
    }
    
    
    private class TextBoothKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int iKey = e.getKeyCode();
            if(iKey == KeyEvent.VK_ENTER){
                if(txtbooth.getText().length() != 0){            
                    controller.CheckBooth(txtbooth.getText());
                    if(controller.getBooth() != null){
                        userAuthentication.Authenticate(txtusername.getText(), txtpassword.getText());                    
                        if(userAuthentication.isLoginstatus() == true){                                    
                            clearLoginInfo();                            
                            userAuthentication.getAuth().setBooth(controller.getBooth().getBoothid());
                            userAuthentication.getAuth().setBoothname(controller.getBooth().getBoothname());
                            userAuthentication.getAuth().setDriver(controller.getBooth().getDriver());
                            userAuthentication.getAuth().setPrintin(controller.getBooth().getPrintin());
                            userAuthentication.getAuth().setPrintout(controller.getBooth().getPrintout());
                            userAuthentication.getAuth().setPrinterport(controller.getBooth().getPrinterport());
                            //userAuthentication.getAuth().setReportpath(controller.getConfiguration().getReportpath());
                            new ValetController(getLoginFrame(),userAuthentication.getAuth());                                                                        
                        }else{
                            clearLoginInfo();
                            lblErrorMsg.setText("Try to login!!!");
                        }                                                                                    
                    }else{
                        lblErrorMsg.setText("Booth not found!!!");    
                    }
                
                    
                }else{
                    
                }
            }    
                       
            if(iKey == KeyEvent.VK_ESCAPE){
                txtpassword.setEnabled(true);
                txtpassword.requestFocus();
                txtbooth.setText("");
                txtbooth.setEnabled(false);                               
            }
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
        
    }

    
    private void clearLoginInfo(){
        this.txtusername.setEnabled(true);
        this.txtusername.setText("");        
        this.txtpassword.setText("");
        this.txtpassword.setEnabled(false);
        this.txtbooth.setText("");
        this.txtbooth.setEnabled(false);
        this.txtusername.requestFocus();
    }
    
    public JTextField getTxtbooth() {
        return txtbooth;
    }

    public JPasswordField getTxtpassword() {
        return txtpassword;
    }

    public JTextField getTxtusername() {
        return txtusername;
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
        jLabel1 = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtpassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        txtbooth = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lblErrorMsg = new javax.swing.JLabel();
        lblbackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 102));
        jLabel1.setText("Username");

        txtusername.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        txtusername.setForeground(new java.awt.Color(102, 0, 102));
        txtusername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102)));
        txtusername.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 102));
        jLabel2.setText("Password");

        txtpassword.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        txtpassword.setForeground(new java.awt.Color(102, 0, 102));
        txtpassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102)));
        txtpassword.setOpaque(false);
        txtpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpasswordActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 102));
        jLabel3.setText("Booth");

        txtbooth.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        txtbooth.setForeground(new java.awt.Color(102, 0, 102));
        txtbooth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 102)));
        txtbooth.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 102));
        jLabel4.setText("Enter : Next , Esc : Back , F10 : Exit");

        lblErrorMsg.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        lblErrorMsg.setForeground(new java.awt.Color(102, 0, 102));
        lblErrorMsg.setText("-");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                .addGap(27, 27, 27)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtpassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                            .addComponent(txtusername, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbooth, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                    .addComponent(lblErrorMsg, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtbooth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblErrorMsg)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(680, 580, 333, 170);

        lblbackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/valetparking/resource/login2.jpg"))); // NOI18N
        getContentPane().add(lblbackground);
        lblbackground.setBounds(0, 0, 1020, 770);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-1036)/2, (screenSize.height-805)/2, 1036, 805);
    }// </editor-fold>//GEN-END:initComponents

    private void txtpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpasswordActionPerformed

    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblErrorMsg;
    private javax.swing.JLabel lblbackground;
    private javax.swing.JTextField txtbooth;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
