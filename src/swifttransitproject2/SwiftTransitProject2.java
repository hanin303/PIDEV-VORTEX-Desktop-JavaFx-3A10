/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swifttransitproject2;

import connexionbd.utils.DataSource;

/**
 *
 * @author hanin
 */
public class SwiftTransitProject2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DataSource ds1 = DataSource.getInstance();
        System.out.println(ds1);
        DataSource ds2 = DataSource.getInstance();
        System.out.println(ds2);
        DataSource ds3 = DataSource.getInstance();
        System.out.println(ds3);
    }
    
}
