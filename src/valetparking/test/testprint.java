/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class testprint {
   
    public testprint(){
        FileWriter out = null;
        try {
            out = new FileWriter("/dev/ttyUSB0");     
            out.write((char) 27 + "Testing" + (char) 10);
            out.write((char) 27 + "Testing" + (char) 10);
            out.write((char) 27 + "Testing" + (char) 10);
            out.write((char) 27 + "Testing" + (char) 10);
            out.write((char) 27 + "Testing" + (char) 10);
            out.write((char) 27 + "Testing" + (char) 10);
            out.write((char) 27 + "Testing" + (char) 10);
            
            out.close();
            
            System.out.println("Done");   
        } catch (IOException ex) {
            Logger.getLogger(testprint.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(testprint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String[] argv){
        new testprint();
    }
    
}
