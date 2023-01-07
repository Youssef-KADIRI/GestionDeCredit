package ma.projet.projetcontrolle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import ma.projet.projetcontrolle.bean.Categorie;
import ma.projet.projetcontrolle.bean.Client;
import ma.projet.projetcontrolle.bean.Produit;
import ma.projet.projetcontrolle.controller.Helper;

public class DetailProduitVente extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView nom,prix;
    String id;
    Spinner spincl;
    int paye,id_client,id_categorie;
    RadioGroup payeGroup;
    Button edit;
    Helper h = new Helper(DetailProduitVente.this);
    ArrayList<Client> cl;
    ArrayList<String> clnom;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produit_vente);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.menu_Open,R.string.close_menu);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent intenth = new Intent(DetailProduitVente.this,MainActivity.class);
                        startActivity(intenth);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_client:
                        Intent intentcl = new Intent(DetailProduitVente.this,AcceuilClient.class);
                        startActivity(intentcl);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_categorie:
                        Intent intentcat = new Intent(DetailProduitVente.this,AcceuilCategorie.class);
                        startActivity(intentcat);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_produit:
                        Intent intentp = new Intent(DetailProduitVente.this,AcceuilProduit.class);
                        startActivity(intentp);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_vente:
                        Intent intentv = new Intent(DetailProduitVente.this,ListeProduitDispo.class);
                        startActivity(intentv);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_credit:
                        Intent intentcr = new Intent(DetailProduitVente.this,ListeClientCredit.class);
                        startActivity(intentcr);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_statistique:
                        Intent intents = new Intent(DetailProduitVente.this,BarChart.class);
                        startActivity(intents);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });

        nom = findViewById(R.id.nom);
        prix = findViewById(R.id.prix);
        edit = findViewById(R.id.edit);
        payeGroup = (RadioGroup) findViewById(R.id.payeGroup);
        payeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.paye1:
                        paye = 1;
                        break;
                    case R.id.paye0:
                        paye = 0;
                        break;
                }
            }
        });
        spincl = (Spinner) findViewById(R.id.spincl);
        spincl.setOnItemSelectedListener(this);
        cl = h.getSpinnerClient();
        clnom = new ArrayList<>();
        for(int i = 0 ; i < cl.size(); i++){
            clnom.add(cl.get(i).getNom());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, clnom);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spincl.setAdapter(dataAdapter);
        id=getIntent().getStringExtra("id");
        Produit p=h.findProduitById(Integer.parseInt(id));
        nom.setText(p.getNom());
        prix.setText(String.valueOf(p.getPrix()));
        id_categorie = p.getId_categorie();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Produit p = new Produit();
                p.setId(Integer.parseInt(id));
                p.setNom(nom.getText().toString());
                p.setPrix(Double.parseDouble(prix.getText().toString()));
                p.setPaye(paye);
                p.setId_categorie(id_categorie);
                p.setId_client(id_client);
                h.updateProduit(p);
                Intent intent = new Intent(DetailProduitVente.this,ListeProduitDispo.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        id_client = cl.get(i).getId();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}