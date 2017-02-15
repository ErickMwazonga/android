package com.example.erickmwazonga.alertdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

/**
 * Created by Erick Mwazonga on 6/7/2016.
 */
public class AlertClass extends DialogFragment{
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setTitle("Awesome Dialog");

        //multiple item click
        builder.setMultiChoiceItems(R.array.days, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(getActivity(),"Item from position"+which+" was selected "+isChecked,Toast.LENGTH_LONG).show();
            }
        });

        //display a message to the dialog
        //builder.setMessage("Do you like this Dialog");


        //display a list inside the dialog
       /* builder.setItems(R.array.days, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               /* if(which==0){
                   startActivity(new Intent(getContext(),Main2Activity.class));
                }else{}

                Toast.makeText(getActivity(),"Item was selected :"+which,Toast.LENGTH_LONG).show();
            }
        });*/

        //Negative Button was clicked
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"Negative Button was clicked",Toast.LENGTH_LONG).show();
            }
        });

        //Positive Button was clicked
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"Positive Button was clicked",Toast.LENGTH_LONG).show();
            }
        });
        Dialog dialog=builder.create();
        return dialog;
    }
}
