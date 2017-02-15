package com.example.erickmwazonga.flexiblefragment;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{

    FragmentManager manager;
    TextView message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager=getSupportFragmentManager();
        message= (TextView) findViewById(R.id.textViewMessage);
        manager.addOnBackStackChangedListener(this);
    }
    public void addA(View v){
        FragmentA fa = new FragmentA();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.group,fa,"A");
        transaction.addToBackStack("addA");
        transaction.commit();
    }
    public void removeA(View v){
        FragmentA fa= (FragmentA) manager.findFragmentByTag("A");
        FragmentTransaction transaction=manager.beginTransaction();
        if(fa!=null){
            transaction.remove(fa);
            transaction.addToBackStack("removeA");
            transaction.commit();
        }else{
            Toast.makeText(this,"Fragment A was not added before",Toast.LENGTH_SHORT).show();
        }
    }
    public void replaceAB(View v){
        Fragmentb fb=new Fragmentb();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.group,fb,"B");
        transaction.addToBackStack("replaceAB");
        transaction.commit();
    }
    public void addB(View v){
        Fragmentb fb = new Fragmentb();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.group,fb,"B");
        transaction.addToBackStack("addB");
        transaction.commit();
    }
    public void removeB(View v){
        Fragmentb fb= (Fragmentb) manager.findFragmentByTag("B");
        FragmentTransaction transaction=manager.beginTransaction();
        if(fb!=null){
            transaction.remove(fb);
            transaction.addToBackStack("removeB");
            transaction.commit();
        }else{
            Toast.makeText(this,"Fragment B was not added before",Toast.LENGTH_SHORT).show();
        }
    }
    public void replaceBA(View v){
        //Fragmentb fb=new Fragmentb();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.group,manager.findFragmentByTag("A"),"A");
        transaction.addToBackStack("replaceBA");
        transaction.commit();
    }
    public void attachA(View v){
        FragmentA fa= (FragmentA) manager.findFragmentByTag("A");
        FragmentTransaction transaction=manager.beginTransaction();
        if(fa!=null){
            transaction.attach(fa);
            transaction.addToBackStack("attachA");
            transaction.commit();
        }else{}
    }
    public void detachA(View v){
        FragmentA fa= (FragmentA) manager.findFragmentByTag("A");
        FragmentTransaction transaction=manager.beginTransaction();
        if(fa!=null){
            transaction.detach(fa);
            transaction.addToBackStack("detachA");
            transaction.commit();
        }else{}
    }
    public void back(){
        manager.popBackStack();
    }
    public void popAddB(){
        //manager.popBackStack("addB",0);
        manager.popBackStack("addB",FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onBackStackChanged() {
        message.setText(message.getText()+"\n");
        message.setText(message.getText()+"The current state on back stack");

        int count=manager.getBackStackEntryCount();
        for(int i=count-1;i>=0;i--){
            FragmentManager.BackStackEntry entry=manager.getBackStackEntryAt(i);
            message.setText(message.getText()+" "+entry.getName()+"\n");
        }
        message.setText(message.getText()+"\n");
    }
}
