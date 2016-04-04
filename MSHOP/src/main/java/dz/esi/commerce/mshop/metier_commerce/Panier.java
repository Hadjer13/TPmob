package dz.esi.commerce.mshop.metier_commerce;


import java.io.Serializable;
import java.util.LinkedList;


/**
 * Created by Fatteh on 3/14/2016.
 */
public class Panier implements Serializable{


    public static String sortedBy; // Une variable qui indique la maniere utiliser pour ordonner les Produit
    //par nom , ou par prix , ou par categorie ....
    // cette variable va etre utiliser par la methode compareTO


    private float montant; // la somme total des produit de panier = Somme(prix1 + prix2 + prix3 + prix1 + prix4 + ...)
    private LinkedList<LigneCommande> listCommande;



    public Panier() {
        montant = 0;
        listCommande = new LinkedList<LigneCommande>();
    }

    public LigneCommande getLigneDeProduit(Produit produit) {
        LigneCommande ligne = new LigneCommande(produit, 0);
        int position = listCommande.indexOf(ligne);
        if (position >= 0) return listCommande.get(position);
        return null;

    }

    public void addProduct(Produit produit) // ajouter un produit au panier
    {
        LigneCommande ligne = new LigneCommande(produit, 1);
        addLigneCommande(ligne);
    }

    public void addLigneCommande(LigneCommande ligneCommande) // ajouter une commande
    {
        LigneCommande ligne = getLigneDeProduit(ligneCommande.getProduit());
        if (ligne != null) //si le produit existe deja (on a deja une commande sur ce produit
        {

            ligne.ajouterNproduit(ligneCommande.getQuantite()); //on ajout la quantite de commande
            montant+= ligneCommande.getMontant() ;
        } else //sinon
        {
            listCommande.add(ligneCommande);
            montant+=ligneCommande.getMontant();
        }
    }

    public void addProduct(int pos) {
        Produit p =getProduit(pos);
        addProduct(p);
    }
    public Produit getProduit (int pos)
    {
        return getLigneDeCommande(pos).getProduit();
    }

    public int decrementeQuantiteCommande(Produit produit, int quantiteDeminition)  // une methode qui demenue la quantité demander d'une commande(produit en - )
    {
        LigneCommande ligne = getLigneDeProduit(produit);
        int n= ligne.decrementeQuantite(quantiteDeminition);
        if(n==0) listCommande.remove(ligne);
        montant-= produit.getPrix() * quantiteDeminition;

        return n;

    }
    public void deleteProduit(int pos) {
        LigneCommande ligne =listCommande.get(pos);
        montant-=ligne.getMontant();
        listCommande.remove(pos);

    }
    public int decrementeQuantiteCommande(int pos, int quantiteDeminition)
    {
        return decrementeQuantiteCommande(getProduit(pos),quantiteDeminition);
    }

    public LinkedList<LigneCommande> filtrerByCategorie(String categorie) // renvoi tous les ligne de produit qui concerne une categorie
    {
        LinkedList<LigneCommande> result = new LinkedList<LigneCommande>();

        for (LigneCommande ligne : listCommande) {
            if (ligne.getProduit().getCategorie().equals(categorie)) result.add(ligne);
        }
        return result;
    }

    public LinkedList<LigneCommande> filtrerByMontatMoreThen(float montant)// renvoi tous les ligne de produit qui ont un montatn > montant
    {
        LinkedList<LigneCommande> result = new LinkedList<LigneCommande>();

        for (LigneCommande ligne : listCommande) {
            if (ligne.getMontant() > montant) result.add(ligne);
        }
        return result;
    }


    public int getcount() {
        return listCommande.size();
    }

    public LinkedList<LigneCommande> getListCommande() {
        return listCommande;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public void setListCommande(LinkedList<LigneCommande> listCommande) {
        this.listCommande = listCommande;
    }

    public LigneCommande getLigneDeCommande(int position) {
        return listCommande.get(position);
    }



}
