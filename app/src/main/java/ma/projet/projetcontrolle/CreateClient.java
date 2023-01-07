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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import ma.projet.projetcontrolle.bean.Client;
import ma.projet.projetcontrolle.controller.Helper;

public class CreateClient extends AppCompatActivity {

    EditText nom,prenom,age,cin;
    String sexe;
    RadioGroup sexeGroup;
    Button btnInsert;
    Helper h = new Helper(CreateClient.this);

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
        setContentView(R.layout.activity_create_client);

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
                        Intent intenth = new Intent(CreateClient.this,MainActivity.class);
                        startActivity(intenth);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_client:
                        Intent intentcl = new Intent(CreateClient.this,AcceuilClient.class);
                        startActivity(intentcl);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_categorie:
                        Intent intentcat = new Intent(CreateClient.this,AcceuilCategorie.class);
                        startActivity(intentcat);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_produit:
                        Intent intentp = new Intent(CreateClient.this,AcceuilProduit.class);
                        startActivity(intentp);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_vente:
                        Intent intentv = new Intent(CreateClient.this,ListeProduitDispo.class);
                        startActivity(intentv);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_credit:
                        Intent intentcr = new Intent(CreateClient.this,ListeClientCredit.class);
                        startActivity(intentcr);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_statistique:
                        Intent intents = new Intent(CreateClient.this,BarChart.class);
                        startActivity(intents);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        age = findViewById(R.id.age);
        cin = findViewById(R.id.cin);
        sexeGroup = (RadioGroup) findViewById(R.id.sexeGroup);
        sexeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.homme:
                        sexe = "Homme";
                        break;
                    case R.id.femme:
                        sexe = "Femme";
                        break;
                }
            }
        });
        btnInsert = findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client c = new Client();
                c.setNom(nom.getText().toString());
                c.setPrenom(prenom.getText().toString());
                c.setSexe(sexe);
                c.setAge(Integer.parseInt(age.getText().toString()));
                c.setCin(cin.getText().toString());
                h.insertClient(c);
                Intent intent = new Intent(CreateClient.this,AcceuilClient.class);
                startActivity(intent);
            }
        });
    }
}