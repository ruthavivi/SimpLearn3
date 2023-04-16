package com.example.simplearn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class articleFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public String motherL,learnL;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.articlefragment,container, false);

//        Button hebrew=(Button) view.findViewById(R.id.button2);
//        Button english=(Button) view.findViewById(R.id.button3);
//        Button spanish=(Button) view.findViewById(R.id.button4);
//        Button chinease=(Button) view.findViewById(R.id.button5);
//        Button russian=(Button) view.findViewById(R.id.button6);
//        Button arabic=(Button) view.findViewById(R.id.button7);
//        Button italian=(Button) view.findViewById(R.id.button8);
//        Button franche=(Button) view.findViewById(R.id.button9);
        Button article=(Button) view.findViewById(R.id.button10);

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseFirestore = FirebaseFirestore.getInstance();

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
                motherL= muserprofile.getMotherlanguage();
                learnL=muserprofile.getLearnlanguage();


            }
        });

        article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch(learnL) {
                    case "Hebrew":
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/topics/CAAqJggKIiBDQkFTRWdvSUwyMHZNRGx1YlY4U0FtbDNHZ0pKVENnQVAB?hl=he&gl=IL&ceid=IL%3Ahe"));
                        startActivity(browserIntent);
                        // code block
                        break;
                    case "English":
                        Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=en-US&gl=US&ceid=US:en"));
                        startActivity(browserIntent2);
                        // code blockcase
                        break;
                    case "Spanish":
                        Intent browserIntent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=es-419&gl=AR&ceid=AR:es-419"));
                        startActivity(browserIntent3);
                        break;

                    case "chinease":

                        Intent browserIntent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=zh-CN&gl=CN&ceid=CN:zh-Hans"));
                        startActivity(browserIntent4);
                        break;

                    case "russian":
                        Intent browserIntent5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=ru&gl=RU&ceid=RU:ru"));
                        startActivity(browserIntent5);
                        break;

                    case "Arabic":
                        Intent browserIntent6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=ar&gl=AE&ceid=AE:ar"));
                        startActivity(browserIntent6);
                        break;
                    case "Italian":
                        Intent browserIntent7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=it&gl=IT&ceid=IT:it"));
                        startActivity(browserIntent7);
                        break;
                    case "Franche":
                        Intent browserIntent8 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=fr&gl=FR&ceid=FR:fr"));
                        startActivity(browserIntent8);
                        break;
                    default:
                        // code block
                }

            }
        });












//        hebrew.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/topics/CAAqJggKIiBDQkFTRWdvSUwyMHZNRGx1YlY4U0FtbDNHZ0pKVENnQVAB?hl=he&gl=IL&ceid=IL%3Ahe"));
//                startActivity(browserIntent);
//            }
//        });
//        english.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=en-US&gl=US&ceid=US:en"));
//                startActivity(browserIntent);
//            }
//        });
//
//        spanish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=es-419&gl=AR&ceid=AR:es-419"));
//                startActivity(browserIntent);
//            }
//        });
//
//        chinease.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=zh-CN&gl=CN&ceid=CN:zh-Hans"));
//                startActivity(browserIntent);
//            }
//        });
//
//        russian.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=ru&gl=RU&ceid=RU:ru"));
//                startActivity(browserIntent);
//            }
//        });
//        arabic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=ar&gl=AE&ceid=AE:ar"));
//                startActivity(browserIntent);
//            }
//        });
//        italian.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=it&gl=IT&ceid=IT:it"));
//                startActivity(browserIntent);
//            }
//        });
//
//        franche.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=fr&gl=FR&ceid=FR:fr"));
//                startActivity(browserIntent);
//            }
//        });
        return view;


    }
    }

