package dz.esi.commerce.mshop.fragements;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;

import dz.esi.commerce.mshop.Adaptateur.CutomAdapter;
import dz.esi.commerce.mshop.MainActivity;
import dz.esi.commerce.mshop.R;
import dz.esi.commerce.mshop.metier_commerce.ListProduit;
import dz.esi.commerce.mshop.metier_commerce.Produit;


public class FragementListProduit extends Fragment implements Serializable{

    public static ListProduit listStaticProduit ; //une liste partager contiens tous les produit

    ListProduit listcProduitDeProfile; // une liste concerne l'instance de fragment , elle contient la liste des produit selon le  Profile
    CutomAdapter adapter;
    ListView listview;
    String typeProfile="";


    public FragementListProduit() {
        listcProduitDeProfile = new ListProduit();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        typeProfile = bundle.getString("typeProfile");

        if(listStaticProduit!=null) listcProduitDeProfile = listStaticProduit.filtrerByProfil(typeProfile); // Récupérer la list de ce Profile

        View v = inflater.inflate(R.layout.list_ptoduit_fragment, null);
        listview = (ListView) v.findViewById(R.id.productsList);
        adapter = new CutomAdapter(inflater.getContext(), listcProduitDeProfile);

        listview.setAdapter(adapter);

        //Set Title Page
        //Ajouter le Listner
        //On click -> Afficher le détail



        return v;
    }


    public static FragementListProduit newInstance(String _typeProfile) {

        //Le Role de cette fonction est de générer un fragement selon le type de Profil (Tous ,Homme, Enfant , Femme)
        // elle sera utilisé par le FragmentTabed.Fragment getItem(int position) ,lors de changement de profil

        //il exite une autre solution , il s'agait d'utiliser les Filtres une autres fois , pour filtrer les produit selon le type de Profil !!!!!!!!!!!!!!!!!!!!!!!!

        FragementListProduit fragment = new FragementListProduit();
        Bundle bundle = new Bundle();
        bundle.putSerializable("typeProfile",  _typeProfile);
        fragment.setArguments(bundle);

        return fragment;
    }


    public void showProductOfCategories(String categorie) {
        if(adapter!=null)     adapter.getFilter().filter(categorie);
    }



