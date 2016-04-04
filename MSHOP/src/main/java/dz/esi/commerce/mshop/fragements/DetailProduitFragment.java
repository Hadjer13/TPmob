package dz.esi.commerce.mshop.fragements;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dz.esi.commerce.mshop.R;
import dz.esi.commerce.mshop.metier_commerce.Produit;

public class DetailProduitFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_detail_produit, null);

        Bundle bundle = getArguments();
        if (bundle!=null) {
            //recuperer champs details
            TextView nom = (TextView)view.findViewById(R.id.nomp);
            TextView prix = (TextView)view.findViewById(R.id.prixp);
            TextView marque = (TextView)view.findViewById(R.id.marquep);
            TextView profil = (TextView)view.findViewById(R.id.profilp);
            TextView categorie = (TextView)view.findViewById(R.id.categoriep);

            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);


            Produit produit = (Produit)bundle.getSerializable("produit");
            System.out.print("\n \n \n NOM:"+produit.getNomProduit()+"\n PRIX"+produit.getPrix()+
                    "\n marque"+produit.getConstrecteur()+"\n POUR"+produit.getProfil()+"\n CATEGORIE"+produit.getCategorie());
            nom.setText("NOM : "+produit.getNomProduit());
            prix.setText("PRIX : "+produit.getPrix());
            marque.setText("MARQUE : "+produit.getConstrecteur());
            profil.setText("POUR : "+produit.getProfil());
            categorie.setText("CATEGORIE : "+produit.getCategorie());
           imageView.setImageResource(produit.getIconDrawable());
        }

        return view;
    }

}
