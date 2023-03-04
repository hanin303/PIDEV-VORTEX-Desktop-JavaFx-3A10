/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
/**
 *
 * @author MSI
 */
public class User {
    private int id_user;
    private String nom;
    private String prenom;
    private String username;
    private String email;
    private String mdp;
    private int num_tel;
    private int cin;
    private String image;
    private  Role role;    
    public User(){
    }
    
    public User(String nom, String prenom, String username,String email, String mdp, int num_tel, int cin,String image, Role role){
        this.nom=nom;
        this.prenom=prenom;
        this.username=username;
        this.email=email;
        this.mdp=mdp;
        this.num_tel=num_tel;
        this.cin=cin; 
        this.image=image;
        this.role=role;
    }
    
    public User( int id_user,String nom, String prenom, String username,String email, String mdp, int num_tel, int cin, String image){
        this.id_user=id_user;
        this.nom=nom;
        this.prenom=prenom;
        this.username=username;
        this.email=email;
        this.mdp=mdp;
        this.num_tel=num_tel;
        this.cin=cin; 
        this.image=image;
    }
  
    public User( int id_user,String nom, String prenom, String username,String email, String mdp, int num_tel, int cin, String image,Role role){
        this.id_user=id_user;
        this.nom=nom;
        this.prenom=prenom;
        this.username=username;
        this.email=email;
        this.mdp=mdp;
        this.num_tel=num_tel;
        this.cin=cin; 
        this.image=image;
        this.role=role;
    }
    
    public int getId_user(){
        return id_user;
    }
    public void setId_user(int id_user){
        this.id_user=id_user;
    }
    
    public String getNom(){
        return nom;
    }
    public void setNom(String nom){
        this.nom=nom;
    }
    
    public String getPrenom(){
        return prenom;
    }
    public void setPrenom(String prenom){
        this.prenom=prenom;
    }
    
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    
    public String getMdp(){
        return mdp;
    }
    public void setMdp(String mdp){
        this.mdp=mdp;
    }
    
    public int getNum_tel(){
        return num_tel;
    }
    public void setNum_tel(int num_tel){
        this.num_tel=num_tel;
    }
    
    public int getCin(){
        return cin;
    }
    public void setCin(int cin){
        this.cin=cin;
    }
    public String getImage(){
        return image;
    }
    public void setImage(){
        this.image=image;
    }
    
    public Role getRole(){
        return role;
    }
    
    public void setRole(Role role){
        this.role=role;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id_user;
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
        final User other = (User) obj;
        return this.id_user == other.id_user;
    }

    @Override
    public String toString() {
        if(role!=null){
        return "User{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", email=" + email + ", mdp=" + mdp + ", num_tel=" + num_tel + ", cin=" + cin + ", image=" + image + ", role=" + role.getId_role()+ '}';
    }else {
        return "User{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", email=" + email + ", mdp=" + mdp + ", num_tel=" + num_tel + ", cin=" + cin + ", image=" + image +'}';
        }
    }
    
}
