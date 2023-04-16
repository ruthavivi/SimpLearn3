package com.example.simplearn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

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
    private Button btnSubmit,button3;
    private TextView tvResult;

    private ArrayList<String> wordsList;
    private String currentWord;
    private Random random;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    public String motherL,learnL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_game);

        // Initialize UI components
        tvScrambledWord = findViewById(R.id.tvScrambledWord);
        etUserGuess = findViewById(R.id.etUserGuess);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvResult = findViewById(R.id.tvResult);
        button3=findViewById(R.id.button3);


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

                learnL=muserprofile.getLearnlanguage();
                motherL= muserprofile.getMotherlanguage();


            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (learnL) {
                    case "Hebrew":
                        wordsList = new ArrayList<>();
                        wordsList.add("תפוח");
                        wordsList.add("חלון");
                        wordsList.add("ארון");
                        wordsList.add("ספה");
                        wordsList.add("שולחן");
                        wordsList.add("מכונית");
                        wordsList.add("מטוס");
                        wordsList.add("אופניים");
                        wordsList.add("שיניים");
                        wordsList.add("כיסא");
                        wordsList.add("גרביים");
                        wordsList.add("טלפון");
                        wordsList.add("מחשב");
                        wordsList.add("כפתור");
                        wordsList.add("חתול");
                        wordsList.add("כלב");
                        // code block
                        break;
                    case "English":
                        wordsList = new ArrayList<>();
                        wordsList.add("hellow");
                        wordsList.add("red");
                        wordsList.add("black");
                        wordsList.add("blue");
                        wordsList.add("hellow");
                        wordsList.add("red");
                        wordsList.add("black");
                        wordsList.add("blue");
                        wordsList.add("hellow");
                        wordsList.add("red");
                        wordsList.add("black");
                        wordsList.add("blue");
                        wordsList.add("hellow");
                        wordsList.add("red");
                        wordsList.add("black");
                        wordsList.add("blue");
                        break;
                    case "Spanish":
                        wordsList = new ArrayList<>();
                        wordsList.add("amor");
                        wordsList.add("enojado");
                        wordsList.add("corazón ");
                        wordsList.add("traición ");
                        wordsList.add("emocionante ");
                        wordsList.add("triste ");
                        wordsList.add("mano ");
                        wordsList.add("pierna ");
                        wordsList.add("cabeza ");
                        wordsList.add("ojos ");
                        wordsList.add("naris ");
                        wordsList.add("boca ");
                        wordsList.add("madre ");
                        wordsList.add("padre ");
                        wordsList.add("hermana ");
                        wordsList.add("abuelo ");
                        break;

                    case "chinease":

                        wordsList = new ArrayList<>();
                        wordsList.add("你好");
                        wordsList.add("爱");
                        wordsList.add("幸福");
                        wordsList.add("朋友");
                        wordsList.add("家庭");
                        wordsList.add("梦想");
                        wordsList.add("快乐");
                        wordsList.add("健康");
                        wordsList.add("成功");
                        wordsList.add("学习");
                        wordsList.add("希望");
                        wordsList.add("自由");
                        wordsList.add("美丽");
                        wordsList.add("和谐");
                        wordsList.add("勇敢");
                        wordsList.add("喜欢");
                        wordsList.add("智慧");
                        wordsList.add("感恩");
                        wordsList.add("成长");
                        wordsList.add("坚持");
                        wordsList.add("幸运");
                        wordsList.add("宽容");
                        wordsList.add("诚实");
                        break;

                    case "russian":
                        wordsList = new ArrayList<>();
                        wordsList.add("привет");
                        wordsList.add("любовь");
                        wordsList.add("счастье");
                        wordsList.add("дружба");
                        wordsList.add("семья");
                        wordsList.add("мечта");
                        wordsList.add("радость");
                        wordsList.add("здоровье");
                        wordsList.add("успех");
                        wordsList.add("учение");
                        wordsList.add("надежда");
                        wordsList.add("свобода");
                        wordsList.add("красота");
                        wordsList.add("гармония");
                        wordsList.add("смелость");
                        wordsList.add("любимый");
                        wordsList.add("мудрость");
                        wordsList.add("благодарность");
                        wordsList.add("рост");
                        wordsList.add("настойчивость");
                        break;

                    case "Arabic":
                        wordsList = new ArrayList<>();
                        wordsList.add("مرحبا");
                        wordsList.add("حب");
                        wordsList.add("سعادة");
                        wordsList.add("عائلة");
                        wordsList.add("حلم");
                        wordsList.add("فرح");
                        wordsList.add("صحة");
                        wordsList.add("نجاح");
                        wordsList.add("تعلم");
                        wordsList.add("أمل");
                        wordsList.add("حرية");
                        wordsList.add("جمال");
                        wordsList.add("وئام");
                        wordsList.add("شجاعة");
                        wordsList.add("استمتاع");
                        wordsList.add("حكمة");
                        wordsList.add("امتنان");
                        wordsList.add("نمو");
                        wordsList.add("اصرار");
                        wordsList.add("حظ");
                        break;
                    case "Italian":
                        wordsList = new ArrayList<>();
                        wordsList.add("Ciao");
                        wordsList.add("Amore");
                        wordsList.add("Felicità");
                        wordsList.add("Amicizia");
                        wordsList.add("Famiglia");
                        wordsList.add("Sogno");
                        wordsList.add("Gioia");
                        wordsList.add("Salute");
                        wordsList.add("Successo");
                        wordsList.add("Studio");
                        wordsList.add("Speranza");
                        wordsList.add("Libertà");
                        wordsList.add("Bellezza");
                        wordsList.add("Armonia");
                        wordsList.add("Coraggio");
                        wordsList.add("Piacere");
                        wordsList.add("Saggezza");
                        wordsList.add("Gratitudine");
                        wordsList.add("Crescita");
                        wordsList.add("Persistenza");
                        wordsList.add("Fortuna");
                        break;
                    case "Franche":
                        wordsList = new ArrayList<>();
                        wordsList.add("bonjour");
                        wordsList.add("amour");
                        wordsList.add("joie");
                        wordsList.add("maison");
                        wordsList.add("famille");
                        wordsList.add("rêve");
                        wordsList.add("bonheur");
                        wordsList.add("santé");
                        wordsList.add("succès");
                        wordsList.add("apprendre");
                        wordsList.add("espoir");
                        wordsList.add("liberté");
                        wordsList.add("beauté");
                        wordsList.add("harmonie");
                        wordsList.add("courage");
                        wordsList.add("aimer");
                        wordsList.add("sagesse");
                        wordsList.add("gratitude");
                        wordsList.add("croissance");
                        wordsList.add("persévérance");
                        break;
                    default:
                        wordsList = new ArrayList<>();
                        wordsList.add("hellow");
                        wordsList.add("red");
                        wordsList.add("black");
                        wordsList.add("blue");
                        wordsList.add("hellow");
                        wordsList.add("red");
                        wordsList.add("black");
                        wordsList.add("blue");
                        wordsList.add("hellow");
                        wordsList.add("red");
                        wordsList.add("black");
                        wordsList.add("blue");
                        wordsList.add("hellow");
                        wordsList.add("red");
                        wordsList.add("black");
                        wordsList.add("blue");
                        break;
                    // code block
                }
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
        });










        // Load words from text file




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
            startNewGame();
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