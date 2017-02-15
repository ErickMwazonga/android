package com.example.erickmwazonga.myweek;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

Button buttonSendEmail,buttonViewMap,buttonLaunchMarket,buttonCalculator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSendEmail=(Button)findViewById(R.id.buttonSendEmail);
        buttonSendEmail.setOnClickListener(this);
        buttonViewMap=(Button)findViewById(R.id.buttonViewMap);
        buttonViewMap.setOnClickListener(this);
        buttonLaunchMarket=(Button)findViewById(R.id.buttonLaunchMarket);
        buttonLaunchMarket.setOnClickListener(this);
        buttonCalculator=(Button)findViewById(R.id.buttonCalculator);
        buttonCalculator.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        Intent chooser=null;

        //custom toast
        Toast toast=new Toast(this);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 0);

        LayoutInflater inflator=getLayoutInflater();
        View apperance=inflator.inflate(R.layout.activity_monday, (ViewGroup) findViewById(R.id.root));
        toast.setView(apperance);
        toast.show();


        if(v.getId()==R.id.buttonViewMap){
            intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:19.076,72.8777"));
            chooser=Intent.createChooser(intent,"Launch Map");
            startActivity(chooser);
        }
        if(v.getId()==R.id.buttonLaunchMarket){
            intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=dolphin.developers.com"));
            chooser=Intent.createChooser(intent,"Launch Market");
            startActivity(chooser);
        }

        if(v.getId()==R.id.buttonSendEmail){
            intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto"));
            String[] to={"erickmwazonga@gmail.com","dianneprinsescah@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL,to);
            intent.putExtra(Intent.EXTRA_SUBJECT,"Hello baby");
            intent.putExtra(Intent.EXTRA_TEXT,"I love you baby");
            intent.setType("message/rcf822");
            chooser=Intent.createChooser(intent,"Send Email");
            startActivity(chooser);
        }
        if(v.getId()==R.id.buttonCalculator){
            startActivity(new Intent(this, Calculator.class));
        }


    }
}
