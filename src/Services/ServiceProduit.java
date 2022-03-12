/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.Iproduit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Produit;
import Util.maConnexion;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Amine
 */
public class ServiceProduit implements Iproduit{

    Connection cnx = maConnexion.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement pste;
    
    @Override
    public void add(Produit p) {
 String Req = "INSERT INTO `produit`( `id`, `nom`, `prix`, `date`)VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(Req);
            ps.setInt(1, p.getId());
            ps.setString(2, p.getNom().toString());
            ps.setFloat(3, p.getPrix());
            ps.setString(4, p.getDate().toString());
            ps.execute();
            System.out.println("produit ajoutee avec succes");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
          }


    public void update(int id,Produit p) {
             String sql ="update produit set nom = ? , prix= ?,   date= ? where id  = " + id;
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, p.getNom().toString());
            ps.setFloat(2, p.getPrix());
            ps.setString(3, p.getDate().toString());
            
            ps.executeUpdate();
            System.out.println("produit modifié avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(int id ,Produit p) {
            String delete = "DELETE FROM produit  where id  = ?";
        try {
            pste = cnx.prepareStatement(delete);
            pste.setInt(1, id);
            pste.executeUpdate();

        } catch (SQLException ex) {

            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);

        } 
    }
    
    

    

    @Override
    public List<Produit> afficherProduit() {
    List<Produit> Produits = new ArrayList<>();
        String query = "SELECT * FROM produit ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {  
                Produit p= new Produit(rs.getInt(1), rs.getString(2),rs.getFloat(3), rs.getTimestamp(4).toLocalDateTime());
                Produits.add(p);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return Produits;    }

    @Override
    public List<Produit> ChercherProduit(String str) {
List<Produit> Produits = new ArrayList<>();
        String query = "SELECT * FROM produit where nom like '"+str+"%' or prix like '"+str+"%'";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {  
                Produit p= new Produit(rs.getInt(1), rs.getString(2),rs.getFloat(3), rs.getTimestamp(4).toLocalDateTime());
                Produits.add(p);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return Produits;    }
    
    
    
            public HashMap<String, Double> StatistiqueParNom() {
        HashMap<String, Double> data = new HashMap<>();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT nom, COUNT(*) as nb FROM produit GROUP BY nom;";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int nb = rs.getInt("nb");
                String key = nb + " " + rs.getString("nom");
                data.put(key, new Double(nb));
            }
            System.out.println(data.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
}
    
}
