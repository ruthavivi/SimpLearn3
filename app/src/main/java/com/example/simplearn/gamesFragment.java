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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.gamesfragment,container, false);

        Button button=(Button) view.findViewById(R.id.button2);
        Button button3=(Button) view.findViewById(R.id.button3);
        Button button4=(Button) view.findViewById(R.id.button4);
        Button button5=(Button) view.findViewById(R.id.button5);

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
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.50languages.com/games/memo/he/es/1/1/"));
                startActivity(browserIntent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(learnL) {
                    case "Hebrew":
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-hebrew#/en/Essentials"));
                        startActivity(browserIntent);
                        // code block
                        break;
                    case "German":
                        Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-german#/en/Essentials"));
                        startActivity(browserIntent2);
                        // code blockcase
                        break;
                    case "Spanish":
                        Intent browserIntent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-spanish#/en/Essentials"));
                        startActivity(browserIntent3);
                        break;

                    case "chinease":

                        Intent browserIntent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-chinese#/en/Essentials"));
                        startActivity(browserIntent4);
                        break;

                    case "russian":
                        Intent browserIntent5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-russian#/en/Essentials"));
                        startActivity(browserIntent5);
                        break;

                    case "Arabic":
                        Intent browserIntent6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-arabic#/en/Essentials"));
                        startActivity(browserIntent6);
                        break;
                    case "Italian":
                        Intent browserIntent7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-italian#/en/Essentials"));
                        startActivity(browserIntent7);
                        break;
                    case "Franche":
                        Intent browserIntent8 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-french#/en/Essentials"));
                        startActivity(browserIntent8);
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
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/German.htm"));
                        startActivity(browserIntent);
                        // code block
                        break;
                    case "Spanish":
                        Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/Spanish.htm"));
                        startActivity(browserIntent2);
                        // code blockcase
                        break;
                    case "chinese":
                        Intent browserIntent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/Chinese.htm"));
                        startActivity(browserIntent3);
                        break;

                    case "russian":

                        Intent browserIntent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/Russian.htm"));
                        startActivity(browserIntent4);
                        break;

                    case "Arabic":
                        Intent browserIntent5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/Arabic.htm"));
                        startActivity(browserIntent5);
                        break;

                    case "Italian":
                        Intent browserIntent6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/Italian.htm"));
                        startActivity(browserIntent6);
                        break;
                    case "Franche":
                        Intent browserIntent7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/French.htm"));
                        startActivity(browserIntent7);
                        break;

                    default:
                        Intent browserIntent8 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/French.htm"));
                        startActivity(browserIntent8);
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
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.yo-yoo.co.il/tifzorot/tif.php?id=13"));
                        startActivity(browserIntent);
                        // code block
                        break;
                    case "English":
                        Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amuselabs.com/games/wordsearch/"));
                        startActivity(browserIntent2);
                        // code blockcase
                        break;
                    case "Spanish":
                        Intent browserIntent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://thewordsearch.com/puzzle/4479/la-comida/"));
                        startActivity(browserIntent3);
                        break;

                    case "chinease":

                        Intent browserIntent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                        startActivity(browserIntent4);
                        break;

                    case "russian":
                        Intent browserIntent5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                        startActivity(browserIntent5);
                        break;

                    case "Arabic":
                        Intent browserIntent6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                        startActivity(browserIntent6);
                        break;
                    case "Italian":
                        Intent browserIntent7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                        startActivity(browserIntent7);
                        break;
                    case "Franche":
                        Intent browserIntent8 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://thewordsearch.com/puzzle/10843/french-for-beginners/"));
                        startActivity(browserIntent8);
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
