package com.example.simplearn.chat;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simplearn.Messages;
import com.example.simplearn.MessagesAdapter;
import com.example.simplearn.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SpecificChat extends AppCompatActivity implements VideoEnterRoomNumberDialog.VideoEnterRoomNumberDialogListener {

    EditText mgetmessage;
    ImageButton msendmessagebutton;

    CardView msendmessagecardview;
    androidx.appcompat.widget.Toolbar mtoolbarofspecificchat;
    ImageView mimageviewofspecificuser;
    TextView mnameofspecificuser;

    private String enteredmessage;
    Intent intent;
    String mrecievername, sendername, mrecieveruid, msenderuid;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    String senderroom, recieverroom;

    ImageButton mbackbuttonofspecificchat;
    ImageView videoCallBtn;

    RecyclerView mmessagerecyclerview;

    String currenttime;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;

    MessagesAdapter messagesAdapter;
    ArrayList<Messages> messagesArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specificchat);

        mgetmessage = findViewById(R.id.getmessage);
        msendmessagecardview = findViewById(R.id.carviewofsendmessage);
        msendmessagebutton = findViewById(R.id.imageviewsendmessage);
        videoCallBtn = findViewById(R.id.video_call_btn);
        mtoolbarofspecificchat = findViewById(R.id.toolbarofspecificchat);
        mnameofspecificuser = findViewById(R.id.Nameofspecificuser);
        mimageviewofspecificuser = findViewById(R.id.specificuserimageinimageview);
        mbackbuttonofspecificchat = findViewById(R.id.backbuttonofspecificchat);

        messagesArrayList = new ArrayList<>();
        mmessagerecyclerview = findViewById(R.id.recyclerviewofspecific);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mmessagerecyclerview.setLayoutManager(linearLayoutManager);
        messagesAdapter = new MessagesAdapter(SpecificChat.this, messagesArrayList);
        mmessagerecyclerview.setAdapter(messagesAdapter);


        intent = getIntent();

        setSupportActionBar(mtoolbarofspecificchat);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("hh:mm a");


        msenderuid = firebaseAuth.getUid();
        mrecieveruid = getIntent().getStringExtra("receiveruid");
        mrecievername = getIntent().getStringExtra("name");


        senderroom = msenderuid + mrecieveruid;
        recieverroom = mrecieveruid + msenderuid;

        DatabaseReference databaseReference = firebaseDatabase.getReference().child("chats").child(senderroom).child("messages");
        messagesAdapter = new MessagesAdapter(SpecificChat.this, messagesArrayList);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messagesArrayList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Messages messages = snapshot1.getValue(Messages.class);
                    messagesArrayList.add(messages);
                }
                messagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        mbackbuttonofspecificchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        mnameofspecificuser.setText(mrecievername);
        String uri = intent.getStringExtra("imageuri");
        if (uri.isEmpty()) {
            Toast.makeText(getApplicationContext(), "null is recieved", Toast.LENGTH_SHORT).show();
        } else {
            Picasso.get().load(uri).into(mimageviewofspecificuser);
        }


        msendmessagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                enteredmessage = mgetmessage.getText().toString();
                if (enteredmessage.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter message first", Toast.LENGTH_SHORT).show();
                } else {
                    Date date = new Date();
                    currenttime = simpleDateFormat.format(calendar.getTime());
                    Messages messages = new Messages(enteredmessage, firebaseAuth.getUid(), date.getTime(), currenttime);
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    firebaseDatabase.getReference().child("chats")
                            .child(senderroom)
                            .child("messages")
                            .push().setValue(messages).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            firebaseDatabase.getReference()
                                    .child("chats")
                                    .child(recieverroom)
                                    .child("messages")
                                    .push()
                                    .setValue(messages).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });
                        }
                    });

                    mgetmessage.setText(null);
                }
            }
        });
        initJitsiMeet();
        videoCallBtn.setOnClickListener(view -> onClickVideoCall());
    }

    private void initJitsiMeet() {
        URL serverURL;
        try {
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions =
                    new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(serverURL)
                            .setWelcomePageEnabled(false)
                            .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void onClickVideoCall() {
        VideoEnterRoomNumberDialog.newInstance()
                .show(getSupportFragmentManager(), VideoEnterRoomNumberDialog.class.getSimpleName());
    }

    @Override
    public void share(String code) {

    }

    @Override
    public void joinMeeting(String code) {
        JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                .setRoom(code)
                .setWelcomePageEnabled(false)
                .build();

        JitsiMeetActivity.launch(this, options);
    }

    @Override
    public void onStart() {
        super.onStart();
        messagesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (messagesAdapter != null) {
            messagesAdapter.notifyDataSetChanged();
        }
    }
}