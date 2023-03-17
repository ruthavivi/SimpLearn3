package com.example.simplearn;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfileActivity extends AppCompatActivity  {


    EditText mviewusername,myviewmotherlanguage,myvuewlearning,myviewage;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    TextView mmovetoupdateprofile;

    FirebaseFirestore firebaseFirestore;

    ImageView mviewuserimageinimageview;

    StorageReference storageReference;

    private String ImageURIacessToken;

    androidx.appcompat.widget.Toolbar mtoolbarofviewprofile;
    ImageButton mbackbuttonofviewprofile;


    FirebaseStorage firebaseStorage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mviewuserimageinimageview=findViewById(R.id.viewuserimageinimageview);
        mviewusername=findViewById(R.id.viewusername);
        myviewmotherlanguage=findViewById(R.id.viewmotherlanguage);
        myvuewlearning=findViewById(R.id.viewLearninglanguage);
        myviewage=findViewById(R.id.viewAge);

        mmovetoupdateprofile=findViewById(R.id.updateProfileSubmit);
        firebaseFirestore=FirebaseFirestore.getInstance();
        mtoolbarofviewprofile=findViewById(R.id.toolbarofviewprofile);
        mbackbuttonofviewprofile=findViewById(R.id.backbuttonofviewprofile);
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();


        setSupportActionBar(mtoolbarofviewprofile);

        mbackbuttonofviewprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        storageReference=firebaseStorage.getReference();
        storageReference.child("Images").child(firebaseAuth.getUid()).child("Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                ImageURIacessToken=uri.toString();
                Picasso.get().load(uri).into(mviewuserimageinimageview);

            }
        });
        DocumentReference documentReference = firebaseFirestore.collection("Users").document(firebaseAuth.getUid());

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                userprofile muserprofile= value.toObject(userprofile.class);
                if(muserprofile == null) {
                    userprofile profile = new userprofile();
                    profile.setUserUID(firebaseAuth.getUid());
                    firebaseFirestore.collection("Users")
                            .document(firebaseAuth.getUid())
                            .set(profile);
                    return;
                }
                mviewusername.setText(muserprofile.getUsername());
                myviewmotherlanguage.setText(muserprofile.getMotherlanguage());
                myvuewlearning.setText(muserprofile.getLearnlanguage());
                myviewage.setText(muserprofile.getAge());
            }
        });


        mmovetoupdateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = mviewusername.getText().toString();
                String motherLanguage = myviewmotherlanguage.getText().toString();
                String learningLanguage = myvuewlearning.getText().toString();
                String age = myviewage.getText().toString();
                String problematicField = null;
                if(userName.isEmpty()) {
                    mviewusername.requestFocus();
                    problematicField = "User name";
                }
                if(age.isEmpty()) {
                    myviewmotherlanguage.requestFocus();
                    problematicField = "Age";
                }
                if(motherLanguage.isEmpty()) {
                    myviewmotherlanguage.requestFocus();
                    problematicField = "Mother language";
                }
                if(learningLanguage.isEmpty()) {
                    myvuewlearning.requestFocus();
                    problematicField = "Learning language";
                }

                if(problematicField != null) {
                    Toast.makeText(ProfileActivity.this,problematicField + " is required!",Toast.LENGTH_SHORT).show();
                    return;
                }
                ProgressDialog pd = new ProgressDialog(ProfileActivity.this);
                pd.setTitle("SimpLearn");
                pd.setMessage("Saving changes..");
                pd.show();
                pd.setCancelable(false);
                HashMap<String,Object> updateValues = new HashMap<>();
                updateValues.put("username",userName);
                updateValues.put("age",age);
                updateValues.put("motherlanguage",motherLanguage);
                updateValues.put("learnlanguage",learningLanguage);
                FirebaseFirestore.getInstance().collection("Users")
                        .document(FirebaseAuth.getInstance().getUid())
                        .update(updateValues)
                        .addOnSuccessListener(success -> {
                           Toast.makeText(ProfileActivity.this,
                                   "Changes saved successfully",Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }).addOnFailureListener(e -> {
                            Toast.makeText(ProfileActivity.this,
                                    "Changes where not saved!",Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        });


                /*Intent intent=new Intent(ProfileActivity.this,UpdateProfile.class);
                intent.putExtra("nameofuser",mviewusername.getText().toString());
                intent.putExtra("namemotherlanguage",myviewmotherlanguage.getText().toString());
                intent.putExtra("namelearninglanguage",myvuewlearning.getText().toString());
                intent.putExtra("age",myviewage.getText().toString());
                startActivity(intent);*/
            }
        });




    }




    @Override
    protected void onStop() {
        super.onStop();
        DocumentReference documentReference=firebaseFirestore.collection("Users").document(firebaseAuth.getUid());
        documentReference.update("status","Offline").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(),"Now User is Offline",Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        DocumentReference documentReference=firebaseFirestore.collection("Users").document(firebaseAuth.getUid());
        documentReference.update("status","Online").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(),"Now User is Online",Toast.LENGTH_SHORT).show();
            }
        });

    }


}