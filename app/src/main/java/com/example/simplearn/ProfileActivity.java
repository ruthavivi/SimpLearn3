package com.example.simplearn;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfileActivity extends AppCompatActivity  {



    private final static int CAMERA_CODE = 1;
    private final static int GALLERY_CODE = 2;

    EditText mviewusername,myviewmotherlanguage,myvuewlearning,myviewage,bio;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    TextView mmovetoupdateprofile;
    String tweetText="hey im new here";

    FirebaseFirestore firebaseFirestore;

    ImageView mviewuserimageinimageview;

    StorageReference storageReference;


    // user images to upload
    private Uri imageUri;
    private Bitmap imageBitmap;
    boolean userHasImage;

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
        bio=findViewById(R.id.bio);

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

        //EditText bioEt = findViewById(R.id.mviewBio);
        storageReference=firebaseStorage.getReference();

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
                if(muserprofile.getImage() != null && imageBitmap == null && imageUri ==null) {
                    Picasso.get().load(muserprofile.getImage()).into(mviewuserimageinimageview);
                }
                //bioEt.setText(muserprofile.getBio());
                mviewusername.setText(muserprofile.getUsername());
                bio.setText(muserprofile.getBio());
                myviewmotherlanguage.setText(muserprofile.getMotherlanguage());
                myvuewlearning.setText(muserprofile.getLearnlanguage());
                myviewage.setText(muserprofile.getAge());
            }
        });


        mmovetoupdateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = mviewusername.getText().toString();
                String mybio=bio.getText().toString();
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
                if(mybio.isEmpty()) {
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
                //if(!bioEt.getText().toString().isEmpty())
                 //updateValues.put("bio", bioEt.getText().toString());
                updateValues.put("age",age);
                updateValues.put("bio",mybio);
                updateValues.put("motherlanguage",motherLanguage);
                updateValues.put("learnlanguage",learningLanguage);
                updateValues.put("text", tweetText);
                StorageReference reference = FirebaseStorage.getInstance()
                        .getReference("Images")
                        .child(FirebaseAuth.getInstance().getUid())
                        .child("Profile pic");
                if(imageUri !=null) {
                    reference.putFile(imageUri)
                            .addOnSuccessListener(taskSnapshot ->
                                    reference.getDownloadUrl()
                                            .addOnSuccessListener(uri -> {
                                                updateValues.put("image", uri.toString());
                                                saveUserDetails(pd, updateValues);
                                            }).addOnFailureListener(e -> Toast.makeText(ProfileActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show())).addOnFailureListener(e -> Toast.makeText(ProfileActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show());
                } else if(imageBitmap != null) {
                    System.out.println("Uploading image");
                    byte[] imageBytes = getBytesFromBitmap(imageBitmap);
                    reference.putBytes(imageBytes)
                            .addOnSuccessListener(taskSnapshot ->
                                    reference.getDownloadUrl()
                                            .addOnSuccessListener(uri -> {
                                                updateValues.put("image", uri.toString());
                                                saveUserDetails(pd, updateValues);
                                            }).addOnFailureListener(e -> Toast.makeText(ProfileActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show())).addOnFailureListener(e -> Toast.makeText(ProfileActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show());

                } else {
                    saveUserDetails(pd ,updateValues);
                }
            }
        });

        mviewuserimageinimageview.setOnClickListener(v -> {

            View pictureChooseLayout = getLayoutInflater().inflate(R.layout.choose_picture,null,false);
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("SimpLearn")
                    .setView(pictureChooseLayout)
                    .setPositiveButton("Close",null)
                    .create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    Button cameraBtn = pictureChooseLayout.findViewById(R.id.takePicture);
                    Button galleryBtn = pictureChooseLayout.findViewById(R.id.chooseFromGallery);

                    cameraBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(ActivityCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.CAMERA)
                            == PERMISSION_GRANTED) {
                                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, CAMERA_CODE);
                            } else {
                                 ActivityCompat.requestPermissions(ProfileActivity.this,
                                         new String[]{Manifest.permission.CAMERA},
                                         1);
                            }
                        }
                    });

                    galleryBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(ActivityCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                                    == PERMISSION_GRANTED) {
                                Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                                galleryIntent.setType("image/*");
                                startActivityForResult(galleryIntent, GALLERY_CODE);
                            } else {
                                ActivityCompat.requestPermissions(ProfileActivity.this,
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                        1);
                            }
                        }
                    });
                }
            });
            dialog.show();
        });

    }

    public static byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
    private void saveUserDetails(ProgressDialog pd, HashMap<String,Object> updateValues) {

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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if(requestCode == CAMERA_CODE) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                mviewuserimageinimageview.setImageBitmap(bitmap);
                imageBitmap = bitmap;
                imageUri = null;
            }else if(requestCode == GALLERY_CODE) {
                Uri uri = data.getData();
                mviewuserimageinimageview.setImageURI(uri);
                imageUri = uri;
                imageBitmap = null;
            }
        }
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