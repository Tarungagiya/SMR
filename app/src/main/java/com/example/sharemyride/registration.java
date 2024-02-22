package com.example.sharemyride;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registration extends AppCompatActivity {

    EditText nemail, npassword,nname,nmobile_number;
    Button nregister;
    TextView redirect_login;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nemail = (EditText) findViewById(R.id.profile_email);
        npassword = (EditText) findViewById(R.id.profile_password);
        nname = (EditText) findViewById(R.id.profile_name);
        nmobile_number = (EditText) findViewById(R.id.profile_mobileno);
        nregister = (Button) findViewById(R.id.register_btn);
        redirect_login = findViewById(R.id.redirect_login);

        redirect_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });

        nregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = nemail.getText().toString();
                String password = npassword.getText().toString();
                String name = nname.getText().toString();
                String mobile_number = nmobile_number.getText().toString();

                if (!email.isEmpty()){
                    nemail.setError(null);
                    nemail.setEnabled(false);
                    if (!password.isEmpty()){
                        npassword.setError(null);
                        npassword.setEnabled(false);
                        if (!name.isEmpty()){
                            nname.setError(null);
                            nname.setEnabled(false);
                            if (!mobile_number.isEmpty()){
                                nmobile_number.setError(null);
                                nmobile_number.setEnabled(false);

                                firebaseDatabase = FirebaseDatabase.getInstance();
                                reference = firebaseDatabase.getReference("user");

                                String email_s = nemail.getText().toString();
                                String password_s = npassword.getText().toString();
                                String name_s = nname.getText().toString();
                                String mobile_number_s = nmobile_number.getText().toString();

                                storedata storedatass = new storedata(name_s,mobile_number_s,email_s,password_s);

                                reference.child(name_s).setValue(storedatass);

                                Toast.makeText(getApplicationContext(),"Register successfully",Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(registration.this,login.class);
                                startActivity(intent);
                                finish();

                            } else {
                                nmobile_number.setError("please enter the mobile number");
                            }

                        } else {
                            nname.setError("please enter the name");
                        }

                    } else {
                        npassword.setError("please enter the password");
                    }

                } else {
                    nemail.setError("please enter the email");
                }

            }
        });

    }
}