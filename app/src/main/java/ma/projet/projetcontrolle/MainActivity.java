package ma.projet.projetcontrolle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    CardView client,categorie,produit,vente,credit,statistique;

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
        setContentView(R.layout.activity_main);

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
                        Intent intenth = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intenth);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_client:
                        Intent intentcl = new Intent(MainActivity.this,AcceuilClient.class);
                        startActivity(intentcl);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_categorie:
                        Intent intentcat = new Intent(MainActivity.this,AcceuilCategorie.class);
                        startActivity(intentcat);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_produit:
                        Intent intentp = new Intent(MainActivity.this,AcceuilProduit.class);
                        startActivity(intentp);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_vente:
                        Intent intentv = new Intent(MainActivity.this,ListeProduitDispo.class);
                        startActivity(intentv);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_credit:
                        Intent intentcr = new Intent(MainActivity.this,ListeClientCredit.class);
                        startActivity(intentcr);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_statistique:
                        Intent intents = new Intent(MainActivity.this,BarChart.class);
                        startActivity(intents);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });



        client = findViewById(R.id.iclient);
        categorie = findViewById(R.id.icategorie);
        produit = findViewById(R.id.iproduit);
        vente = findViewById(R.id.ivente);
        credit = findViewById(R.id.icredit);
        statistique = findViewById(R.id.istatistique);

        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AcceuilClient.class);
                startActivity(intent);
            }
        });

        categorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AcceuilCategorie.class);
                startActivity(intent);
            }
        });

        produit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AcceuilProduit.class);
                startActivity(intent);
            }
        });

        vente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListeProduitDispo.class);
                startActivity(intent);
            }
        });

        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListeClientCredit.class);
                startActivity(intent);
            }
        });

        statistique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,BarChart.class);
                startActivity(intent);
            }
        });
    }
}