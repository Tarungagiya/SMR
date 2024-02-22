package com.example.sharemyride;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    EditText nemail, npassword,nname,nmobile_number;
    Button nlogin;
    public static  String PREFS_NAME = "myprefsfile";
    public void reg_page(View v){
        Intent intent  = new Intent(getApplicationContext(), registration.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nemail = findViewById(R.id.login_email);
        npassword = findViewById(R.id.login_password);
        nlogin = findViewById(R.id.login_btn);

        SharedPreferences sharedPreferences = getSharedPreferences(login.PREFS_NAME,0);

        nlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putBoolean("hasloggedin",true);
                editor.commit();

                String email = nemail.getText().toString();
                String password = npassword.getText().toString();

                if (!email.isEmpty()){
                    nemail.setError(null);
                    nemail.setEnabled(false);
                    if (!password.isEmpty()){
                        npassword.setError(null);
                        npassword.setEnabled(false);

                        final String email_data = nemail.getText().toString();
                        final String password_data = npassword.getText().toString();

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference("user");

                        Query check_email = databaseReference.orderByChild("name").equalTo(email_data);

                        check_email.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if (snapshot.exists()){
                                    nemail.setError(null);
                                    nemail.setEnabled(false);
                                    String passwordckeck = snapshot.child(email_data).child("password").getValue(String.class);
                                    if (passwordckeck.equals(password_data)){
                                        npassword.setError(null);
                                        npassword.setEnabled(false);
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        npassword.setError("wrong password");
                                    }

                                } else {
                                    nemail.setError("user does not exits");
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    } else {
                        npassword.setError("please enter the password");
                    }

                }else {
                    nemail.setError("please enter the email");
                }

            }
        });
    }
}