/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.services;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import valetparking.controller.ValetController;
import valetparking.db.ShiftController;
import valetparking.entity.Shift;

/**
 *
 * @author wahhid
 */
public class FindShift {
        
    private ValetController controller;
    
    private SimpleDateFormat fullFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    
    private Date transdate;
    
    public FindShift(ValetController controller){
        this.controller = controller;
    }
    
    private ShiftController getShiftController(){
        return this.controller.getBackingController().getShiftController();
    }
    public Shift Find(){
        List<Shift> shifts;
        Shift currentShift = new Shift();
        shifts = this.getShiftController().getAll();
        if(shifts != null){
            for(Shift shift : shifts){
                Date currentDate = new Date(System.currentTimeMillis());
                String strCurrentDate = dateFormat.format(currentDate);
                if(shift.getNextday() == false){
                    try {
                        //Same Day
                        Date startTime = fullFormat.parse(strCurrentDate + " " + timeFormat.format(shift.getStarttime()));
                        Date endTime = fullFormat.parse(strCurrentDate + " " + timeFormat.format(shift.getEndtime()));
                        if(startTime.before(currentDate) && endTime.after(currentDate)){
                            currentShift = shift;
                            transdate = currentDate;
                            break;
                        }
                    } catch (ParseException ex) {
                        System.out.println();
                    }                                        
                }else{
                    //Next Day                    
                    Date startTime;
                    Date endTime;
                    try {                        
                        startTime = fullFormat.parse(strCurrentDate + " 00:00:00");                        
                        endTime = fullFormat.parse(strCurrentDate + " 07:00:00");
                        if(startTime.before(currentDate) && endTime.after(currentDate)){
                            currentShift = shift;                           
                            Calendar cal1 = Calendar.getInstance();                                
                            cal1.setTime(currentDate);
                            cal1.add(Calendar.DATE, -1);
                            transdate = cal1.getTime();                            
                        }else{
                            
                            startTime = fullFormat.parse(strCurrentDate + " 22:00:00");
                            endTime = fullFormat.parse(strCurrentDate + " 23:59:99");
                            if(startTime.before(currentDate) && endTime.after(currentDate)){
                                currentShift = shift;
                                transdate = currentDate;                                                           
                            }
                        }                        
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }                                        
                }
            }
        }else{
            currentShift = null;
        }        
        return currentShift;
    }

    public Date getTransdate() {
        return transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }
    
    
}
