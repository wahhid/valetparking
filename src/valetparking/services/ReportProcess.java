/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package valetparking.services;


import valetparking.entity.Transvalet;

/**
 *
 * @author wahhid
 */
public class ReportProcess {
   
        
    public ReportProcess(){
        
    }
    
//    public JRDataSource createReportDataSource(Transvalet transvalet){
//        JRBeanArrayDataSource dataSource;
//        Transvalet[] reportRows = initializeBeanArray(transvalet);
//        dataSource = new JRBeanArrayDataSource(reportRows);
//        return dataSource;
//    }
    
    private Transvalet[] initializeBeanArray(Transvalet transvalet){
        Transvalet[] reportRows = new Transvalet[1];
        reportRows[0] = transvalet;
        return reportRows;
    }
    
}
