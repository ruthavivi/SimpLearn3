package com.example.simplearn;

//test
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.simplearn.chat.SpecificChat;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;


public class ChatFragment extends firebasemodel implements SwipeRefreshLayout.OnRefreshListener {


    private FirebaseFirestore firebaseFirestore;
    LinearLayoutManager linearLayoutManager;
    private FirebaseAuth firebaseAuth;






    ImageView mimageviewofuser;

    FirestoreRecyclerAdapter<firebasemodel, NoteViewHolder> chatAdapter;

    RecyclerView mrecyclerview;


    public String motherL,learnL;
    private SwipeRefreshLayout mSwipeRefreshLayout;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chatfragment, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        mrecyclerview = v.findViewById(R.id.recyclerview);
        mSwipeRefreshLayout = v.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        DocumentReference documentReference = firebaseFirestore.collection("Users").document(firebaseAuth.getUid());

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value == null || !value.exists()) { // user left the save-profile page when registered
                    userprofile profile = new userprofile();
                    profile.setMotherlanguage("Hebrew");
                    profile.setLearnlanguage("Spanish");
                    profile.setUserUID(firebaseAuth.getUid());
                    firebaseFirestore.collection("Users")
                            .document(firebaseAuth.getUid())
                            .set(profile);
                    return;
                }
                userprofile muserprofile= value.toObject(userprofile.class);
                if(muserprofile==null)return; // unknown error
                motherL = muserprofile.getMotherlanguage();
                learnL = muserprofile.getLearnlanguage();
                // Create the chat adapter whenever the user languages changes (or at first entry)
                createChatAdapter();
            }
        });




        mrecyclerview.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mrecyclerview.setLayoutManager(linearLayoutManager);
        return v;
    }


    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView particularusername;
        private TextView statusofuser;
        private TextView bio;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            bio = itemView.findViewById(R.id.bio);
            particularusername = itemView.findViewById(R.id.nameofuser);
            statusofuser = itemView.findViewById(R.id.statusofuser);
            mimageviewofuser = itemView.findViewById(R.id.imageviewofuser);
        }
    }

    private void createChatAdapter() {
        // if chat adapter already exists, stop listening and create new adapter with new user settings
        if(chatAdapter!=null && chatAdapter.getSnapshots().isListening()) {
            chatAdapter.stopListening();
        }
        /*
            The user only sees users that their mother language
            are the same with as the current user's learning language
         */
        Query query = firebaseFirestore.collection("Users")
                .whereNotEqualTo("uid", firebaseAuth.getUid())
                .whereEqualTo("motherlanguage", learnL)
                .whereEqualTo("learnlanguage", motherL);


        FirestoreRecyclerOptions<firebasemodel> allusername = new FirestoreRecyclerOptions.Builder<firebasemodel>()
                .setQuery(query, firebasemodel.class).build();

        chatAdapter = new FirestoreRecyclerAdapter<firebasemodel, NoteViewHolder>(allusername) {
            @Override
            protected void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i, @NonNull firebasemodel firebasemodel) {

                noteViewHolder.particularusername.setText(firebasemodel.getName());
                String uri = firebasemodel.getImage();
                noteViewHolder.bio.setText(firebasemodel.getBio());
                Picasso.get().load(uri).into(mimageviewofuser);
                if (firebasemodel.getStatus().equals("Online")) {

                    noteViewHolder.statusofuser.setText(firebasemodel.getStatus());
                    noteViewHolder.statusofuser.setTextColor(Color.GREEN);
                } else {
                    noteViewHolder.statusofuser.setText(firebasemodel.getStatus());
                }

                noteViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), SpecificChat.class);
                        intent.putExtra("name", firebasemodel.getName());
                        intent.putExtra("receiveruid", firebasemodel.getUid());
                        intent.putExtra("imageuri", firebasemodel.getImage());
                        intent.putExtra("motherLanguage", firebasemodel.getMotherlanguage());
                        intent.putExtra("learnLanguage", firebasemodel.getLearnlanguage());
                        intent.putExtra("age", firebasemodel.getAge());
                        intent.putExtra("bio",firebasemodel.getBio());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatviewlayout, parent, false);
                return new NoteViewHolder(view);
            }
        };
        mrecyclerview.setAdapter(chatAdapter);
        chatAdapter.startListening();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (chatAdapter != null) {
            chatAdapter.stopListening();
        }
    }


    @Override
    public void onRefresh() {
        // Refresh data here
        chatAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }
}




