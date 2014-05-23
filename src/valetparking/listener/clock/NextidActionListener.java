/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.listener.clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import valetparking.db.BackingController;
import valetparking.entity.Idseq;

/**
 *
 * @author root
 */
public class NextidActionListener implements ActionListener{

    private JLabel lblnextid;
    private String boothid;
    private BackingController backingController;
    
    public NextidActionListener(JLabel lblnextid,String boothid){
        this.lblnextid = lblnextid;
        this.boothid = boothid;
        this.backingController = new BackingController();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            Idseq idseq = this.backingController.getIdseqController().getByBoothID(boothid);
            if(idseq != null){
                this.lblnextid.setText(Integer.toString(idseq.getSeqvalue()));
            }else{
                this.lblnextid.setText("Error read data");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
