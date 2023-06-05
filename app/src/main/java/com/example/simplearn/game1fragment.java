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

public class game1fragment extends Fragment {
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
        View view = inflater.inflate(R.layout.gameonefragment, container, false);

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
                                ll = "he";
                                break;
                            case "German":
                                ll = "de";
                                break;
                            case "English":
                                ll = "en";
                                break;
                            case "Spanish":
                                ll = "es";
                                break;

                            case "chinease":
                                ll = "zh";
                                break;

                            case "russian":
                                ll = "ru";
                                break;

                            case "Arabic":
                                ll = "ar";
                                break;
                            case "Italian":
                                ll = "it";
                                break;
                            case "Franche":
                                ll = "fr";
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

                url = "https://50languages.com/" + ml + "/game/memo/" + ll + "/0";
                mWebView.loadUrl(url);

                return view;
            }

        }


