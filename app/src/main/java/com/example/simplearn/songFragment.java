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

public class songFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public String motherL,learnL;
    private WebView mWebView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.songfragment,container, false);



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
                        mWebView.loadUrl("https://youtube.com/playlist?list=PLGoOLgH-OsN7AoG5Rot_fK_lmy3ol81Dv");
//                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLGoOLgH-OsN7AoG5Rot_fK_lmy3ol81Dv"));
//                        startActivity(browserIntent);
                        // code block
                        break;
                    case "English":
                        mWebView.loadUrl("https://youtube.com/playlist?list=PLYLb0XTvo8voLll99HN_tlXSqo7_P_yij");
//                        Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLYLb0XTvo8voLll99HN_tlXSqo7_P_yij"));
//                        startActivity(browserIntent2);
                        // code blockcase
                        break;
                    case "Spanish":
                        mWebView.loadUrl("https://youtube.com/playlist?list=PLTqtOhbrKEtCg-GDksPpIOWGf5JM-y0nt");
//                        Intent browserIntent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLTqtOhbrKEtCg-GDksPpIOWGf5JM-y0nt"));
//                        startActivity(browserIntent3);
                        break;

                    case "chinease":
                        mWebView.loadUrl("https://youtube.com/playlist?list=PLMMP91KB4rYYQRVUej89yFPx6uiyBnBk_");

//                        Intent browserIntent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLMMP91KB4rYYQRVUej89yFPx6uiyBnBk_"));
//                        startActivity(browserIntent4);
                        break;

                    case "russian":
                        mWebView.loadUrl("https://youtube.com/playlist?list=PLG7T7IWRveA_tmHLanNYQuGdABxaYfCpr");
//                        Intent browserIntent5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLG7T7IWRveA_tmHLanNYQuGdABxaYfCpr"));
//                        startActivity(browserIntent5);
                        break;

                    case "Arabic":
                        mWebView.loadUrl("https://youtube.com/playlist?list=PLBCdoy0Y0lsMdy3E8mP55FPpEGWGXyZE2");
//                        Intent browserIntent6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLBCdoy0Y0lsMdy3E8mP55FPpEGWGXyZE2"));
//                        startActivity(browserIntent6);
                        break;
                    case "Italian":
                        mWebView.loadUrl("https://youtube.com/playlist?list=PLBTWm99Xrp1fuC5ctyDjJQ4fbuUvUNIq_");
//                        Intent browserIntent7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLBTWm99Xrp1fuC5ctyDjJQ4fbuUvUNIq_"));
//                        startActivity(browserIntent7);
                        break;
                    case "Franche":
                        mWebView.loadUrl("https://www.youtube.com/watch?v=DB3DXcy8CJc&list=PLUTooNITsBD7LG1KAQ7ECWWY7RHykfMoK");
//                        Intent browserIntent8 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=DB3DXcy8CJc&list=PLUTooNITsBD7LG1KAQ7ECWWY7RHykfMoK"));
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
