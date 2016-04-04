package dz.esi.commerce.mshop.metier_commerce;

import java.io.Serializable;

/**
 * Created by Fatteh on 3/14/2016.
 */
public class Produit implements Serializable, Comparable<Produit>{

    private int id ;
    private String nomProduit;
    private float prix;
    private String constrecteur; //Entreprise de fabrication
    private String dateFabrication;
    private String Description;
    private String categorie;
    private String profil; //pour femme , hommes , enfants ....

    private int iconDrawable;
    private int[] imagesDrawable; // on va utiliser une liste pour afficher plusieures images de produit

    private Boolean ischecked = false;


    public Produit() {
        initialiserParDefeaut(); // pour inisialiser les parametres (images par defeaut ....)
    }

    public Produit(int id ,String nomProduit, float prix) {
        this.id=id;
        this.nomProduit = nomProduit;
        this.prix = prix;
    }

    public Produit(int id, String nomProduit, float prix, String constrecteur, String dateFabrication, String description, String categorie,String profil,int iconDrawable, int[] imagesDrawable) {
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.constrecteur = constrecteur;
        this.dateFabrication = dateFabrication;
        Description = description;
        this.categorie = categorie;
        this.iconDrawable = iconDrawable;
        this.imagesDrawable = imagesDrawable;
        this.profil = profil;
        this.id=id;
    }

    private void initialiserParDefeaut() { // pour inisialiser les parametres (images par defeaut ....)
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Produit another) {
        switch (Panier.sortedBy) {
            case "Nom":
                return this.nomProduit.compareTo(another.getNomProduit());
            case "Prix":
                return (int) (this.prix - another.getPrix());
            case "Constricteur":
                return this.constrecteur.compareTo(another.getConstrecteur());
            case "Categorie":
                return this.categorie.compareTo(another.getCategorie());
        }
        String id = nomProduit+constrecteur+categorie+prix;
       return id.compareTo(another.getNomProduit() + another.getConstrecteur() + another.getCategorie() + another.getPrix() );

    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Produit) {
            return ((Produit) o).getNomProduit().equals(this.nomProduit);
        } else return super.equals(o);
    }


//Getter And Setter

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getConstrecteur() {
        return constrecteur;
    }

    public void setConstrecteur(String constrecteur) {
        this.constrecteur = constrecteur;
    }

    public String getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(String dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getIconDrawable() {
        return iconDrawable;
    }

    public void setIconDrawable(int iconDrawable) {
        this.iconDrawable = iconDrawable;
    }

    public int[] getImagesDrawable() {
        return imagesDrawable;
    }

    public void setImagesDrawable(int[] imagesDrawable) {
        this.imagesDrawable = imagesDrawable;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public String getProfil() {
        return profil;
    }

    public Boolean getIschecked() {
        return ischecked;
    }

    public void setIschecked(Boolean ischecked) {
        this.ischecked = ischecked;
    }
}
