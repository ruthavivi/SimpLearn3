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
    private WebView mWebView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.articlefragment,container, false);

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
                        mWebView.loadUrl("https://news.google.com/topics/CAAqJggKIiBDQkFTRWdvSUwyMHZNRGx1YlY4U0FtbDNHZ0pKVENnQVAB?hl=he&gl=IL&ceid=IL%3Ahe");
//                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/topics/CAAqJggKIiBDQkFTRWdvSUwyMHZNRGx1YlY4U0FtbDNHZ0pKVENnQVAB?hl=he&gl=IL&ceid=IL%3Ahe"));
//                        startActivity(browserIntent);
                        // code block
                        break;
                    case "English":

                        mWebView.loadUrl("https://news.google.com/home?hl=en-US&gl=US&ceid=US:en");
//                        Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=en-US&gl=US&ceid=US:en"));
//                        startActivity(browserIntent2);
                        // code blockcase
                        break;
                    case "Spanish":
                        mWebView.loadUrl("https://news.google.com/home?hl=es-419&gl=AR&ceid=AR:es-419");
//                        Intent browserIntent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=es-419&gl=AR&ceid=AR:es-419"));
//                        startActivity(browserIntent3);
                        break;

                    case "chinease":
                        mWebView.loadUrl("https://news.google.com/home?hl=zh-CN&gl=CN&ceid=CN:zh-Hans");

//                        Intent browserIntent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=zh-CN&gl=CN&ceid=CN:zh-Hans"));
//                        startActivity(browserIntent4);
                        break;

                    case "russian":
                        mWebView.loadUrl("https://news.google.com/home?hl=ru&gl=RU&ceid=RU:ru");
//                        Intent browserIntent5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=ru&gl=RU&ceid=RU:ru"));
//                        startActivity(browserIntent5);
                        break;

                    case "Arabic":
                        mWebView.loadUrl("https://news.google.com/home?hl=ar&gl=AE&ceid=AE:ar");
//                        Intent browserIntent6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=ar&gl=AE&ceid=AE:ar"));
//                        startActivity(browserIntent6);
                        break;
                    case "Italian":
                        mWebView.loadUrl("https://news.google.com/home?hl=it&gl=IT&ceid=IT:it");
//                        Intent browserIntent7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=it&gl=IT&ceid=IT:it"));
//                        startActivity(browserIntent7);
                        break;
                    case "Franche":
                        mWebView.loadUrl("https://news.google.com/home?hl=fr&gl=FR&ceid=FR:fr");
//                        Intent browserIntent8 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/home?hl=fr&gl=FR&ceid=FR:fr"));
//                        startActivity(browserIntent8);
                        break;
                    default:
                        // code block
                }
            }
        }, 50); // מחכה למשך 5 שניות לפני שהופעל ה switch case







        return view;


    }
    }

