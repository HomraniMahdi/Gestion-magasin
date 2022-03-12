/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Model.Magazin;
import Model.Produit;
import java.util.List;

/**
 *
 * @author Amine
 */
public interface Imagazin {
        public void add(Magazin m);
public void update(int id,Magazin m) ;
public void delete(int id,Magazin m);
 public List<Magazin> afficherMagazin();
         public List<Magazin> ChercherMagazin(String str);
}
