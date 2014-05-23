/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.listener.clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import valetparking.db.BackingController;
import valetparking.gui.ValetFrame;

/**
 *
 * @author root
 */
public class TransactionListener implements ActionListener {  
    private ValetFrame frame;
    private BackingController backingController;

    public TransactionListener(ValetFrame frame){
        this.frame = frame;
        this.backingController = new BackingController();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
   
}
