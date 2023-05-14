package com.example.simplearn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Translate extends AppCompatActivity {
    private WebView mWebView;
    ImageButton backButton;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public String motherL,learnL;

    String ll,ml,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate2);

        mWebView = findViewById(R.id.webview);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient());

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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                switch(learnL) {
                    case "Hebrew":
                        ll="he";
                        break;
                    case "German":
                        ll="de";
                        break;
                    case "English":
                        ll="en";
                        break;
                    case "Spanish":
                        ll="es";
                        break;

                    case "chinease":
                        ll="zh";
                        break;

                    case "russian":
                        ll="ru";
                        break;

                    case "Arabic":
                        ll="ar";
                        break;
                    case "Italian":
                        ll="it";
                        break;
                    case "Franche":
                        ll="fr";
                        break;
                    default:

                        // code block
                }

                switch(motherL) {
                    case "Hebrew":
                        ml="he";
                        break;
                    case "German":
                        ml="de";
                        break;
                    case "English":
                        ml="en";
                        break;
                    case "Spanish":
                        ml="es";
                        break;

                    case "chinease":
                        ml="zh";
                        break;

                    case "russian":
                        ml="ru";
                        break;

                    case "Arabic":
                        ml="ar";
                        break;
                    case "Italian":
                        ml="it";
                        break;
                    case "Franche":
                        ml="fr";
                        break;
                    default:

                        // code block
                }
            }
        }, 50); // מחכה למשך 5 שניות לפני שהופעל ה switch case

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                url="https://translate.google.co.il/?sl="+ml+"&tl="+ll+"&op=translate";
                mWebView.loadUrl(url);
                    }
        }, 70); // מחכה למשך 5 שניות לפני שהופעל ה switch case


        mWebView.loadUrl(url);
        backButton = findViewById(R.id.backbuttonofspecificchat);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}