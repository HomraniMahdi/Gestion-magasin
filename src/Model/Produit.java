/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author Amine
 */
public class Produit {
    private int id;
    private String nom;
    private float prix;
    private LocalDateTime date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }



    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", prix=" + prix + ", nom=" + nom + ", date=" + date + '}';
    }

    public Produit() {
    }

    public Produit(int id, String nom, float prix, LocalDateTime date) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.date = date;
    }

    public Produit(String nom, float prix, LocalDateTime date) {
        this.nom = nom;
        this.prix = prix;
        this.date = date;
    }


    
    
    
    
}
