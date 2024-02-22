package com.example.sharemyride;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class YourPublishedRideShow extends AppCompatActivity {

    TextView text_time,text_from,text_to,text_person,text_name;
    Button delete_btn;
    LinearLayout your_item_main;
    SharedPreferences sharedPreferences;
    DatabaseReference databaseReference;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_FROM = "from";
    private static final String KEY_TO = "to";
    private static final String KEY_PERSON = "person";
    private static final String KEY_TIME = "time";
    private static final String KEY_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_published_ride_show);

        text_time = findViewById(R.id.your_ride_show_time);
        text_from = findViewById(R.id.your_ride_show_from);
        text_to = findViewById(R.id.your_ride_show_to);
        text_person = findViewById(R.id.your_ride_show_person);
        text_name = findViewById(R.id.your_ride_show_name);
        delete_btn = findViewById(R.id.delete_btn);
        your_item_main = findViewById(R.id.your_item_main);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String time = sharedPreferences.getString(KEY_TIME,null);
        String from = sharedPreferences.getString(KEY_FROM,null);
        String to = sharedPreferences.getString(KEY_TO,null);
        String person = sharedPreferences.getString(KEY_PERSON,null);
        String name = sharedPreferences.getString(KEY_NAME,null);


        text_time.setText(time);
        text_from.setText(from);
        text_to.setText(to);
        text_person.setText(person);
        text_name.setText(name);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("available_ride");

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(name).removeValue();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(YourPublishedRideShow.this, "Delete successfull", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        if (time == null){
            your_item_main.removeAllViews();
        }

    }
}