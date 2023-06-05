package com.example.simplearn;

import android.content.Intent;
import android.net.Uri;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class game3fragment extends Fragment {
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
        View view = inflater.inflate(R.layout.gamethreefragment, container, false);

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
                    case "German":
                        mWebView.loadUrl("https://www.digitaldialects.com/German.htm");
                        //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/German.htm"));
                        //startActivity(browserIntent);
                        // code block
                        break;
                    case "Spanish":
                        mWebView.loadUrl("https://www.digitaldialects.com/Spanish.htm");
                        //Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/Spanish.htm"));
                        //startActivity(browserIntent2);
                        // code blockcase
                        break;
                    case "chinese":
                        mWebView.loadUrl("https://www.digitaldialects.com/Chinese.htm");
                        //Intent browserIntent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/Chinese.htm"));
                        //startActivity(browserIntent3);
                        break;

                    case "russian":
                        mWebView.loadUrl("https://www.digitaldialects.com/Russian.htm");

                        //Intent browserIntent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/Russian.htm"));
                        //startActivity(browserIntent4);
                        break;

                    case "Arabic":
                        mWebView.loadUrl("https://www.digitaldialects.com/Arabic.htm");
                        //Intent browserIntent5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/Arabic.htm"));
                        //startActivity(browserIntent5);
                        break;

                    case "Italian":
                        mWebView.loadUrl("https://www.digitaldialects.com/Italian.htm");
                        //Intent browserIntent6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/Italian.htm"));
                        //startActivity(browserIntent6);
                        break;
                    case "Franche":
                        mWebView.loadUrl("https://www.digitaldialects.com/French.htm");
                        //Intent browserIntent7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/French.htm"));
                        //startActivity(browserIntent7);
                        break;

                    default:
                        mWebView.loadUrl("https://www.digitaldialects.com/French.htm");
                        //Intent browserIntent8 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/French.htm"));
                        //startActivity(browserIntent8);
                        // code block
                }




            }
        }, 30); // מחכה למשך 5 שניות לפני שהופעל ה switch case









        return view;
    }

}

