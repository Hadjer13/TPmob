package dz.esi.commerce.mshop.fragements;

import android.app.Application;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import dz.esi.commerce.mshop.R;
import dz.esi.commerce.mshop.metier_commerce.ListProduit;
import dz.esi.commerce.mshop.metier_commerce.Panier;
import dz.esi.commerce.mshop.metier_commerce.Produit;


/**
 * Created by Fatteh on 3/17/2016.
 */

//Ce Fragement sera utilisé pour assurer la Navigation en Tabe (Tous ,Homme, Enfant , Femme)
//Il Renvoi a chqaue fois un fragement de type FragementListProduit Selon le Profil

public class FragementTabed extends FragmentPagerAdapter {

    String[] listProfil;
    FragementListProduit[]  fragmentsByProfil; // une map conteinet tous les fragement utilisé par ce module
    Panier panier;
    int curentPosition=0;
    Context context ;

    public FragementTabed(FragmentManager fm , String[] listProfil, Context context) {
        super(fm);
        this.listProfil =   listProfil;
        panier = new Panier();
        fragmentsByProfil = new FragementListProduit[getCount()];
        this.context = context;
    }
    @Override
    public Fragment getItem(int position) {

        if(fragmentsByProfil[position] == null)
        {
            if(position ==0 )
            { //profils tous
                FragementListProduit fragementListProduit = FragementListProduit.newInstance("");
                fragmentsByProfil[position]=fragementListProduit;

                return fragementListProduit;
            }
            FragementListProduit fragementListProduit = FragementListProduit.newInstance(listProfil[position]);
            fragmentsByProfil[position]=fragementListProduit;


            return fragementListProduit;
        }

      return fragmentsByProfil[position];

    }
    //Reteurn Le Titre
    @Override
    public CharSequence getPageTitle(int position) {
        return listProfil[position];
    }
    @Override
    public int getCount() {
      return listProfil.length;
    }

    public void showProductOfCategories(String s) {
        if(fragmentsByProfil !=null)
        {
            for(int profil =0 ; profil <fragmentsByProfil.length ; profil ++)
            {
                if(fragmentsByProfil[profil] !=null)   {
                    fragmentsByProfil[profil].showProductOfCategories(s);
                    System.out.print("C'est pas nul i="+profil);
                }


            }
        }

    }
    public ListProduit getListProduit(int profil) {
        if(fragmentsByProfil !=null)
        {
            if(fragmentsByProfil[profil] !=null) return   fragmentsByProfil[profil].getListcProduitDeProfile();
        }
        return null;
    }

    public Produit findProduct(int id){
        Produit produit = new Produit();
        for (int i = 0 ; i<FragementListProduit.listStaticProduit.size();i++)
        {
            if(FragementListProduit.listStaticProduit.get(i).getId()==id){
                produit = FragementListProduit.listStaticProduit.get(i);
            }
        }
        if(produit!=null){
            System.out.print("\n \n \n NOM:"+produit.getNomProduit()+"\n PRIX"+produit.getPrix()+
                    "\n marque"+produit.getConstrecteur()+"\n POUR"+produit.getProfil()+"\n CATEGORIE"+produit.getCategorie());

        }
        else {
            System.out.print("NULL");
        }
        return  produit ;
    }

    /**
     * retourne la position du produit dans la static liste
     * @param id
     * @return
     */
    public int findPosProduct(int id){
        int pos=0;

        for (int i = 0 ; i<FragementListProduit.listStaticProduit.size();i++)
        {
            if(FragementListProduit.listStaticProduit.get(i).getId()==id){
                pos = i;
            }
        }

        return  pos ;
    }

    public void addProduct(Produit produit) {
        panier.addProduct(produit);
        produit.setIschecked(true);

    }
    public void decrementeQuantiteProduit(Produit produit , int quantite) throws Exception {

        panier.decrementeQuantiteCommande(produit, quantite);

    }

    /**
     * on enleve la position et on utilise le ID
     * rechrcher par ID dans la static list + modifier is checked
     * ajouter le produit concerne au panier
     */
    public Produit addProduct(int id , int profil) {

        if(fragmentsByProfil !=null)
        {
            if(fragmentsByProfil[profil] !=null)
            {
                int pos = findPosProduct(id);


                Produit produit = FragementListProduit.listStaticProduit.get(pos);
                //modif dans static liste
                FragementListProduit.listStaticProduit.get(pos).setIschecked(true);

                addProduct(produit);
                return produit;
            }
        }
        return null;
    }


    public Produit removeProduct(int pos , int profil) throws Exception {

        if(fragmentsByProfil !=null)
        {
            if(fragmentsByProfil[profil] !=null)
            {
                Produit produit = fragmentsByProfil[profil].getProduit(pos);
                decrementeQuantiteProduit(produit,1);
                produit.setIschecked(false);
            }
        }


        return null;
    }
    //getters And Setters
    public Panier getPanier() {
        return panier;
    }
    public FragementListProduit[] getFragmentsByProfil() {
        return fragmentsByProfil;
    }
    public String[] getListProfil() {
        return listProfil;
    }
    public void setListProfil(String[] listProfil) {
        this.listProfil = listProfil;
    }
    public void setFragmentsByProfil(FragementListProduit[] fragmentsByProfil) {
        this.fragmentsByProfil = fragmentsByProfil;
    }
    public void setPanier(Panier panier) {
        this.panier = panier;
    }
    public FragementListProduit getFragmentByPos(int pos)
    {
        return fragmentsByProfil[pos];
    }
    public int getCurentPosition() {
        return curentPosition;
    }
    public void setCurentPosition(int curentPosition) {
        this.curentPosition = curentPosition;
    }
    public void UpdateListFromPanier(Panier panier) {

        /* mise a jour de la liste des produits partager pour assurer que les produit seront les mm
     <-> les produit sléction dans le panier -> isChecked = True ...
                                       */
        for (Produit produit :FragementListProduit.listStaticProduit)
        {
            if(panier.getLigneDeProduit(produit)!=null) produit.setIschecked(true);
            else produit.setIschecked(false);
        }
    }

}
