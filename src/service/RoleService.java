/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.Role;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import connexionbd.utils.DataSource;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author MSI
 */
public class RoleService implements IService<Role>{
    private Connection conn;
    public RoleService(){
        conn=DataSource.getInstance().getCnx();
   
    }
    
    @Override
    public void insert(Role r) {
        String requete="INSERT INTO Role(id_role,nom) values (?,?)";
        PreparedStatement ste;
        try {
            ste = conn.prepareStatement(requete);
            ste.setInt(1,r.getId_role());
            ste.setString(2,r.getNom());
            ste.executeUpdate();
        }catch (SQLIntegrityConstraintViolationException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur de base de données");
                alert.setContentText("rôle existe déjà");
                alert.showAndWait();
            } catch (SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void delete(int id) {
       String requete="DELETE FROM Role WHERE id_role="+id;
       PreparedStatement ste;
        try {
            ste = conn.prepareStatement(requete);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void update(List<Object> list, int id) {
        String requete="UPDATE role SET nom=? WHERE id_role="+id;
        try {
            PreparedStatement ste = conn.prepareStatement(requete);
            ste.setString(1,(String)list.get(0));
            ste.executeUpdate();
        }catch (SQLIntegrityConstraintViolationException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur de base de données");
                alert.setContentText("rôle existe déjà");
                alert.showAndWait();
            } catch (SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Role> readAll() {
        String requete="SELECT * FROM Role";
        List<Role> list = new ArrayList<>();
        
        try {
            PreparedStatement ste = conn.prepareStatement(requete);
            ResultSet rs = ste.executeQuery(requete);
            UserService us = new UserService();
            while (rs.next()){
                Role r= new Role(rs.getInt(1),rs.getString(2), us.readAll_User());
                list.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    
    @Override
    public Role readByID(int id) {
        String requete="SELECT * FROM Role WHERE id_role=?";
        Role role = null;
        try {
            PreparedStatement ste = conn.prepareStatement(requete);
            ste.setInt(1, id);            
            ResultSet rs= ste.executeQuery();
            UserService us= new UserService();
            if(rs.next()){
                role= new Role(rs.getInt(1),rs.getString(2),us.readyById_role(id));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return role;
    }
   
  
}
