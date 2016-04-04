package dz.esi.commerce.mshop;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import dz.esi.commerce.mshop.fragements.DetailProduitFragment;
import dz.esi.commerce.mshop.metier_commerce.Produit;

public class DetailProduitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produit);

        //car dans le RES c'est un fragment layout
        DetailProduitFragment detailFragment = new DetailProduitFragment();

        Intent intent = getIntent();
          Produit produit = (Produit)intent.getSerializableExtra("produit");
        Bundle bundle = new Bundle();
        bundle.putSerializable("produit",produit);

        detailFragment.setArguments(bundle);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutdetail, detailFragment);
        ft.commit();


    }
}
