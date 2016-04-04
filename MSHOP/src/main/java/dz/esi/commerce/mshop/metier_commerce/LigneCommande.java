package dz.esi.commerce.mshop.metier_commerce;

import java.io.Serializable;

/**
 * Created by Fatteh on 3/14/2016.
 */
public class LigneCommande implements Comparable<LigneCommande>,Serializable {
    private Produit produit;
    private int quantite =0;
    private float montant =0; //montant total de la commande = (Quantite * prix )


    public LigneCommande(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
        this.montant = quantite * produit.getPrix();
    }

    public int ajouterNproduit(int n) // une methode qui augmante la quantité demander avec n nouveau produit (produit en plus )
    {
        this.quantite += n;
        montant += produit.getPrix() * n; // recalculer le montant total
        return this.quantite;
    }

    public int decrementeQuantite() throws Exception // une methode qui demenue la quantité demander (produit en - )
    {
        if (this.quantite - 1 >= 0) // si il ya un demande deja !!! (pour eviter que la quantité recoi une valeur negative
        {
            this.quantite--;
            montant -= produit.getPrix() * 1; // recalculer le montant total
            return this.quantite;
        }
        throw new Exception("la Quantite est null");

    }

    public int decrementeQuantite(int quantite)  // une methode qui demenue la quantité demander N fois (quantiti = quaniti - N )
    {
        if (this.quantite - quantite >= 0) // si il ya un demande deja !!! (pour eviter que la quantité recoi une valeur negative
            {
                if(this.quantite - quantite == 0)
                {
                    return 0;
                }
            this.quantite -= quantite;
            montant -= produit.getPrix() * quantite; // recalculer le montant total
            return this.quantite;
        }
        else return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof LigneCommande) {
            return ((LigneCommande) o).getProduit().equals(this.produit);
        } else return super.equals(o);
    }


    //Geter And Setter

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getMontant() {
        return montant;
    }

    @Override
    public int compareTo(LigneCommande another) {
        return this.produit.compareTo(another.produit);
    }
}
