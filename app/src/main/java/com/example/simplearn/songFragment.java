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

public class songFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public String motherL,learnL;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.songfragment,container, false);

//        Button button=(Button) view.findViewById(R.id.button2);
//        Button button3=(Button) view.findViewById(R.id.button3);
//        Button button4=(Button) view.findViewById(R.id.button4);
//        Button button5=(Button) view.findViewById(R.id.button5);
//        Button button6=(Button) view.findViewById(R.id.button6);
//        Button button7=(Button) view.findViewById(R.id.button7);
//        Button button8=(Button) view.findViewById(R.id.button8);
//        Button button9=(Button) view.findViewById(R.id.button9);
        Button button10=(Button) view.findViewById(R.id.button10);
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

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch(learnL) {
                    case "Hebrew":
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLGoOLgH-OsN7AoG5Rot_fK_lmy3ol81Dv"));
                        startActivity(browserIntent);
                        // code block
                        break;
                    case "English":
                        Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLYLb0XTvo8voLll99HN_tlXSqo7_P_yij"));
                        startActivity(browserIntent2);
                        // code blockcase
                        break;
                    case "Spanish":
                        Intent browserIntent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLTqtOhbrKEtCg-GDksPpIOWGf5JM-y0nt"));
                        startActivity(browserIntent3);
                        break;

                    case "chinease":

                        Intent browserIntent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLMMP91KB4rYYQRVUej89yFPx6uiyBnBk_"));
                        startActivity(browserIntent4);
                        break;

                    case "russian":
                        Intent browserIntent5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLG7T7IWRveA_tmHLanNYQuGdABxaYfCpr"));
                        startActivity(browserIntent5);
                        break;

                    case "Arabic":
                        Intent browserIntent6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLBCdoy0Y0lsMdy3E8mP55FPpEGWGXyZE2"));
                        startActivity(browserIntent6);
                        break;
                    case "Italian":
                        Intent browserIntent7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLBTWm99Xrp1fuC5ctyDjJQ4fbuUvUNIq_"));
                        startActivity(browserIntent7);
                        break;
                    case "Franche":
                        Intent browserIntent8 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=DB3DXcy8CJc&list=PLUTooNITsBD7LG1KAQ7ECWWY7RHykfMoK"));
                        startActivity(browserIntent8);
                        break;
                    default:
                        // code block
                }

            }
        });






//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLGoOLgH-OsN7AoG5Rot_fK_lmy3ol81Dv"));
//                startActivity(browserIntent);
//            }
//        });
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLYLb0XTvo8voLll99HN_tlXSqo7_P_yij"));
//                startActivity(browserIntent);
//            }
//        });
//
//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLTqtOhbrKEtCg-GDksPpIOWGf5JM-y0nt"));
//                startActivity(browserIntent);
//            }
//        });
//
//        button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLMMP91KB4rYYQRVUej89yFPx6uiyBnBk_"));
//                startActivity(browserIntent);
//            }
//        });
//
//        button6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLG7T7IWRveA_tmHLanNYQuGdABxaYfCpr"));
//                startActivity(browserIntent);
//            }
//        });
//        button7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLBCdoy0Y0lsMdy3E8mP55FPpEGWGXyZE2"));
//                startActivity(browserIntent);
//            }
//        });
//        button8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/playlist?list=PLBTWm99Xrp1fuC5ctyDjJQ4fbuUvUNIq_"));
//                startActivity(browserIntent);
//            }
//        });
//
//        button9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=DB3DXcy8CJc&list=PLUTooNITsBD7LG1KAQ7ECWWY7RHykfMoK"));
//                startActivity(browserIntent);
//            }
//        });
        return view;


    }
}
