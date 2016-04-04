package dz.esi.commerce.mshop.metier_commerce;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Fatteh on 3/16/2016.
 */
public class ListProduit extends LinkedList<Produit> implements Serializable {

    private LinkedList<String> listCategories;
    private LinkedList<String> listProfiles;

    public ListProduit() {
        listCategories = new LinkedList<String>();
        listProfiles = new LinkedList<String>();
    }

    public ListProduit(Collection<? extends Produit> collection) {
        super(collection);
        listCategories = new LinkedList<String>();
        listProfiles = new LinkedList<String>();
    }



    @Override
    public boolean add(Produit object) { //Redefinir pour recuperer la list des catgeories
        if (!listCategories.contains(object.getCategorie()))
            listCategories.add(object.getCategorie());
            // si ce produit appartient a une nouvelle categories, on l'ajout a la list des categories

        if (!listProfiles.contains(object.getProfil()))
            listProfiles.add(object.getProfil());
            // si ce produit appartient a une nouvelle categories, on l'ajout a la list des categories

        return super.add(object);
    }


    public ListProduit filtrerByCategorie(String categorie) // renvoi tous les  produits qui concerne une categorie
    {
        ListProduit result = new ListProduit();

        for (Produit produit : this) {
            if (produit.getCategorie().equals(categorie)) result.add(produit);
        }
        return result;
    }

    public ListProduit filtrerByProfil(String profile) // renvoi tous les  produits qui concerne une categorie
    {
        ListProduit result = new ListProduit();

        for (Produit produit : this) {
            if (produit.getProfil().equals(profile) || profile.equals("")) result.add(produit);
        }
        return result;
    }

    public ListProduit filtrerByPrixMoreThen(float prix)// renvoi tous les  produit qui ont un produit.prix > prix
    {
        ListProduit result = new ListProduit();

        for (Produit produit : this) {
            if (produit.getPrix() > prix) result.add(produit);
        }
        return result;
    }


    //Getter And Setter
    public LinkedList<String> getListCategories() {
        return listCategories;
    }

    public void setListCategories(LinkedList<String> listCategories) {
        this.listCategories = listCategories;
    }
}
