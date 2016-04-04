package dz.esi.commerce.mshop;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import dz.esi.commerce.mshop.fragements.FragementPanier;
import dz.esi.commerce.mshop.metier_commerce.Panier;

public class ActivityPanier extends AppCompatActivity {

    Panier panier;
    FragementPanier fragemetPanier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        Intent intent = getIntent();
        Bundle bundle = new Bundle();


        bundle.putSerializable("Panier", (Panier) intent.getSerializableExtra("Panier"));
        panier = (Panier) intent.getSerializableExtra("Panier");
         fragemetPanier = new FragementPanier();
        fragemetPanier.setArguments(bundle);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.panierShower, fragemetPanier);
        ft.commit();
    }

    public void decrementeProduit(View view)
    {
        int pos= getPositionOfviewOnclick(view);//position de Produit dans la list
        panier.decrementeQuantiteCommande(pos, 1); //Decrementer la quantite
        updatePanier(pos);
    }

    public void addQuantite(View view)
    {

        int pos= getPositionOfviewOnclick (view);//position de Produit dans la list
        panier.addProduct(pos); //Augmenter La quantite
        updatePanier(pos);

    }
    public void deleteProduit(View view)
    {
        int pos= getPositionOfviewOnclick (view);//position de Produit dans la list
        panier.deleteProduit(pos);
        updatePanier(pos);
    }

    public int getPositionOfviewOnclick(View view)
    {
        ListView listView =(ListView)view.getParent().getParent();
        return listView.getPositionForView(view);
    }


    public void updatePanier(int pos) //le role de cette fonction est : synchronizer les changement effectuer et reaficher la Panier
    {
        fragemetPanier.updatePanier(pos);
    }




}