    public static  void ReadProductList() {

        listStaticProduit = new ListProduit();
        Produit p1 = new Produit(1,"Vêtement1", 1500, "Condor", "2016", "", "vêtements","Enfant" ,R.drawable.ic_evet1, null);
        Produit p2 = new Produit(2,"Vêtement2", 4500, "Boss", "2016", "", "vêtements", "Enfant" ,R.drawable.ic_evet2, null);
        Produit p3 = new Produit(3,"Vêtement3", 500, "Gussi", "2016", "", "vêtements", "Enfant" ,R.drawable.ic_evet3, null);
        Produit p4 = new Produit(4,"Vêtement4", 2500, "Gavana", "2016", "", "vêtements", "Enfant" ,R.drawable.ic_evet4, null);
        Produit p5 = new Produit(5,"Vêtement5", 5500, "Adidass", "2016", "", "vêtements", "Enfant",R.drawable.ic_evet5, null);
        Produit p6 = new Produit(6,"Vêtement6", 7500, "Nike", "2016", "", "vêtements", "Enfant",R.drawable.ic_evet6, null);
        Produit p7 = new Produit(7,"Vêtement7", 9500, "Boss", "2016", "", "vêtements", "Enfant",R.drawable.ic_evet7, null);
        Produit p8 = new Produit(8,"Vêtement8", 9500, "Boss", "2016", "", "vêtements", "Enfant",R.drawable.ic_evet8, null);

        Produit p11 = new Produit(9,"Vêtement9", 95200, "Adidass", "2016", "", "vêtements","Femme", R.drawable.ic_fvet1, null);
        Produit p12 = new Produit(10,"Vêtement10", 96200, "Nike", "2016", "", "vêtements","Femme", R.drawable.ic_fvet2, null);
        Produit p13 = new Produit(11,"Vêtement11", 95200, "puma", "2016", "", "vêtements","Femme", R.drawable.ic_fvet3, null);
        Produit p14 = new Produit(12,"Vêtement12", 95200, "Zibra", "2016", "", "vêtements","Femme", R.drawable.ic_fvet4, null);
        Produit p15 = new Produit(13,"Vêtement13", 95200, "Romio", "2016", "", "vêtements", "Femme",R.drawable.ic_fvet5, null);
        Produit p16 = new Produit(14,"Vêtement14", 95200, "Adidass", "2016", "", "vêtements", "Femme",R.drawable.ic_fvet6, null);
        Produit p17 = new Produit(15,"Vêtement15", 95200, "Romio", "2016", "", "vêtements", "Femme",R.drawable.ic_fvet7, null);
        Produit p18 = new Produit(16,"Vêtement16", 95200, "Adidass", "2016", "", "vêtements", "Femme",R.drawable.ic_fvet8, null);

        Produit p21 = new Produit(17,"Vêtement17", 95200, "Paulo", "2016", "", "vêtements","Homme", R.drawable.ic_hvet1, null);
        Produit p22 = new Produit(18,"Vêtement18", 95200, "Adidass", "2016", "", "vêtements","Homme", R.drawable.ic_hvet11, null);
        Produit p23 = new Produit(19,"Vêtement20", 95200, "Adidass", "2016", "", "vêtements","Homme", R.drawable.ic_hvet2, null);
        Produit p24 = new Produit(20,"Vêtement21", 95200, "Adidass", "2016", "", "vêtements","Homme", R.drawable.ic_hvet3, null);
        Produit p25 = new Produit(21,"Vêtement22", 95200, "Adidass", "2016", "", "vêtements","Homme", R.drawable.ic_hvet4, null);
        Produit p26 = new Produit(22,"Vêtement23", 95200, "Adidass", "2016", "", "vêtements","Homme", R.drawable.ic_hvet5, null);
        Produit p27 = new Produit(23,"Vêtement24", 95200, "Adidass", "2016", "", "vêtements","Homme", R.drawable.ic_hvet6, null);

        listStaticProduit.add(p1);
        listStaticProduit.add(p2);
        listStaticProduit.add(p3);
        listStaticProduit.add(p4);
        listStaticProduit.add(p5);
        listStaticProduit.add(p6);
        listStaticProduit.add(p7);
        listStaticProduit.add(p8);

        listStaticProduit.add(p11);
        listStaticProduit.add(p12);
        listStaticProduit.add(p13);
        listStaticProduit.add(p14);
        listStaticProduit.add(p15);
        listStaticProduit.add(p16);
        listStaticProduit.add(p17);
        listStaticProduit.add(p18);

        listStaticProduit.add(p21);
        listStaticProduit.add(p22);
        listStaticProduit.add(p23);
        listStaticProduit.add(p24);
        listStaticProduit.add(p25);
        listStaticProduit.add(p26);
        listStaticProduit.add(p27);

         p1 = new Produit(25,"Chaussure1", 1500, "Condor", "2016", "", "chaussures","Enfant" ,R.drawable.ic_echau1, null);
         p2 = new Produit(26,"Chaussure2", 4500, "Boss", "2016", "", "chaussures", "Enfant" ,R.drawable.ic_echau2, null);
         p3 = new Produit(27,"Chaussure3", 500, "Gutchi", "2016", "", "chaussures", "Enfant" ,R.drawable.ic_echau3, null);
         p4 = new Produit(28,"Chaussure4", 2500, "Gavana", "2016", "", "chaussures", "Enfant" ,R.drawable.ic_echau4, null);
         p5 = new Produit(29,"Chaussure5", 5500, "Adidass", "2016", "", "chaussures", "Enfant",R.drawable.ic_echau5, null);
         p6 = new Produit(30,"Chaussure6", 7500, "Nike", "2016", "", "chaussures", "Enfant",R.drawable.ic_echau6, null);

         p11 = new Produit(31,"Chaussure7", 95200, "Adidass", "2016", "", "chaussures","Femme", R.drawable.ic_fchau1, null);
         p12 = new Produit(32,"Chaussure8", 96200, "Nike", "2016", "", "chaussures","Femme", R.drawable.ic_fchau2, null);
         p13 = new Produit(33,"Chaussure9", 95200, "puma", "2016", "", "chaussures","Femme", R.drawable.ic_fchau3, null);
         p14 = new Produit(34,"Chaussure10", 95200, "Zibra", "2016", "", "chaussures","Femme", R.drawable.ic_fchau4, null);
         p15 = new Produit(35,"Chaussure11", 95200, "Romio", "2016", "", "chaussures", "Femme",R.drawable.ic_fchau5, null);
         p16 = new Produit(36,"Chaussure12", 95200, "Adidass", "2016", "", "chaussures", "Femme",R.drawable.ic_fchau6, null);

         p21 = new Produit(37,"Chaussure13", 95200, "Adidass", "2016", "", "chaussures","Homme", R.drawable.ic_hchau1, null);
         p22 = new Produit(38,"Chaussure14", 95200, "Adidass", "2016", "", "chaussures","Homme", R.drawable.ic_hchau2, null);
         p23 = new Produit(39,"Chaussure15", 95200, "Adidass", "2016", "", "chaussures","Homme", R.drawable.ic_hchau3, null);
         p24 = new Produit(40,"Chaussure16", 95200, "Adidass", "2016", "", "chaussures","Homme", R.drawable.ic_hchau4, null);
         p25 = new Produit(41,"Chaussure17", 95200, "Adidass", "2016", "", "chaussures","Homme", R.drawable.ic_hchau5, null);

        listStaticProduit.add(p1);
        listStaticProduit.add(p2);
        listStaticProduit.add(p3);
        listStaticProduit.add(p4);
        listStaticProduit.add(p5);
        listStaticProduit.add(p6);
        listStaticProduit.add(p7);
        listStaticProduit.add(p8);

        listStaticProduit.add(p11);
        listStaticProduit.add(p12);
        listStaticProduit.add(p13);
        listStaticProduit.add(p14);
        listStaticProduit.add(p15);
        listStaticProduit.add(p16);

        listStaticProduit.add(p21);
        listStaticProduit.add(p22);
        listStaticProduit.add(p23);
        listStaticProduit.add(p24);
        listStaticProduit.add(p25);

        p1 = new Produit(44,"Accessoire1", 1500, "Condor", "2016", "", "accessoires","Enfant" ,R.drawable.ic_echapeau1, null);
        p2 = new Produit(45,"Accessoire2", 4500, "Boss", "2016", "", "accessoires", "Enfant" ,R.drawable.ic_echapeau2, null);
        p3 = new Produit(46,"Accessoire3", 500, "Gutchi", "2016", "", "accessoires", "Enfant" ,R.drawable.ic_echapeau3, null);
        p4 = new Produit(47,"Accessoire4", 2500, "Gavana", "2016", "", "accessoires", "Enfant" ,R.drawable.ic_echapeau4, null);
        p5 = new Produit(48,"Accessoire5", 5500, "Adidass", "2016", "", "accessoires", "Enfant",R.drawable.ic_ecar1, null);
        p6 = new Produit(49,"Accessoire6", 7500, "Nike", "2016", "", "accessoires", "Enfant",R.drawable.ic_ecar2, null);

        p11 = new Produit(50,"Accessoire7", 95200, "Adidass", "2016", "", "accessoires","Femme", R.drawable.ic_facc1, null);
        p12 = new Produit(51,"Accessoire8", 96200, "Nike", "2016", "", "accessoires","Femme", R.drawable.ic_facc2, null);
        p13 = new Produit(52,"Accessoire9", 95200, "puma", "2016", "", "accessoires","Femme", R.drawable.ic_facc3, null);
        p14 = new Produit(53,"Accessoire10", 95200, "Zibra", "2016", "", "accessoires","Femme", R.drawable.ic_fchal1, null);
        p15 = new Produit(54,"Accessoire11", 95200, "Romio", "2016", "", "accessoires", "Femme",R.drawable.ic_fchal2, null);
        p16 = new Produit(55,"Accessoire12", 95200, "Adidass", "2016", "", "accessoires", "Femme",R.drawable.ic_fchapeau1, null);

        p21 = new Produit(56,"Accessoire13", 95200, "Adidass", "2016", "", "accessoires","Homme", R.drawable.ic_hacc1, null);
        p22 = new Produit(57,"Accessoire14", 95200, "Adidass", "2016", "", "accessoires","Homme", R.drawable.ic_hacc2, null);
        p23 = new Produit(58,"Accessoire15", 95200, "Adidass", "2016", "", "accessoires","Homme", R.drawable.ic_hacc3, null);
        p24 = new Produit(59,"Accessoire16", 95200, "Adidass", "2016", "", "accessoires","Homme", R.drawable.ic_hchapeau1, null);
        p25 = new Produit(60,"Accessoire17", 95200, "Adidass", "2016", "", "accessoires","Homme", R.drawable.ic_hchapeau2, null);
        p26 = new Produit(61,"Accessoire18", 95200, "Adidass", "2016", "", "accessoires","Homme", R.drawable.ic_hacc4, null);

        listStaticProduit.add(p1);
        listStaticProduit.add(p2);
        listStaticProduit.add(p3);
        listStaticProduit.add(p4);
        listStaticProduit.add(p5);
        listStaticProduit.add(p6);

        listStaticProduit.add(p11);
        listStaticProduit.add(p12);
        listStaticProduit.add(p13);
        listStaticProduit.add(p14);
        listStaticProduit.add(p15);
        listStaticProduit.add(p16);

        listStaticProduit.add(p21);
        listStaticProduit.add(p22);
        listStaticProduit.add(p23);
        listStaticProduit.add(p24);
        listStaticProduit.add(p25);
        listStaticProduit.add(p26);
    }


    //geteer And Setter

    public CutomAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(CutomAdapter adapter) {
        this.adapter = adapter;
    }

    public ListProduit getListcProduitDeProfile() {
        return listcProduitDeProfile;
    }

    public void setListcProduitDeProfile(ListProduit listcProduitDeProfile) {
        this.listcProduitDeProfile = listcProduitDeProfile;

    }

    public ListView getListview() {
        return listview;
    }

    public void setListview(ListView listview) {
        this.listview = listview;
    }

    public Produit getProduit(int pos) {
        return listcProduitDeProfile.get(pos);
    }


}
