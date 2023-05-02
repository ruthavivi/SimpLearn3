package com.example.simplearn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class gamesFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public String motherL,learnL;
    private WebView mWebView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.gamesfragment,container, false);

        Button button=(Button) view.findViewById(R.id.button2);
        Button button3=(Button) view.findViewById(R.id.button3);
        Button button4=(Button) view.findViewById(R.id.button4);
        Button button5=(Button) view.findViewById(R.id.button5);
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




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("https://www.50languages.com/games/memo/he/es/1/1/");
                //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.50languages.com/games/memo/he/es/1/1/"));
                //startActivity(browserIntent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(learnL) {
                    case "Hebrew":
                        mWebView.loadUrl("https://www.loecsen.com/en/learn-hebrew#/en/Essentials");
                        //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-hebrew#/en/Essentials"));
                        //startActivity(browserIntent);
                        // code block
                        break;
                    case "German":
                        mWebView.loadUrl("https://www.loecsen.com/en/learn-german#/en/Essentials");
                        //Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-german#/en/Essentials"));
                        //startActivity(browserIntent2);
                        // code blockcase
                        break;
                    case "Spanish":
                        mWebView.loadUrl("https://www.loecsen.com/en/learn-spanish#/en/Essentials");
                        //Intent browserIntent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-spanish#/en/Essentials"));
                        //startActivity(browserIntent3);
                        break;

                    case "chinease":
                        mWebView.loadUrl("https://www.loecsen.com/en/learn-chinese#/en/Essentials");

                        //Intent browserIntent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-chinese#/en/Essentials"));
                        //startActivity(browserIntent4);
                        break;

                    case "russian":
                        mWebView.loadUrl("https://www.loecsen.com/en/learn-russian#/en/Essentials");
                        //Intent browserIntent5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-russian#/en/Essentials"));
                        //startActivity(browserIntent5);
                        break;

                    case "Arabic":
                        mWebView.loadUrl("https://www.loecsen.com/en/learn-arabic#/en/Essentials");
                        //Intent browserIntent6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-arabic#/en/Essentials"));
                        //startActivity(browserIntent6);
                        break;
                    case "Italian":
                        mWebView.loadUrl("https://www.loecsen.com/en/learn-italian#/en/Essentials");
                        //Intent browserIntent7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-italian#/en/Essentials"));
                        //startActivity(browserIntent7);
                        break;
                    case "Franche":
                        mWebView.loadUrl("https://www.loecsen.com/en/learn-french#/en/Essentials");
                        //Intent browserIntent8 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-french#/en/Essentials"));
                        //startActivity(browserIntent8);
                        break;
                    default:

                        // code block
                }
//                Fragment languageFragment = new languageFragment();
//                FragmentTransaction fm=getActivity().getSupportFragmentManager().beginTransaction();
//                fm.replace(R.id.fragment_container,languageFragment).commit();



//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.50languages.com/games/memo/he/es/1/1/"));
//                startActivity(browserIntent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch(learnL) {
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

//                Fragment languagetwoFragment = new languagetwoFragment();
//                FragmentTransaction fm=getActivity().getSupportFragmentManager().beginTransaction();
//                fm.replace(R.id.fragment_container,languagetwoFragment).commit();



//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.50languages.com/games/memo/he/es/1/1/"));
//                startActivity(browserIntent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(learnL) {
                    case "Hebrew":

                        Intent intent = new Intent(getActivity(),WordGame.class);
                        startActivity(intent);
                        // code block
                        break;
                    case "English":

                        Intent intent2 = new Intent(getActivity(),WordGame.class);
                        startActivity(intent2);
                        // code blockcase
                        break;
                    case "Spanish":

                        Intent intent3 = new Intent(getActivity(),WordGame.class);
                        startActivity(intent3);
                        break;

                    case "chinease":


                        Intent intent4 = new Intent(getActivity(),WordGame.class);
                        startActivity(intent4);
                        break;

                    case "russian":

                        Intent intent5 = new Intent(getActivity(),WordGame.class);
                        startActivity(intent5);
                        break;

                    case "Arabic":

                        Intent intent6 = new Intent(getActivity(),WordGame.class);
                        startActivity(intent6);
                        break;
                    case "Italian":

                        Intent intent7 = new Intent(getActivity(),WordGame.class);
                        startActivity(intent7);
                        break;
                    case "Franche":

                        Intent intent8 = new Intent(getActivity(),WordGame.class);
                        startActivity(intent8);
                        break;
                    default:
                        // code block
                }
//                Fragment WordSearchLang = new WordSearchLang();
//                FragmentTransaction fm=getActivity().getSupportFragmentManager().beginTransaction();
//                fm.replace(R.id.fragment_container,WordSearchLang).commit();


            }
        });


        return view;
    }
}
