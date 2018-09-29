package waka.wakamarket.wakamarket;
import android.*;
import android.Manifest;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vistrav.ask.Ask;

import java.util.ArrayList;
import java.util.List;

import jonathanfinerty.once.Once;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private android.support.v7.widget.SearchView searchView;
    DatabaseReference mfirebase;

    final List<Pizza> pizzas = new ArrayList<>();
    String phone;
    String address;
    TextView user;
   PizzaAdapter pAdapter = new PizzaAdapter(pizzas, MainActivity.this);

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        Intent reprice = getIntent();
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        mfirebase = FirebaseDatabase.getInstance().getReference("wakamarket101");
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        final LinearLayout content = findViewById(R.id.content);
        final TextView textView = (TextView) findViewById(R.id.username);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.username);
        ImageView navIma = (ImageView) headerView.findViewById(R.id.choosedp);
        String pic=reprice.getStringExtra("uri");
        if (TextUtils.isEmpty(pic)) {
           navIma.setImageResource(R.drawable.banner);
        }
        else{
            navIma.setImageURI(Uri.parse(pic));
        }


        TextView num = (TextView) headerView.findViewById(R.id.textView);

        final String user=reprice.getStringExtra("username");
        final String number=reprice.getStringExtra("number");
        navUsername.setText(user);
        num.setText(number);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));

        final String usern;
        Ask.on(this)
                .id(123) // in case you are invoking multiple time Ask from same activity or fagment
                .forPermissions(android.Manifest.permission.CAMERA, Manifest.permission.INTERNET
                        , android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withRationales("permission needed for Camera"
                        ,"Internet permission is needed",
                        "In order to save file you will need to grant storage permission") //optional
                .go();


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close) {
            private float scaleFactor = 6f;

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float slideX = drawerView.getWidth() * slideOffset;
                content.setTranslationX(slideX);
                content.setScaleX(1 - (slideOffset / scaleFactor));
                content.setScaleY(1 - (slideOffset / scaleFactor));
            }
        };


        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.setDrawerElevation(0f);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);



    }


    private void ShowFragment(int itemId) {

        Fragment fragment = null;

        switch (itemId) {
            case R.id.nav_camera:
               fragment= new first();

            getSupportActionBar().setTitle("Chat4Meal"
            );
            break;
            case R.id.nav_gallery:
                fragment = new Second();
                getSupportActionBar().setTitle("My Account"
                        );

                break;
            case R.id.nav_slideshow:
                fragment = new ThirdFragment();
                getSupportActionBar().setTitle("Favourite");
                break;
            case R.id.set:
                fragment = new Settings();
                getSupportActionBar().setTitle("Settings" +
                        "");
                break;



        }


        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
    }

        // Associate searchable configuration with the SearchView

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       return true;

    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        ShowFragment(item.getItemId());
        return true;
    }


    @Override
    public void onBackPressed() {
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:


                final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

                drawerLayout.openDrawer(GravityCompat.START);
                return true;

        }
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);


    }






}

