package service;

import connexionbd.utils.DataSource;
import entity.Commune;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommuneService implements IService<Commune> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Commune c) {
        try {
            String req = "INSERT INTO `commune` (`nom_commune`, `long_alt`) VALUES (?, ?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, c.getNom_commune());
            ps.setString(2, c.getLong_alt());
            ps.executeUpdate();
            System.out.println("Commune created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(int id_commune) {
        try {
            String req = "DELETE FROM `commune` WHERE id_commune = " + id_commune;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commune deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Commune c) {
        try {
            String req = "UPDATE `commune` SET `nom_commune`=?,`long_alt`=? WHERE id_commune=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, c.getNom_commune());
            ps.setString(2, c.getLong_alt());
            ps.setInt(3, c.getId_commune());
            ps.executeUpdate();
            System.out.println("Commune updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Commune> getAll() {
        List<Commune> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `commune`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Commune c = new Commune(rs.getInt("id_commune"), rs.getString("nom_commune"), rs.getString("long_alt"));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public Commune getOneById(int id_commune) {
        Commune c = null;
        try {
            String req = "SELECT * FROM `commune` WHERE id_commune = " + id_commune;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                c = new Commune(rs.getInt("id_commune"), rs.getString("nom_commune"), rs.getString("long_alt"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }

    @Override
    public void update(List<Object> list, int id) {
        String requete = "UPDATE `commune` SET `id_commune`,`nom_commune`=?,`long_alt`=?=? WHERE id_commune=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, (String) list.get(0));
            ps.setString(2, (String) list.get(1));
            ps.setInt(3, id);
            ps.executeUpdate();
            System.out.println("Commune updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void insert(Commune t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commune> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Commune readByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
   