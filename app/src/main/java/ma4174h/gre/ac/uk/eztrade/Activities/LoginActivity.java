package ma4174h.gre.ac.uk.eztrade.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import ma4174h.gre.ac.uk.eztrade.Adapters.LoginAdapter;
import ma4174h.gre.ac.uk.eztrade.R;

import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Register"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

         LoginAdapter loginAdapter = new LoginAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
         viewPager.setAdapter(loginAdapter);
//        viewPager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        tabLayout.setTranslationY(300);
        tabLayout.setAlpha(0);
        tabLayout.animate().translationY(0).alpha(100).setDuration(1000).setStartDelay(800).start();

    }

}
//        call.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                if(!response.isSuccessful()) {
//                    System.out.println(response.code());
//                    return;
//                }
//                response.body()
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//                System.out.println(t.getMessage());
//            }
//        });
