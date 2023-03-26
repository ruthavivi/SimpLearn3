package com.example.simplearn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class languageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_language,container, false);

        Button button=(Button) view.findViewById(R.id.button2);
        Button button3=(Button) view.findViewById(R.id.button3);
        Button button4=(Button) view.findViewById(R.id.button4);
        Button button5=(Button) view.findViewById(R.id.button5);
        Button button6=(Button) view.findViewById(R.id.button6);
        Button button7=(Button) view.findViewById(R.id.button7);
        Button button8=(Button) view.findViewById(R.id.button8);
        Button button9=(Button) view.findViewById(R.id.button9);


//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.duolingo.com/lesson"));
//                startActivity(browserIntent);
//            }
//        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/German.htm"));
                startActivity(browserIntent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/Spanish.htm"));
                startActivity(browserIntent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/Chinese.htm"));
                startActivity(browserIntent);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/Russian.htm"));
                startActivity(browserIntent);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/Arabic.htm"));
                startActivity(browserIntent);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/Italian.htm"));
                startActivity(browserIntent);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.digitaldialects.com/French.htm"));
                startActivity(browserIntent);
            }
        });
        return view;


    }
}
