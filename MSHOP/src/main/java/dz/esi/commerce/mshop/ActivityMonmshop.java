package dz.esi.commerce.mshop;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityMonmshop extends ListActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monmshop);
        // storing string resources into Array

        String[] adobe_products = getResources().getStringArray(R.array.information);

        // Binding Array to ListAdapter
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, adobe_products));

        ListView lv = getListView();

        // listening to single list item on click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                CallFunc(position);


            }

        });}
    private void CallFunc(int position) {
        Intent intent = null ;
        switch (position) {
            case 0:
                intent = new Intent(getApplicationContext() , Donnee_personelles_Activity.class);
                break;
            case 1 :
                intent = new Intent(getApplicationContext() , MesCommandesActivity.class);
                break;
            case 2 :
                intent = new Intent(getApplicationContext() , MesAchatsActivity.class);
                break;
            case 3:
                intent = new Intent(getApplicationContext() , MainActivity.class);
                break;

        }
        if(intent != null)
            startActivity(intent);
    }
}
