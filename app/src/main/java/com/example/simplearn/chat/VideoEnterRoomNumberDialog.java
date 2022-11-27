package com.example.simplearn.chat;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.simplearn.R;
import com.example.simplearn.R;

public class VideoEnterRoomNumberDialog extends AppCompatDialogFragment {

    interface VideoEnterRoomNumberDialogListener {
        void share(String code);
        void joinMeeting(String code);
    }

    static VideoEnterRoomNumberDialog newInstance(){
        return new VideoEnterRoomNumberDialog();
    }

    private VideoEnterRoomNumberDialogListener listener;
    private EditText codeEdt;
    private Button shareBtn,joinMeetingBtn;


    @Override
    public void onAttach(@NonNull Context context) {
        try{
            listener = (VideoEnterRoomNumberDialogListener) context;
        }catch (Exception e){
            throw new RuntimeException("activity must implement VideoEnterRoomNumberDialogListener");
        }
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.room_number_dialog,container);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        codeEdt = view.findViewById(R.id.code_box);
        shareBtn = view.findViewById(R.id.share_btn);
        joinMeetingBtn = view.findViewById(R.id.join_btn);
        initClicks();
    }

    private void initClicks(){
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo need to check it
                Toast.makeText(getContext(),"need to talk about the logic",Toast.LENGTH_SHORT).show();
                dismissAllowingStateLoss();
            }
        });

        joinMeetingBtn.setOnClickListener(view -> joinMeetingClick());
    }

    private void joinMeetingClick(){
        String code = codeEdt.getText().toString();
        if(TextUtils.isEmpty(codeEdt.getText().toString())){
            Toast.makeText(getContext(),"Code cannot be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        listener.joinMeeting(code);
        dismissAllowingStateLoss();
    }
}