//
//    private FirebaseFirestore firebaseFirestore;
//    LinearLayoutManager linearLayoutManager;
//    private FirebaseAuth firebaseAuth;
//
//
//
//
//
//
//    ImageView mimageviewofuser;
//
//    FirestoreRecyclerAdapter<firebasemodel, NoteViewHolder> chatAdapter;
//
//    RecyclerView mrecyclerview;
//
//    @NonNull firebasemodel firebasemodel1;
//
//    public String motherL,learnL;
//
//
//
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.chatfragment, container, false);
//
//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseFirestore = FirebaseFirestore.getInstance();
//        mrecyclerview = v.findViewById(R.id.recyclerview);
//        DocumentReference documentReference = firebaseFirestore.collection("Users").document(firebaseAuth.getUid());
//
//        documentReference.addSnapshotListener((value, error) -> {
//            if(value == null) return;
//            userprofile muserprofile= value.toObject(userprofile.class);
//
//            if(muserprofile == null) {
//                userprofile profile = new userprofile();
//                profile.setUserUID(firebaseAuth.getUid());
//                firebaseFirestore.collection("Users")
//                        .document(firebaseAuth.getUid())
//                        .set(profile);
//                return;
//            }
//            motherL = muserprofile.getMotherlanguage();
//            learnL = muserprofile.getLearnlanguage();
//            attachChats(learnL, motherL);
//        });
//
//
//
//        mrecyclerview.setHasFixedSize(true);
//        linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
//        mrecyclerview.setLayoutManager(linearLayoutManager);
//        return v;
//    }
//
//    private void attachChats(String filterByLearnLanguage, String filterByMotherLanguage) {
//        Query query = firebaseFirestore.collection("Users");
//               /* .whereNotEqualTo("uid", firebaseAuth.getUid());*/
//
//        /*if(filterByLearnLanguage != null && !filterByLearnLanguage.isEmpty())
//            query = query.whereEqualTo("learnlanguage", filterByLearnLanguage);*/
//      /*  if(filterByMotherLanguage != null && !filterByMotherLanguage.isEmpty())
//            query = query.whereEqualTo("motherlanguage", filterByLearnLanguage);
//*/
//        FirestoreRecyclerOptions<firebasemodel> allusername = new FirestoreRecyclerOptions
//                .Builder<firebasemodel>()
//                .setQuery(query, firebasemodel.class).build();
//
//        chatAdapter = new FirestoreRecyclerAdapter<firebasemodel, NoteViewHolder>(allusername) {
//            @Override
//            protected void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i, @NonNull firebasemodel firebasemodel) {
//
//                noteViewHolder.particularusername.setText(firebasemodel.getName());
//                String uri = firebasemodel.getImage();
//                noteViewHolder.bio.setText(firebasemodel.getBio());
//                Picasso.get().load(uri).into(mimageviewofuser);
//                if (firebasemodel.getStatus().equals("Online")) {
//
//                    noteViewHolder.statusofuser.setText(firebasemodel.getStatus());
//                    noteViewHolder.statusofuser.setTextColor(Color.GREEN);
//                } else {
//                    noteViewHolder.statusofuser.setText(firebasemodel.getStatus());
//                }
//
//                noteViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(getActivity(), SpecificChat.class);
//                        intent.putExtra("name", firebasemodel.getName());
//                        intent.putExtra("receiveruid", firebasemodel.getUid());
//                        intent.putExtra("imageuri", firebasemodel.getImage());
//                        startActivity(intent);
//                    }
//                });
//            }
//
//            @NonNull
//            @Override
//            public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatviewlayout, parent, false);
//                return new NoteViewHolder(view);
//            }
//        };
//        mrecyclerview.setAdapter(chatAdapter);
//        chatAdapter.startListening();
//    }
//
//
//    public class NoteViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView particularusername;
//        private TextView statusofuser;
//        private TextView bio;
//
//
//        public NoteViewHolder(@NonNull View itemView) {
//            super(itemView);
//            bio = itemView.findViewById(R.id.bio);
//            particularusername = itemView.findViewById(R.id.nameofuser);
//            statusofuser = itemView.findViewById(R.id.statusofuser);
//            mimageviewofuser = itemView.findViewById(R.id.imageviewofuser);
//        }
//    }
//
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (chatAdapter != null) {
//            chatAdapter.stopListening();
//        }
//    }
//}
//
