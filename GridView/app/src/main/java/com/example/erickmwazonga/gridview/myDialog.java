package com.example.erickmwazonga.gridview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class myDialog extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dialog);

        Intent intent=new Intent();
        if(intent!=null){
            int imageId=intent.getIntExtra("countryImage",R.drawable.kenya);
            String countryName=intent.getStringExtra("countryName");

            ImageView imageView= (ImageView) findViewById(R.id.imageView2);
            imageView.setImageResource(imageId);
            TextView textView= (TextView) findViewById(R.id.textView);
            textView.setText("This Flag Belogs to "+countryName);
        }
    }
    public void closeDialog(View v){
        finish();;
    }
}
