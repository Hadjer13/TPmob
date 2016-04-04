package dz.esi.commerce.mshop.Adaptateur;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

import dz.esi.commerce.mshop.R;
import dz.esi.commerce.mshop.metier_commerce.*;

public class CutomAdapter extends BaseAdapter implements Filterable,Serializable{
    private Context context;
    private ListProduit produitList;

    //      Pour le fitre
    private ValueFilter valueFilter;
    private ListProduit mFilterList;

    public CutomAdapter(Context context, ListProduit list) {
        this.context = context;


        this.produitList = list;
        //     créer une copie de ListProduit pour le filtre
        this.mFilterList = list;
    }

    @Override
    public int getCount() {
        return produitList.size();
    }

    @Override
    public Object getItem(int position) {
        return produitList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // mettre le layout une seule fois lorsque convertView est null
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.shower_produit_, null);
        }
        ImageView productIcon = (ImageView) convertView.findViewById(R.id.productIcon);
        TextView productTitle = (TextView) convertView.findViewById(R.id.productName);
        TextView productCategorie = (TextView) convertView.findViewById(R.id.productCategorie);
        TextView productConstructor = (TextView) convertView.findViewById(R.id.productConstructor);
        TextView productPrix = (TextView) convertView.findViewById(R.id.productPrix);
        TextView productProfil = (TextView) convertView.findViewById(R.id.profil);
        TextView productID  = (TextView) convertView.findViewById(R.id.productId);

        CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.checkBox);

        productIcon.setImageResource(produitList.get(position).getIconDrawable());

        Produit p =produitList.get(position);
        productTitle.setText(p.getNomProduit());
        productProfil.setText(p.getProfil());
        productCategorie.setText(p.getCategorie());
        productConstructor.setText("Constructeur: " + p.getConstrecteur());
        productPrix.setText("" + p.getPrix() + " DA");
        productID.setText(""+p.getId());

         checkBox.setChecked(p.getIschecked());


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


    // Une nouvelle classe de type Filter est définie
    // Quand on va instancier ValueFilter, deux méthodes sont appelées :  : performFiltering et publishResults

    private class ValueFilter extends Filter {

        // cette méthode effecute le filtre sur la liste des livres
        // une copie de cette liste mFilterList est utilisée

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ListProduit listFilter = new ListProduit();
            FilterResults filterResults = new FilterResults();
            // véirifer si le texte n'est pas vide
            if (constraint != null && constraint.length() > 0 && !constraint.equals("Tous")) {
                for (Produit produit : mFilterList) {
                    if (produit.getCategorie().equals(constraint)) listFilter.add(produit);
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
            produitList = (ListProduit) results.values;
            notifyDataSetChanged();

        }

    }
}
