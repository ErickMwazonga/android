package com.example.erickmwazonga.interfragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragmentb extends Fragment {


   TextView textViewShow;
    String data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragmentb, container, false);
        if(savedInstanceState==null){
           // data.equals(null);
        }else{
            data=savedInstanceState.getString("data");
            textViewShow= (TextView) view.findViewById(R.id.textViewShow);
            textViewShow.setText(data);
        }
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textViewShow= (TextView) getActivity().findViewById(R.id.textViewShow);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("data",data);
    }

    public void changeText(String data){
        this.data=data;
        textViewShow.setText(data);
    }
}
