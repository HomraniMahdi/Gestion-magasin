/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.Imagazin;
import Model.Magazin;
import Model.Produit;
import Util.maConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amine
 */
public class ServiceMagazin implements Imagazin{
        Connection cnx = maConnexion.getInstance().getCnx();
        private Statement ste;
        private PreparedStatement pste;
    @Override
    public void add(Magazin m) {

        try{  
        String req = "INSERT INTO `magazin`(`nom`, `adresse`, `desig`)VALUES (?,?,?)";
            System.out.println(m);
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println(req);
            ps.setString(1, m.getNom().toString());
            ps.setString(2, m.getAdresse().toString());
            ps.setString(3, m.getDesig().toString());
            
            ps.executeUpdate();
 }
        catch (SQLException ex) {
                Logger.getLogger(ServiceMagazin.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Error in inserting magasin");        }
    }


    @Override
    public void update(int id,Magazin m) {
            String sql ="update magazin set nom = ? , adresse= ?,   desig= ? where id  = " + id;
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, m.getNom().toString());
            ps.setString(2, m.getAdresse().toString());
            ps.setString(3, m.getDesig().toString());
            
            ps.executeUpdate();
            System.out.println("magasin modifié avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            }   
        
        
        
    @Override
    public void delete(int id,Magazin m) {
    
            String delete = "DELETE FROM magazin  where id  = ?";
        try {
            pste = cnx.prepareStatement(delete);
            pste.setInt(1, id);
            pste.executeUpdate();

        } catch (SQLException ex) {

            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);

        }   
    }        
        
    @Override
    public List<Magazin> afficherMagazin() {
List<Magazin> Magazins = new ArrayList<>();
        String query = "SELECT * FROM magazin ";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {  
                Magazin m= new Magazin(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4));
                Magazins.add(m);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return Magazins;   
    }

    @Override
    public List<Magazin> ChercherMagazin(String str) {
List<Magazin> Magazins = new ArrayList<>();
        String query = "SELECT * FROM magazin where nom like '"+str+"%' or adresse like '"+str+"%'";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {  
                Magazin m= new Magazin(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4));
                Magazins.add(m);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return Magazins;    
    }
    
}
