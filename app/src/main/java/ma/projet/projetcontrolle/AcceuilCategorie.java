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
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class AcceuilCategorie extends AppCompatActivity {

    Button btnCree,btnList,btnBack;

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
        setContentView(R.layout.activity_acceuil_categorie);

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
                        Intent intenth = new Intent(AcceuilCategorie.this,MainActivity.class);
                        startActivity(intenth);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_client:
                        Intent intentcl = new Intent(AcceuilCategorie.this,AcceuilClient.class);
                        startActivity(intentcl);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_categorie:
                        Intent intentcat = new Intent(AcceuilCategorie.this,AcceuilCategorie.class);
                        startActivity(intentcat);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_produit:
                        Intent intentp = new Intent(AcceuilCategorie.this,AcceuilProduit.class);
                        startActivity(intentp);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_vente:
                        Intent intentv = new Intent(AcceuilCategorie.this,ListeProduitDispo.class);
                        startActivity(intentv);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_credit:
                        Intent intentcr = new Intent(AcceuilCategorie.this,ListeClientCredit.class);
                        startActivity(intentcr);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_statistique:
                        Intent intents = new Intent(AcceuilCategorie.this,BarChart.class);
                        startActivity(intents);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });

        btnCree = findViewById(R.id.btnCree);
        btnList = findViewById(R.id.btnList);
        btnBack = findViewById(R.id.btnBack);

        btnCree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AcceuilCategorie.this,CreateCategorie.class);
                startActivity(intent);
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AcceuilCategorie.this,ListeCategorie.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AcceuilCategorie.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}