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
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import ma.projet.projetcontrolle.bean.Client;
import ma.projet.projetcontrolle.controller.Helper;

public class DetailClient extends AppCompatActivity {

    EditText nom,prenom,age,cin;
    String id,sexe;
    Button edit,delete;
    Helper h = new Helper(DetailClient.this);

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
        setContentView(R.layout.activity_detail_client);

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
                        Intent intenth = new Intent(DetailClient.this,MainActivity.class);
                        startActivity(intenth);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_client:
                        Intent intentcl = new Intent(DetailClient.this,AcceuilClient.class);
                        startActivity(intentcl);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_categorie:
                        Intent intentcat = new Intent(DetailClient.this,AcceuilCategorie.class);
                        startActivity(intentcat);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_produit:
                        Intent intentp = new Intent(DetailClient.this,AcceuilProduit.class);
                        startActivity(intentp);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_vente:
                        Intent intentv = new Intent(DetailClient.this,ListeProduitDispo.class);
                        startActivity(intentv);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_credit:
                        Intent intentcr = new Intent(DetailClient.this,ListeClientCredit.class);
                        startActivity(intentcr);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_statistique:
                        Intent intents = new Intent(DetailClient.this,BarChart.class);
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
        edit = findViewById(R.id.edit);
        delete = findViewById(R.id.delete);

        id=getIntent().getStringExtra("id");
        Client c=h.findClientById(Integer.parseInt(id));
        nom.setText(c.getNom());
        prenom.setText(c.getPrenom());
        age.setText(String.valueOf(c.getAge()));
        cin.setText(c.getCin());
        sexe = c.getSexe();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client c = new Client();
                c.setId(Integer.parseInt(id));
                c.setNom(nom.getText().toString());
                c.setPrenom(prenom.getText().toString());
                c.setCin(cin.getText().toString());
                c.setAge(Integer.parseInt(age.getText().toString()));
                c.setSexe(sexe);
                h.updateClient(c);
                Intent intent = new Intent(DetailClient.this,ListeClient.class);
                startActivity(intent);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                h.deleteClient(Integer.parseInt(id));
                Intent intent = new Intent(DetailClient.this,ListeClient.class);
                startActivity(intent);
            }
        });
    }
}