/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Produit;

import java.util.List;

/**
 *
 * @author Amine
 */
public interface Iproduit {
    
    public void add(Produit p);
public void update(int id,Produit p) ;
public void delete(int id,Produit p);
 public List<Produit> afficherProduit();
         public List<Produit> ChercherProduit(String str);
}
