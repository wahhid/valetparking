/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.listener.clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import valetparking.gui.ValetFrame;

/**
 *
 * @author wahhid
 */
public class ClockListener implements ActionListener {

    private ValetFrame frame;

    public ClockListener(ValetFrame frame){
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Calendar now = Calendar.getInstance();
        String E = new SimpleDateFormat("E").format(new Date(System.currentTimeMillis()));
        String d = new SimpleDateFormat("dd").format(new Date(System.currentTimeMillis()));
        String mo = new SimpleDateFormat("MM").format(new Date(System.currentTimeMillis()));
        String y = new SimpleDateFormat("yyyy").format(new Date(System.currentTimeMillis()));
        String h = new SimpleDateFormat("H").format(new Date(System.currentTimeMillis()));
        String m = new SimpleDateFormat("mm").format(new Date(System.currentTimeMillis()));
        String s = new SimpleDateFormat("ss").format(new Date(System.currentTimeMillis()));
        this.frame.getLblClock().setText("" + E + ", " + d + "/" + mo + "/" + y + " " + h + ":" + m + ":" + s);
    }
    
}
