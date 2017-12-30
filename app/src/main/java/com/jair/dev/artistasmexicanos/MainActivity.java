package com.jair.dev.artistasmexicanos;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Avoid taking screenshot
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }
        setContentView(R.layout.activity_main);

        //When launch for the first time
        Fragment fragment = new Fragment();
        fragment = new pinturaFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment).commit();

        initElements();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Hide menu when an option is tapped
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);

                drawerLayout.closeDrawers();

                Fragment fragment = new Fragment();
                switch (item.getItemId()){
                    case R.id.pintura:
                        Log.i("item","Pintura");
                        fragment = new pinturaFragment();
                        break;
                    case R.id.escultura:
                        Log.i("item","Escultura");
                        fragment = new esculturaFragment();
                        break;
                    case R.id.literatura:
                        Log.i("item","Literatura");
                        fragment = new literaturaFragment();
                        break;
                    case R.id.cine:
                        Log.i("item","Cine");
                        fragment = new cineFragment();
                        break;
                    case R.id.teatro:
                        Log.i("item","Teatro");
                        fragment = new teatroFragment();
                        break;
                    case R.id.arquitectura:
                        Log.i("item","Arquitectura");
                        fragment = new arquitecturaFragment();
                        break;

                } getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment).commit();
                return true;
            }
        });


    }

    public void initElements() {

        //Set painting Fragment as the main fragment
        //getSupportFragmentManager().beginTransaction().replace(R.id.drawer, pinturaFragment).commit();

        //Action when tapping an element in the left menu
        navigationView = (NavigationView)findViewById(R.id.navigationview);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);


        //show and hide the menu when tapping the menu icon on actionbar
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.abierto,R.string.cerrado);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
