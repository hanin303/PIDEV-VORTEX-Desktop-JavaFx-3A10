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
import entity.Role;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String requete="INSERT INTO user(nom,prenom,username,email,mdp,num_tel,cin,id_role) values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ste=conn.prepareStatement(requete);
            ste.setString(1,u.getNom());
            ste.setString(2,u.getPrenom());
            ste.setString(3,u.getUsername());
            ste.setString(4,u.getEmail());
            ste.setString(5, u.getMdp());
            ste.setInt(6, u.getNum_tel());
            ste.setInt(7, u.getCin());
            ste.setInt(8, u.getRole().getId_role());
            ste.executeUpdate();
        } catch (SQLException ex) {
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
//            ste.setInt(8, (int)list.get(7));
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
                User u= new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),role_service.readByID(rs.getInt(9)));
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
                User u= new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8));
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
            user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),role_service.readByID(rs.getInt(9)));
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
            User u  = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8));
            set.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;
      
   }
   

}
