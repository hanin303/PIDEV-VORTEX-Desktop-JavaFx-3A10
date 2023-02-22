/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swifttransitproject2;

import connexionbd.utils.DataSource;
import entity.Role;
import entity.User;
import service.RoleService;
import service.UserService;

/**
 *
 * @msi
 */
public class SwiftTransitProject2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        DataSource ds1 = DataSource.getInstance();
//        System.out.println(ds1);        
        Role r1 = new Role(1,"super_admin");
        Role r2 = new Role(2,"admin_station");
        Role r3 = new Role(3,"conducteur");
        Role r4 = new Role(4,"client");

        RoleService rs = new RoleService();
//        rs.insert(r1);
//        rs.insert(r2);
//        rs.insert(r3);
//        rs.insert(r4);
        rs.readAll().forEach(System.out::println);
//         System.out.println(rs.readByID(2));



//          List<Object> update_role= new ArrayList<>(Arrays.asList("abir"));
//          rs.update(update_role, 2);

//      rs.delete(2);
 


//     
        
     User u1= new User("Machraoui","Abir","abirmachraoui","abir.machraoui@hotmail.com","1234",58028902,123445678,r1);
     User u2= new User("Hadj Mefteh","Wejdene","wejdenehadjmefteh","wejden.hadjmefteh@hotmail.com","12345",58067995,12674884,r1);
     User u3= new User("Bouden","Khaled","khaledbouden","khaled.bouden@gmail.com","gH687",58570995,12580934,r2);
     User u4= new User("Ben Jemaa","Hanin","haninbenjemaa","hanin.benjemaa@hotmail.com","GFHJJK",4567950,67989034,r2);
     User u5= new User("Klila","Karim","karimklila","karim.klila@hotmail.com","8939",98493040,78734393,r3);
     User u6= new User("Hassayoune","Wassim","wassimhassayoune","wassim.hassayoune@yahoo.com","12jnek5",78439523,76324043,r4);
     User u7= new User("BER","abir","abir","abir","abir",638,739,r2);
        UserService us= new UserService();
        us.insert(u1);
        us.insert(u2);
        us.insert(u3);
        us.insert(u4);
        us.insert(u5);
        us.insert(u6);
        us.insert(u7);

//        us.delete(123);

        
//      List<Object> update_user = new ArrayList<>(Arrays.asList("falten","falteni","falteni.falteni","falten.falteni@gmail.com","abcde",45678930));
//      us.update(update_user, 123);
//   us.readAll().forEach(System.out::println);
        
        

    }
    
}
