package dz.esi.commerce.mshop.fragements;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


import dz.esi.commerce.mshop.Adaptateur.PanierAdapter;
import dz.esi.commerce.mshop.R;
import dz.esi.commerce.mshop.metier_commerce.Panier;

/**
 * Created by Fatteh on 3/19/2016.
 */
public class FragementPanier extends Fragment {

    PanierAdapter adapter;
    Context con;
    Panier panier;
    TextView somme; // pour afficher le montant


    public PanierAdapter getAdapter() {
        return adapter;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.panier_fragment, null);
        ListView list = (ListView) v.findViewById(R.id.listCommande);
        Bundle bundle = getArguments();
        if (bundle!=null) {
            panier = (Panier) bundle.getSerializable("Panier"); //Récupération du panier  (From ActivityPanier )
        }
        else {
            panier = new Panier();
        }
         adapter = new PanierAdapter(inflater.getContext(),panier);
        list.setAdapter(adapter);
        somme = (TextView) v.findViewById(R.id.PanierSomme);
        somme.setText(""+panier.getMontant()+" DA");
        return v;
    }


    public Context getCon() {
        return con;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setCon(Context con) {
        this.con = con;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public void updatePanier(int pos) {
        adapter.update(pos);
        somme.setText(""+panier.getMontant()+" DA");
    }
}
