package com.example.simplearn;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class game2fragment extends Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public String motherL,learnL;
    private WebView mWebView;
    String ll,ml,url;

    MyPagerAdapter pagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gametwofragment, container, false);

        mWebView = view.findViewById(R.id.webview);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient());



        firebaseAuth = FirebaseAuth.getInstance();

        firebaseFirestore = FirebaseFirestore.getInstance();

        DocumentReference documentReference = firebaseFirestore.collection("Users").document(firebaseAuth.getUid());

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                userprofile muserprofile = value.toObject(userprofile.class);
                if (muserprofile == null) {
                    userprofile profile = new userprofile();
                    profile.setUserUID(firebaseAuth.getUid());
                    firebaseFirestore.collection("Users")
                            .document(firebaseAuth.getUid())
                            .set(profile);
                    return;
                }
                motherL = muserprofile.getMotherlanguage();
                learnL = muserprofile.getLearnlanguage();
            }
        });


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (learnL) {
                    case "Hebrew":
                        ll = "hebrew";
                        break;
                    case "German":
                        ll = "german";
                        break;
                    case "English":
                        ll = "english";
                        break;
                    case "Spanish":
                        ll = "spanish";
                        break;

                    case "chinease":
                        ll = "chinease";
                        break;

                    case "russian":
                        ll = "russian";
                        break;

                    case "Arabic":
                        ll = "arabic";
                        break;
                    case "Italian":
                        ll = "italian";
                        break;
                    case "Franche":
                        ll = "french";
                        break;
                    default:

                        // code block
                }

                switch (motherL) {
                    case "Hebrew":
                        ml = "he";
                        break;
                    case "German":
                        ml = "de";
                        break;
                    case "English":
                        ml = "en";
                        break;
                    case "Spanish":
                        ml = "es";
                        break;

                    case "chinease":
                        ml = "zh";
                        break;

                    case "russian":
                        ml = "ru";
                        break;

                    case "Arabic":
                        ml = "ar";
                        break;
                    case "Italian":
                        ml = "it";
                        break;
                    case "Franche":
                        ml = "fr";
                        break;
                    default:

                        // code block
                }



            }
        }, 30); // מחכה למשך 5 שניות לפני שהופעל ה switch case


        url="https://www.lingo-play.com/en/"+ll+"-for-beginners-and-advanced/";

        mWebView.loadUrl(url);

        return view;
    }

}

