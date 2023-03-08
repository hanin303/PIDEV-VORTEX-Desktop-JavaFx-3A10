package service;

import connexionbd.utils.DataSource;
import entity.Station;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StationService implements IService<Station> {

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(Station s) {
        try {
            String req = "INSERT INTO `station` (`lang_alt`, `id_moyen_transport`) VALUES (?, ?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, s.getLang_alt());
            ps.setInt(2, s.getId_moyen_transport());
            ps.executeUpdate();
            System.out.println("Station created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(int id_station) {
        try {
            String req = "DELETE FROM `station` WHERE id_station = " + id_station;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Station deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Station s) {
        try {
            String req = "UPDATE `station` SET `lang_alt`=?,`id_moyen_transport`=? WHERE id_station=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, s.getLang_alt());
            ps.setInt(2, s.getId_moyen_transport());
            ps.setInt(3, s.getId_station());
            ps.executeUpdate();
            System.out.println("Station updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Station> getAll() {
        List<Station> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `station`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Station s = new Station(rs.getInt("id_station"), rs.getString("lang_alt"), rs.getInt("id_moyen_transport"));
                list.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public Station getOneById(int id_station) {
        Station s = null;
        try {
            String req = "SELECT * FROM `station` WHERE id_station = " + id_station;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                s = new Station(rs.getInt("id_station"), rs.getString("lang_alt"), rs.getInt("id_moyen_transport"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return s;
    }

    @Override
    public void update(List<Object> list, int id) {
        String requete = "UPDATE `station` SET `id_station`,`lang_alt`=?,`id_moyen_transport`=?=? WHERE id_station=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, (String) list.get(0));
            ps.setInt(2, (int) list.get(1));
            ps.setInt(3, (int) list.get(2));
            ps.setInt(4, id);
            ps.executeUpdate();
            System.out.println("Station updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void insert(Station t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Station> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Station readByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}