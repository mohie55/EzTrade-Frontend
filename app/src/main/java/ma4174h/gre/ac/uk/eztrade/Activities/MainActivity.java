package ma4174h.gre.ac.uk.eztrade.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Map;

import ma4174h.gre.ac.uk.eztrade.Fragments.AddListingFragment;
import ma4174h.gre.ac.uk.eztrade.Fragments.MapFragment;
import ma4174h.gre.ac.uk.eztrade.Models.User;
import ma4174h.gre.ac.uk.eztrade.R;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FrameLayout fragmentContainer;
    private BottomNavigationView bottomNavigationView;

//    Gson gson = new Gson();
//    String json = mPrefs.getString("MyObject", "");
//    MyObject obj = gson.fromJson(json, MyObject.class);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container_main);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");






        FloatingActionButton fab = findViewById(R.id.fabAddListing);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open add listing fragment
                AddListingFragment addListingFragment = new AddListingFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_main,addListingFragment).commit();
            }
        });


            Fragment mapFragment = new MapFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_main,mapFragment).commit();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.nav_home:
                selectedFragment = new MapFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_main,selectedFragment).commit();
                break;
            case R.id.nav_messages:

                break;
            case R.id.fabAddListing:
                selectedFragment = new AddListingFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_main,selectedFragment).commit();
            break;
            case R.id.nav_alerts:

                break;
            case R.id.nav_account:

                break;

        }
        if (selectedFragment != null) {

        }

        return true;
    }
}