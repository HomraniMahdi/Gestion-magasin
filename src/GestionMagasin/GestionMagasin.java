/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blastandburn;


import Util.maConnexion;
import java.sql.Connection;
import Model.Magazin;
import Model.Produit;
import Services.ServiceMagazin;
import Services.ServiceProduit;
import java.time.Instant;
import java.sql.Date;
import javax.mail.MessagingException;



/**
 *
 * @author jalloul
 */
public class Blastandburn {

    /**
     * @param args the command line arguments
     */
 
    
    public static void main(String[] args) throws MessagingException, Exception {
        // TODO code application logic here
        Connection cnx= maConnexion.getInstance().getCnx();
//          Date d= new Date(2022, 12, 15);
//        // instance magazin
//        Magazin m = new Magazin(1, "nom", "prod", "deg");
//        // instanceproduit
//         Produit p = new Produit(1, "tv",5.4f, d);
//         // service magazin
//        ServiceMagazin ss = new ServiceMagazin();
//        // service prod
//        ServiceProduit sp = new ServiceProduit();
//       // ss.add(m); ajout magazin
//     //   sp.add(p); ajout produit
//        // instance produit
        
         
//         //recherche avancé
//         System.out.println("Recherche____________produit___________");
//          
//          System.out.println(sp.ChercherProduit("jalloul"));
//          System.out.println("Recherche____________magazin___________");
//          System.out.println(ss.ChercherMagazin("nom"));
//          //afficher tous les produits
//          System.out.println("Afficher tous les produits");
//          System.out.println(sp.afficherProduit());
//          System.out.println("Afficher tous les magazins");
//          System.out.println(ss.afficherMagazin());
////          //Mailing api ; 
//          System.out.println("Mailing________________________");
//          Util.JavaMailUtils.sendMail("monrezult@gmail.com");
        
          
    
    }
}
