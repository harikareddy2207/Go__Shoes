package com.example.goshoes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nav;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        firebaseAuth = FirebaseAuth.getInstance();

        nav = findViewById(R.id.nv);
        dl = findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this,dl, R.string.nav_drawer_open, R.string.nav_drawer_close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new MenFragment()).commit();
        }

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId())
                {
                    case R.id.Men :
                        fragment = new MenFragment();
                        break;

                    case R.id.Women :
                        fragment = new WomenFragment();
                        break;

                    case R.id.Kids :
                        fragment = new KidsFragment();
                        break;

                    case R.id.Profile :
                        fragment = new ProfileFragmet();
                        break;

                    case R.id.Logout :

                        firebaseAuth.signOut();
                        finish();
                        Intent intent = new Intent(HomePage.this, MainActivity.class);
                        startActivity(intent);
                        break;


                    default:
                        fragment = new MenFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();



                dl.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (t.onOptionsItemSelected(item))
        {
            return true;
        }


        int id = item.getItemId();

        switch (id)
        {

            case R.id.cart:

                startActivity(new Intent(HomePage.this,MyCartPage.class));
                break;
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }
}
