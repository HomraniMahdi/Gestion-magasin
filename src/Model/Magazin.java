/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Amine
 */
public class Magazin {
    private int id;
    private String nom;
    private String adresse;
    private String desig;

    public Magazin() {
    }

    public Magazin(String nom, String adresse, String desig) {
        this.nom = nom;
        this.adresse = adresse;
        this.desig = desig;
    }
        public Magazin(int id, String nom, String adresse, String desig) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.desig = desig;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDesig() {
        return desig;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }

    @Override
    public String toString() {
        return "Magazin{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", desig=" + desig + '}';
    }
    
}
