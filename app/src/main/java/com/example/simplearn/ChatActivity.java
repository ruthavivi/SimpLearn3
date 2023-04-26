package com.example.simplearn;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ChatActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TabLayout tabLayout;
    TabItem mchat, mcall, mstatus,msongs,marticle,mgames;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    androidx.appcompat.widget.Toolbar mtoolbar;
     Spinner spinner;
    Button button;

    FirebaseAuth firebaseAuth;


    FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);

        tabLayout = findViewById(R.id.include);
        spinner = (Spinner) findViewById(R.id.spinner);
        mchat = findViewById(R.id.chat);
        //mcall = findViewById(R.id.calls);
        //mstatus = findViewById(R.id.status);
        msongs=findViewById(R.id.songs);
        marticle=findViewById(R.id.article);
        mgames=findViewById(R.id.games);
        viewPager = findViewById(R.id.fragmentcontainer);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();


        mtoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);




        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_more_vert_24);
        mtoolbar.setOverflowIcon(drawable);



        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        button=(Button)findViewById(R.id.button10);

        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("English");
        categories.add("Hebrew");
        categories.add("Spanish");
        categories.add("Arabic");
        categories.add("russian");
        categories.add("chinese");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2) {
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String learnlanguage = String.valueOf(spinner.getSelectedItem());

                HashMap<String,Object> updateValues = new HashMap<>();
                updateValues.put("learnlanguage",learnlanguage);
                saveUserDetails(updateValues);
            }
        });


    }

    private void saveUserDetails( HashMap<String,Object> updateValues) {

        FirebaseFirestore.getInstance().collection("Users")
                .document(FirebaseAuth.getInstance().getUid())
                .update(updateValues);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profile:
                Intent intent = new Intent(ChatActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;

            case R.id.settings:
                View settingsLayout = getLayoutInflater().inflate(R.layout.settings_layout,null,false);

                AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                        .setTitle("SimpLearn")
                        .setPositiveButton("Save", (dialogInterface, i) -> {

                        })
                        .setNegativeButton("Close",null)
                        .setView(settingsLayout);
                dialog.show();
                break;
            case R.id.logout:
                AlertDialog.Builder logoutDialog = new AlertDialog.Builder(this)
                        .setTitle("SimpLearn")
                        .setMessage("Are you sure you want to log out?")
                        .setPositiveButton("Yes", (dialogInterface, i) -> {
                            setUserOffline();
                            FirebaseAuth.getInstance().signOut();
                            finish();
                            startActivity(new Intent(this,MainActivity.class));
                        })
                        .setNegativeButton("Close",null);
                logoutDialog.show();
                break;
        }


        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);


        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        setUserOffline();
    }

    void setUserOffline() {
        if(firebaseAuth.getUid() == null) return;
        DocumentReference documentReference = firebaseFirestore.collection("Users")
                .document(firebaseAuth.getUid());
        documentReference.update("status", "Offline")
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(), "Now User is Offline", Toast.LENGTH_SHORT).show();
                }
            });
    }

    @Override
    protected void onStart() {
        super.onStart();
        DocumentReference documentReference = firebaseFirestore.collection("Users").document(firebaseAuth.getUid());
        documentReference.update("status", "Online").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Now User is Online", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}