package dz.esi.commerce.mshop.Adaptateur;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

import dz.esi.commerce.mshop.R;
import dz.esi.commerce.mshop.metier_commerce.LigneCommande;
import dz.esi.commerce.mshop.metier_commerce.Panier;
import dz.esi.commerce.mshop.metier_commerce.Produit;

public class PanierAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private Panier panier;
    //      Pour le fitre
    private ValueFilter valueFilter;

    private LinkedList<LigneCommande> mFilterList;
    private LinkedList<LigneCommande> listCommande;

    public PanierAdapter(Context context, Panier panier) {
        this.context = context;


        this.panier = panier;
        //     créer une copie de ListProduit pour le filtre
        this.mFilterList = panier.getListCommande();
        listCommande = mFilterList;
    }

    @Override
    public int getCount() {
        return listCommande.size();
    }

    @Override
    public Object getItem(int position) {
        return listCommande.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // mettre le layout une seule fois lorsque convertView est null
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.shower_commande, null);
        }
        ImageView productIcon = (ImageView) convertView.findViewById(R.id.productIcon);
        TextView productTitle = (TextView) convertView.findViewById(R.id.productName);

        TextView productPrix = (TextView) convertView.findViewById(R.id.productPrix);
        NumberPicker d = (NumberPicker) convertView.findViewById(R.id.numberPicker);

        d.setMaxValue(10);
        d.setMinValue(0);

        Produit p =listCommande.get(position).getProduit();
        productIcon.setImageResource(p.getIconDrawable());

        productTitle.setText(p.getNomProduit());
        productPrix.setText("" + p.getPrix() + " DA");
        d.setValue(listCommande.get(position).getQuantite());

        //for the number picker
        d.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(oldVal<newVal){

                }else{

                }
            }
        });

        return convertView;
    }


    @Override
    public Filter getFilter() {
        // La méthode retourne un objet de type Filter
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    public void update(int pos) {
     //   getView(pos,null,null);
         notifyDataSetChanged();
    }


    // Une nouvelle classe de type Filter est définie
    // Quand on va instancier ValueFilter, deux méthodes sont appelées :  : performFiltering et publishResults

    private class ValueFilter extends Filter {

        // cette méthode effecute le filtre sur la liste des livres
        // une copie de cette liste mFilterList est utilisée

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            LinkedList<LigneCommande> listFilter = new LinkedList<LigneCommande>();
            FilterResults filterResults = new FilterResults();
            // véirifer si le texte n'est pas vide
            if (constraint != null && constraint.length() > 0 && !constraint.equals("Tous")) {
                for (LigneCommande ligne : mFilterList) {
                    if (ligne.getProduit().getCategorie().equals(constraint)) listFilter.add(ligne);
                }
                filterResults.count = listFilter.size();
                filterResults.values = listFilter;
            } else {
                filterResults.count = mFilterList.size();
                filterResults.values = mFilterList;
            }
            return filterResults;
        }

        // Cette méthode permet d'afficher la nouvelle listView en appelant notifyDataSetChanged()
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listCommande = (LinkedList<LigneCommande>) results.values;
            notifyDataSetChanged();

        }

    }
}
