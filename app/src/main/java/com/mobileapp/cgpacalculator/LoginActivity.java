package com.mobileapp.cgpacalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {

    EditText StdName, StdNum;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StdName = (EditText) findViewById(R.id.TxtName);
        StdNum = (EditText) findViewById(R.id.TxtStdNo);


        sharedPreferences = getApplication().getSharedPreferences("StudentList", 0);
        editor = sharedPreferences.edit();

        username=sharedPreferences.getString("username","");

        if (!(username.equalsIgnoreCase("")))
        {
            Intent intent =new Intent(this,MainActivity.class);
            startActivity(intent);
        }

    }

    public void fnLogin(View vw)
    {
        String strName = StdName.getText().toString();
        String strNum = StdNum.getText().toString();
            editor.putString("username", strName);
            editor.putString("matricno",strNum);
            editor.commit();
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);

    }
}

