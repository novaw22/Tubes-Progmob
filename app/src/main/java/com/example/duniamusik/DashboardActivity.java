package com.example.duniamusik;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.duniamusik.databinding.ActivityDashboardBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {
    ActivityDashboardBinding binding;
    BottomNavigationView navbar;
    String username, email, phone;
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dashboard);
        navbar =  findViewById(R.id.bottomNavigationView);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        replaceFragment(new HomeFragment(), false);
        extras = getIntent().getExtras();
        if (extras!=null){
            username = extras.getString("username");
            email = extras.getString("email");
            phone = extras.getString("phone");
        }

        navbar.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment(), false);
                    break;
                case R.id.setting:
                    replaceFragment(new SettingFragment(), false);
                    break  ;
                case R.id.profile:
                    replaceFragment(new ProfileFragment(), true);
                    break;
            }
            return true;
        });

        binding.bottomNavigationView.setSelectedItemId(R.id.home);
    }

    public void replaceFragment(Fragment fragment,Boolean send_arguments){
        if(send_arguments){
            Bundle bundle = new Bundle();
            bundle.putString("username", username);
            bundle.putString("email", email);
            bundle.putString("phone", phone);
            fragment.setArguments(bundle);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}