package com.example.erickmwazonga.fragmentflexible2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fragmentb extends Fragment {
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragmentb, container, false);
        textView= (TextView) view.findViewById(R.id.textView);
        return view;
    }
    public void changeData(int index){
        String [] descriptions=getResources().getStringArray(R.array.descriptions);
        textView.setText(descriptions[index]);
    }

}
