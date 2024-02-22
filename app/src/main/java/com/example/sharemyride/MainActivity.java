package com.example.sharemyride;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharemyride.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        LoginSuccessfulFragment loginSuccessfulFragment = new LoginSuccessfulFragment();
        loginSuccessfulFragment.show(getSupportFragmentManager(),loginSuccessfulFragment.getTag());

        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (itemId == R.id.profile) {
                replaceFragment(new ProfileFragment());
            } else if (itemId == R.id.search) {
                replaceFragment(new SearchFragment());
            } else if (itemId == R.id.publish) {
                replaceFragment(new PublishFragment());
            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main,fragment);
        fragmentTransaction.commit();
    }

    public void personal_details(View view){

        PersonalDetailsFragment personalDetailsFragment = new PersonalDetailsFragment();
        personalDetailsFragment.show(getSupportFragmentManager(), personalDetailsFragment.getTag());

    }
    public void change_password(View view){

        ChangePasswordFragment changePasswordFragment = new ChangePasswordFragment();
        changePasswordFragment.show(getSupportFragmentManager(), changePasswordFragment.getTag());

    }

}