/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.User;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import connexionbd.utils.DataSource;
import static java.time.Clock.system;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author MSI
 */
public class UserService implements IService<User> {
    
    private Connection conn;
    
    public UserService(){
        conn=DataSource.getInstance().getCnx();
    }

    @Override
    public void insert(User u){
        String requete="INSERT INTO user(nom,prenom,username,email,mdp,num_tel,cin,image,id_role) values (?,?,?,?,?,?,?,?,?)";
        try {
            
            PreparedStatement ste=conn.prepareStatement(requete);
            ste.setString(1,u.getNom());
            ste.setString(2,u.getPrenom());
            ste.setString(3,u.getUsername());
            ste.setString(4,u.getEmail());            
            ste.setString(5, u.getMdp());
            ste.setInt(6, u.getNum_tel());
            ste.setInt(7, u.getCin());
            ste.setString(8,u.getImage());
            ste.setInt(9, u.getRole().getId_role());
            ste.executeUpdate();
            
//        } catch (SQLIntegrityConstraintViolationException e) {
//            if (e instanceof SQLIntegrityConstraintViolationException && e.getMessage().contains("username")) {
//                Alert alert = new Alert(AlertType.ERROR);
//                alert.setTitle("Erreur");
//                alert.setHeaderText("Erreur de base de données");
//                alert.setContentText("username existe déjà");
//                alert.showAndWait();
//                 return;
//            } else if (e instanceof SQLIntegrityConstraintViolationException && e.getMessage().contains("email")) {
//                Alert alert = new Alert(AlertType.ERROR);
//                alert.setTitle("Erreur");
//                alert.setHeaderText("Erreur de base de données");
//                alert.setContentText("L'adresse e-mail existe déjà");
//                alert.showAndWait();
//                return;
//
//        }
        }catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void delete(int id) {
        String requete = "DELETE FROM User where id_user ="+id;
        try {
            PreparedStatement ste = conn.prepareStatement(requete);
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public void update(List<Object> list, int id){
        String requete="UPDATE user SET nom=?, prenom=?, username=?, email=?, mdp=?, num_tel=?, cin=?, image=? where id_user="+id;
        
        try {
            PreparedStatement ste= conn.prepareStatement(requete);
            ste.setString(1,(String)list.get(0));
            ste.setString(2,(String)list.get(1));
            ste.setString(3, (String)list.get(2));
            ste.setString(4, (String)list.get(3));
            ste.setString(5, (String)list.get(4));
            ste.setInt(6, (int)list.get(5));
            ste.setInt(7, (int)list.get(6));
            ste.setString(8, (String) list.get(7));
//            ste.setInt(8, (int)list.get(7));
            ste.executeUpdate();         
//        }catch (SQLIntegrityConstraintViolationException e) {
//            if (e instanceof SQLIntegrityConstraintViolationException && e.getMessage().contains("username")) {
//                Alert alert = new Alert(AlertType.ERROR);
//                alert.setTitle("Erreur");
//                alert.setHeaderText("Erreur de base de données");
//                alert.setContentText("username existe déjà");
//                alert.showAndWait();
//            } else if (e instanceof SQLIntegrityConstraintViolationException && e.getMessage().contains("email")) {
//                Alert alert = new Alert(AlertType.ERROR);
//                alert.setTitle("Erreur");
//                alert.setHeaderText("Erreur de base de données");
//                alert.setContentText("L'adresse e-mail existe déjà");
//                alert.showAndWait();   
//
//        }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updatePassword(String email,String password){
        String requete="UPDATE user SET mdp=? where email=\""+email+"\"";
        System.out.println(email);
        try {
            PreparedStatement ste= conn.prepareStatement(requete);
            ste.setString(1,password);
            ste.executeUpdate();   
        }catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void updateWithoutImage(List<Object> list, int id){
        String requete="UPDATE user SET nom=?, prenom=?, username=?, email=?, mdp=?, num_tel=?, cin=? where id_user="+id;
        
        try {
            PreparedStatement ste= conn.prepareStatement(requete);
            ste.setString(1,(String)list.get(0));
            ste.setString(2,(String)list.get(1));
            ste.setString(3, (String)list.get(2));
            ste.setString(4, (String)list.get(3));
            ste.setString(5, (String)list.get(4));
            ste.setInt(6, (int)list.get(5));
            ste.setInt(7, (int)list.get(6));
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<User> readAll() {
        String requete="SELECT * FROM user";
        List<User> list= new ArrayList<>();
        
        try {
            Statement ste= conn.createStatement();
            ResultSet rs= ste.executeQuery(requete);
            while(rs.next()){
                
                RoleService role_service= new RoleService();
                User u= new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getString(9),role_service.readByID(rs.getInt(10)));
                list.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public HashSet<User> readAll_User() {
        String requete="SELECT * FROM user";
        HashSet<User> set= new HashSet<>();
        
        try {
            Statement ste= conn.createStatement();
            ResultSet rs= ste.executeQuery(requete);
            while(rs.next()){
                User u= new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getString(9));
                set.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;
    }
    @Override
    public User readByID(int id) {
        String requete="SELECT * FROM user WHERE id_user=?";
        User user= null;
        try {
            PreparedStatement ste=conn.prepareStatement(requete);
            ste.setInt(1, id);
            ResultSet rs= ste.executeQuery();
            RoleService role_service= new RoleService();
            if(rs.next()){
            user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getString(9),role_service.readByID(rs.getInt(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    public User readByEmail(String email) {
        String requete="SELECT * FROM user WHERE email=?";
        User user= null;
        try {
            PreparedStatement ste=conn.prepareStatement(requete);
            ste.setString(1, email);
            ResultSet rs= ste.executeQuery();
            RoleService role_service= new RoleService();
            if(rs.next()){
            user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getString(9),role_service.readByID(rs.getInt(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
     public User readByUsername(String username) {
        String requete="SELECT * FROM user WHERE username=?";
        User user= null;
        try {
            PreparedStatement ste=conn.prepareStatement(requete);
            ste.setString(1, username);
            ResultSet rs= ste.executeQuery();
            RoleService role_service = new RoleService();
            if(rs.next()){
            user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getString(9),role_service.readByID(rs.getInt(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    } 
     public User readByCin(String cin) {
        String requete="SELECT * FROM user WHERE cin=?";
        User user= null;
        try {
            PreparedStatement ste=conn.prepareStatement(requete);
            ste.setString(1, cin);
            ResultSet rs= ste.executeQuery();
            RoleService role_service= new RoleService();
            if(rs.next()){
            user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getString(9),role_service.readByID(rs.getInt(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
   public HashSet<User> readyById_role(int id){
       String requete="SELECT * FROM user Where id_role="+id;
       HashSet<User> set = new HashSet<>();
       
        try {
            Statement ste = conn.prepareStatement(requete);
            ResultSet rs= ste.executeQuery(requete);
            while(rs.next()){
            User u  = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getString(9));
            set.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;
      
   }
   

}
