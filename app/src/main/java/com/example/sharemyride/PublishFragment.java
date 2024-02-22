package com.example.sharemyride;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PublishFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PublishFragment extends Fragment {

    EditText publish_from,publish_to,publish_person,publish_time,publish_name;
    Button publish_btn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_FROM = "from";
    private static final String KEY_TO = "to";
    private static final String KEY_PERSON = "person";
    private static final String KEY_TIME = "time";
    private static final String KEY_NAME = "name";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PublishFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PublishFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PublishFragment newInstance(String param1, String param2) {
        PublishFragment fragment = new PublishFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_publish, container, false);

        publish_from = rootview.findViewById(R.id.publish_from);
        publish_to = rootview.findViewById(R.id.publish_to);
        publish_person = rootview.findViewById(R.id.publish_person);
        publish_time = rootview.findViewById(R.id.publish_time);
        publish_name = rootview.findViewById(R.id.publish_name);
        publish_btn = rootview.findViewById(R.id.publish_btn);

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        publish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String from = publish_from.getText().toString();
                String to = publish_to.getText().toString();
                String person = publish_person.getText().toString();
                String time = publish_time.getText().toString();
                String name = publish_name.getText().toString();

                firebaseDatabase = FirebaseDatabase.getInstance();
                reference = firebaseDatabase.getReference("available_ride");

                availableRide storedata = new availableRide(from,to,person,time,name);

                reference.child(name).setValue(storedata);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_FROM,from);
                editor.putString(KEY_TO,to);
                editor.putString(KEY_PERSON,person);
                editor.putString(KEY_TIME,time);
                editor.putString(KEY_NAME,name);
                editor.apply();

                Toast.makeText(getActivity(), "Publish Successfull!", Toast.LENGTH_SHORT).show();
            }
        });

        return rootview;
    }
}