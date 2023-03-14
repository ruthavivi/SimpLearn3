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


public class languagetwoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_languagetwo,container, false);

        Button button=(Button) view.findViewById(R.id.button2);
        Button button3=(Button) view.findViewById(R.id.button3);
        Button button4=(Button) view.findViewById(R.id.button4);
        Button button5=(Button) view.findViewById(R.id.button5);
        Button button6=(Button) view.findViewById(R.id.button6);
        Button button7=(Button) view.findViewById(R.id.button7);
        Button button8=(Button) view.findViewById(R.id.button8);
        Button button9=(Button) view.findViewById(R.id.button9);







        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-hebrew"));
                startActivity(browserIntent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-german"));
                startActivity(browserIntent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-spanish"));
                startActivity(browserIntent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-chinese"));
                startActivity(browserIntent);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-russian"));
                startActivity(browserIntent);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-arabic-moroccan"));
                startActivity(browserIntent);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-italian"));
                startActivity(browserIntent);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.loecsen.com/en/learn-french"));
                startActivity(browserIntent);
            }
        });
        return view;


    }
}
