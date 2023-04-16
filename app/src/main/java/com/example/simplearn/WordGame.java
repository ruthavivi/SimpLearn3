package com.example.simplearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//public class WordGame extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_word_game);
//    }
//}

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WordGame extends AppCompatActivity {
    private TextView tvScrambledWord;
    private EditText etUserGuess;
    private Button btnSubmit;
    private TextView tvResult;

    private ArrayList<String> wordsList;
    private String currentWord;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_game);

        // Initialize UI components
        tvScrambledWord = findViewById(R.id.tvScrambledWord);
        etUserGuess = findViewById(R.id.etUserGuess);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvResult = findViewById(R.id.tvResult);

        // Load words from text file
        wordsList=new ArrayList<>();
        wordsList.add("hellow");
        wordsList.add("red");
        wordsList.add("black");
        wordsList.add("blue");


        // Initialize random object
        random = new Random();

        // Start new game
        startNewGame();

        // Set click listener for submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUserGuess();
            }
        });
    }

    private void startNewGame() {
        // Shuffle words list
        Collections.shuffle(wordsList);

        // Get the next word
        currentWord = wordsList.get(0);

        // Scramble the word
        String scrambledWord = scrambleWord(currentWord);

        // Update UI
        tvScrambledWord.setText(scrambledWord);
        etUserGuess.setText("");
        tvResult.setText("");
    }

    private String scrambleWord(String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int randomIndex = random.nextInt(chars.length);
            char temp = chars[i];
            chars[i] = chars[randomIndex];
            chars[randomIndex] = temp;
        }
        return new String(chars);
    }

    private void checkUserGuess() {
        String userGuess = etUserGuess.getText().toString();
        if (userGuess.equalsIgnoreCase(currentWord)) {
            tvResult.setText("Correct!");
        } else {
            tvResult.setText("Incorrect, try again.");
        }
    }

    private List<String> loadWordsListFromTextFile(Context context, String fileName) {
        List<String> wordsList = new ArrayList<>();
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                wordsList.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsList;
    }
}