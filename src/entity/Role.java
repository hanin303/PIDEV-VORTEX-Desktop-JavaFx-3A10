/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author MSI
 */
public class Role {
    private int id_role;
    private String nom;
    private HashSet<User> users;
   
    
    
    public Role(){
        users= new HashSet<>();
    }

    public Role(int id_role, String nom){
        this.id_role=id_role;
        this.nom=nom;
        users= new HashSet<>();
    }
    
    public Role(String nom){
        this.nom=nom;
    }
    public Role(int id_role, String nom, HashSet<User> users){
        this.id_role=id_role;
        this.nom=nom;
        this.users=users;
    }
    
    public int getId_role(){
        return id_role;
    }
    public void setId_role(int id_role){
        this.id_role=id_role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    public Set<User> getUsers(){
        return users;
    }
    public void setUser(HashSet<User> users){
        this.users=users;
    }
    
    public void addUser(User user){
        if(!this.users.contains(user)){
            this.users.add(user);
        }   
    }
    
    public void deleteUser(User user){
        if(this.users.contains(user)){
            this.users.remove(user);
        }
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id_role;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Role other = (Role) obj;
        return this.id_role == other.id_role;
    }

    @Override
    public String toString() {
        return "Role{" + "id_role=" + id_role + ", nom=" + nom + ", users=" + users + '}';
    }
        
}
