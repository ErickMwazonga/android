package com.example.erickmwazonga.dialogfragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MyDialog extends DialogFragment implements View.OnClickListener{

    Button yes, no;
    Communicator communicator;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator= (Communicator) activity;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_my_dialog, null);
        yes= (Button) view.findViewById(R.id.buttonYes);
        no= (Button) view.findViewById(R.id.buttonNo);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        setCancelable(false);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonYes){
            communicator.onDialogMessage("Yes was clicked");
            //dismiss();

            startActivity(new Intent(getContext(),Main2Activity.class));

            //Intent i = new Intent(getContext(), Main2Activity.class);
            //startActivity(i);

            //startActivity(new Intent(getContext(),Main2Activity.class));
            //Toast.makeText(getActivity(),"Yes button was clicked",Toast.LENGTH_LONG).show();
        }else{
            communicator.onDialogMessage("No was clicked");
            dismiss();
            //Toast.makeText(getActivity(),"No button was clicked",Toast.LENGTH_LONG).show();
        }
    }
    interface  Communicator{
        public void onDialogMessage(String message);
    }
}
