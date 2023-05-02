package com.example.simplearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UserBio extends AppCompatActivity {
    TextView mviewusername,myviewmotherlanguage,myvuewlearning,myviewage,myviewbio;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseFirestore firebaseFirestore;

    ImageView mviewuserimageinimageview;

    StorageReference storageReference;
    String mrecievername, sendername, mrecieveruid, msenderuid,motherLanguage,learnLanguage,age,bio;


    // user images to upload
    private Uri imageUri;
    private Bitmap imageBitmap;
    boolean userHasImage;
    Intent intent;

    androidx.appcompat.widget.Toolbar mtoolbarofviewprofile;
    ImageButton mbackbuttonofviewprofile;


    FirebaseStorage firebaseStorage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_bio);
        intent = getIntent();

        mviewuserimageinimageview=findViewById(R.id.viewuserimageinimageview);
        mviewusername=findViewById(R.id.viewusername);
        myviewmotherlanguage=findViewById(R.id.viewmotherlanguage);
        myvuewlearning=findViewById(R.id.viewLearninglanguage);
        myviewage=findViewById(R.id.viewAge);
        myviewbio=findViewById(R.id.viewBio);



        mrecieveruid = getIntent().getStringExtra("receiveruid");
        mrecievername = getIntent().getStringExtra("name");
        motherLanguage=getIntent().getStringExtra("motherLanguage");
        learnLanguage=getIntent().getStringExtra("learnLanguage");
        age=getIntent().getStringExtra("age");
        bio=getIntent().getStringExtra("bio");

        mviewusername.setText(mrecievername);
        myviewmotherlanguage.setText(motherLanguage);
        myvuewlearning.setText(learnLanguage);
        myviewage.setText(age);
        myviewbio.setText(bio);
    }
}