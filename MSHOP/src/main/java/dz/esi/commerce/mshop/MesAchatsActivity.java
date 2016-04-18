package dz.esi.commerce.mshop;

import android.app.Activity;
import android.app.AlertDialog;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MesAchatsActivity extends Activity {
    ListView listView;

    /*********************************/
    private ListView maListViewPerso;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_achats);

        //Récupération de la listview créée dans le fichier main.xml
        maListViewPerso = (ListView) findViewById(R.id.listviewperso);

        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;
/******************************************************************************************/
        //Création d'une HashMap pour insérer les informations du premier item de notre listView
        map = new HashMap<String, String>();
        //on insère un élément titre que l'on récupérera dans le textView titre créé dans le fichier affichageitem.xml
        map.put("titre", "Chaussure Homme");
        //on insère un élément description que l'on récupérera dans le textView description créé dans le fichier affichageitem.xml
        map.put("description", "Constructeur :Adidass 2016   Prix :1500.00DA");
        //on insère la référence à l'image (convertit en String car normalement c'est un int) que l'on récupérera dans l'imageView créé dans le fichier affichageitem.xml
        map.put("img", String.valueOf(R.drawable.ic_fchau1));
        //enfin on ajoute cette hashMap dans la arrayList
        listItem.add(map);

        //On refait la manip plusieurs fois avec des données différentes pour former les items de notre ListView
/**************************************************************************************************/
        map = new HashMap<String, String>();
        map.put("titre",  "Accessoire Homme");
        map.put("description", "Constructeur :Adidass 2016   Prix :1000.00DA  ");
        map.put("img", String.valueOf(  R.drawable.ic_hacc2));
        listItem.add(map);
/**************************************************************************************************/
        map = new HashMap<String, String>();
        map.put("titre", "Accessoire Homme");
        map.put("description", "Constructeur :Adidass 2016   Prix :1500.00DA");
        map.put("img", String.valueOf(R.drawable.ic_hacc3));
        listItem.add(map);
/*****************************************************************************************************/
        map = new HashMap<String, String>();
        map.put("titre", "Chaussure Femme");
        map.put("description","Constructeur :Femme,Nike 2016   Prix :3000.00DA");
        map.put("img", String.valueOf( R.drawable.ic_fchau2));
        listItem.add(map);
/********************************************************************************************************/
        map = new HashMap<String, String>();
        map.put("titre",  "Vêtement Enfant");
        map.put("description",  "Constructeur :Gussi 2016   Prix :1200.00DA");
        map.put("img", String.valueOf(R.drawable.ic_evet3));
        listItem.add(map);

/*****************************************************************************************************/


  /**************************************************************************************************/


        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.list_achat,
                new String[] {"img", "titre", "description"}, new int[] {R.id.img, R.id.titre, R.id.description});

        //On attribut à notre listView l'adapter que l'on vient de créer
        maListViewPerso.setAdapter(mSchedule);
        //Enfin on met un écouteur d'évènement sur notre listView


    }




    }


