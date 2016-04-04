package dz.esi.commerce.mshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

import Librerie.PagerTransformation.CubeInTransformer;
import dz.esi.commerce.mshop.fragements.FragementListProduit;
import dz.esi.commerce.mshop.fragements.FragementTabed;
import dz.esi.commerce.mshop.metier_commerce.ListProduit;
import dz.esi.commerce.mshop.metier_commerce.Panier;
import dz.esi.commerce.mshop.metier_commerce.Produit;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    FragementTabed fragementTabed; // un fragement quiva  s'ocupper de l'affichage sous forme de section (Pages)
                                    //Selon le profil choisit il va renvoyer un fragement pour Afficher les produits
    ViewPager mViewPager;   //Contenaire
    int curentPos = 0; //Indique la page cournte de mViewPager
    int curentCategorie=0;
    Menu myMenu;
          //Le Menu qui contient le Sppiner catégories
    LinkedList<String> listCategories; //Table des Catégories (il sera utiliser l'ors de la création de menu (spinner Catégories))
    String [] listProfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


            ///       Partie Initialisation

            //Préparation de l'activity (chargement XML ...)



            super.onCreate(savedInstanceState);
            //barre d'outil
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            //Menu Drawer
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            //Debut de Notre Programme
            /*
            *   Le Principe est :
            *   1- d'utiliser un Fragment "FragementTabed" extends FragmentPagerAdapter pour afficher les profils("tous","Homme","Femme" ..) comme des pages
            *   2- utiliser un tableau de FragmentListProduit pour l'affichage des produits par profil + filtre par categorie
             * */

            listProfil =getResources().getStringArray(R.array.profils);
            mViewPager = (ViewPager) findViewById(R.id.productContainer);

            if(savedInstanceState != null)
            {
                Panier panier =(Panier) savedInstanceState.getSerializable("Panier");
                fragementTabed = new FragementTabed(getSupportFragmentManager(),listProfil , this);
                fragementTabed.setPanier(panier);
             //   fragementTabed.UpdateListFromPanier(panier);
            }
            else
            {
                FragementListProduit.listStaticProduit = new ListProduit();
                FragementListProduit.ReadProductList(); // Import resource (Liste des Produits )

                fragementTabed = new FragementTabed(getSupportFragmentManager(),listProfil, this);

                mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {//*On changement de page (profil)
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        setCurentPos(position);
                        Toast.makeText(MainActivity.this, listProfil[getCurentPos()], Toast.LENGTH_SHORT).show();

                        if (fragementTabed != null) {
                            fragementTabed.setCurentPosition(position);
                        }
                        if (curentCategorie == 0) {
                            fragementTabed.showProductOfCategories("");
                        } else {
                            fragementTabed.showProductOfCategories(listCategories.get(curentCategorie));
                        }

                        ///////////////////////////////////////Added to Product Item
                      /*  for (int i=0; i < fragementTabed.getFragmentsByProfil().length;i++) {
            fragementTabed.getFragmentsByProfil()[i].getListview().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), "Helloooo" , Toast.LENGTH_LONG);
                }
            });}*/

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });


            }


            // Set up the ViewPager with the sections adapter.
            mViewPager.setAdapter(fragementTabed);
          //  mViewPager.setPageTransformer(true, new CubeInTransformer()); //Animation



            listCategories = FragementListProduit.listStaticProduit.getListCategories();


            ///Panier  ///

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(), ActivityPanier.class);
                    intent.putExtra("Panier", fragementTabed.getPanier());
                    startActivity(intent);
                }
            });
    }


    public void produit_Show_Details(View view) //On Item click
                                                //lorsqu'on click sur un Produit
    {

       Produit produit = new Produit();

        TextView prodId = (TextView)view.findViewById(R.id.productId);
        int id = Integer.parseInt(prodId.getText().toString());
        produit = fragementTabed.findProduct(id);


        Intent intent = new Intent(view.getContext(), DetailProduitActivity.class);
        intent.putExtra("produit",produit);
        startActivity(intent);

    }

    public int getPositionOfviewOnclick(View view)
    {
        ListView listView =(ListView)view.getParent().getParent();
        return listView.getPositionForView(view);
    }

    public void product_Is_checked(View view) throws Exception //lorsqu'Un produit est séléctioné
    {
        int id=0;
        CheckBox checkBox = (CheckBox) view;
        View item = (View) view.getParent();

        TextView txID = (TextView)item.findViewById(R.id.productId);

        id = Integer.parseInt(txID.getText().toString()) ;

        Produit produit= fragementTabed.findProduct(id);


       if(checkBox.isChecked())
            {
                fragementTabed.addProduct(id,curentPos);

            }
            else {
              //  Produit p = fragementTabed.removeProduct(pos,curentPos);

            }
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        this.myMenu = menu;
        ReadListCategories(menu, listCategories );

        return true;
    }

    public void ReadListCategories(Menu menu, LinkedList<String> listCategories ) //cette fonction Affiche au liveau de menu , la liste des categories
    {
          if(!listCategories.get(0).equals("Tous"))  {
              listCategories.addFirst("Tous");
          }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listCategories.toArray());

        MenuItem item = menu.findItem(R.id.spinner);

        Spinner spinner = (Spinner) item.getActionView();
        spinner.setAdapter(adapter);
        spinner.setSelection(curentCategorie);

        if (curentCategorie == 0) fragementTabed.showProductOfCategories("");
        else fragementTabed.showProductOfCategories(listCategories.get(curentCategorie));

        //chamgement de categorie dans le Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) fragementTabed.showProductOfCategories("");
                else fragementTabed.showProductOfCategories(parent.getAdapter().getItem(position).toString());

                curentCategorie = position;


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            Intent intent = new Intent( getApplicationContext() , ActivityVide.class);
            startActivity(intent);

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public int getCurentPos() {
        return curentPos;
    }

    public void setCurentPos(int curentPos) {
        this.curentPos = curentPos;
        mViewPager.setCurrentItem(curentPos);

    }
    public void setCurentCategrorie(int curentCategrorie) {
        this.curentCategorie = curentCategrorie;
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curentPos", curentPos);

        outState.putInt("curentCategrorie",curentCategorie);
        outState.putSerializable("Panier", fragementTabed.getPanier());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null)
        {
       /*     int curentPos = savedInstanceState.getInt("curentPos");
            int curentCategrorie = savedInstanceState.getInt("curentCategrorie");
            setCurentPos(curentPos);
            setCurentCategrorie(curentCategrorie);*/
        }


    }


}
