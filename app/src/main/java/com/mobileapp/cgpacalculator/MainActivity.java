package com.mobileapp.cgpacalculator;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import SqliteStd.StdDB;
import model.StdDatabaseModel;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView TxtVwCGPA;
    EditText TxtSubCode,TxtSubName,TxtCredit;
    Spinner spinner;
    StdDatabaseModel databaseModel;
    StdDB stdDB;
    ArrayList<StdDatabaseModel> subjectlists;
    Double subCredit;
    String subGrade;
    public Double totalSubjectCredit=0.00;
    public Double gradePointer=0.00;
    public Double subpointer=0.00;
    public Double totalSubPointer=0.00;
    public Double CGPA=0.00;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=getApplicationContext().getSharedPreferences("StudentList",0);
        username=sharedPreferences.getString("username",null);

        Toast.makeText(getApplicationContext(),"Welcome "+username,Toast.LENGTH_SHORT).show();

        TxtSubCode=findViewById(R.id.TxtSubCode);
        TxtSubName=findViewById(R.id.TxtSubName);
        TxtCredit=findViewById(R.id.TxtCredit);
        TxtVwCGPA=findViewById(R.id.TxtVwCGPA);
        spinner=findViewById(R.id.spinnerGrade);

        stdDB=new StdDB(getApplicationContext());
    }

    @SuppressLint("ResourceType")
    public void fnSave(View view)
    {
        AlertDialog.Builder alertBuilder= new AlertDialog.Builder(this);
        alertBuilder.setTitle("Are you sure want to add ?");
        alertBuilder.setPositiveButton(getString(R.string.btnPositive), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                totalSubPointer=0.00;
                totalSubjectCredit=0.00;
                databaseModel = new StdDatabaseModel();
                databaseModel.setStrSubCode(TxtSubCode.getText().toString());
                databaseModel.setStrSubName(TxtSubName.getText().toString());
                databaseModel.setSubCredit(Integer.parseInt(TxtCredit.getText().toString()));
                databaseModel.setStrGrade(String.valueOf(spinner.getSelectedItem()));

                stdDB.fnInsert(databaseModel);
                Toast.makeText(getApplicationContext(),"Subject saved",Toast.LENGTH_SHORT).show();

                subjectlists=(ArrayList<StdDatabaseModel>)stdDB.fnGetAllSubject();

                for(StdDatabaseModel sublist:subjectlists)
                {
                    subCredit=Double.valueOf(sublist.getSubCredit());
                    subGrade=sublist.getStrGrade();
                    totalSubjectCredit+=subCredit;

                    if(subGrade.equalsIgnoreCase("A"))
                    {
                        gradePointer=4.00;
                        subpointer=subCredit*gradePointer;
                    }
                    else if(subGrade.equalsIgnoreCase("A-"))
                    {
                        gradePointer=3.70;
                        subpointer=subCredit*gradePointer;
                    }
                    else if(subGrade.equalsIgnoreCase("B+"))
                    {
                        gradePointer=3.30;
                        subpointer=subCredit*gradePointer;
                    }
                    else if(subGrade.equalsIgnoreCase("B"))
                    {
                        gradePointer=3.00;
                        subpointer=subCredit*gradePointer;
                    }
                    else if(subGrade.equalsIgnoreCase("B-"))
                    {
                        gradePointer=2.70;
                        subpointer=subCredit*gradePointer;
                    }
                    else if(subGrade.equalsIgnoreCase("C+"))
                    {
                        gradePointer=2.30;
                        subpointer=subCredit*gradePointer;
                    }
                    else if(subGrade.equalsIgnoreCase("C"))
                    {
                        gradePointer=2.00;
                        subpointer=subCredit*gradePointer;
                    }
                    else if(subGrade.equalsIgnoreCase("C-"))
                    {
                        gradePointer=1.70;
                        subpointer=subCredit*gradePointer;
                    }
                    else if(subGrade.equalsIgnoreCase("D+"))
                    {
                        gradePointer=1.30;
                        subpointer=subCredit*gradePointer;
                    }
                    else if(subGrade.equalsIgnoreCase("D"))
                    {
                        gradePointer=1.00;
                        subpointer=subCredit*gradePointer;
                    }
                    else if(subGrade.equalsIgnoreCase("E"))
                    {
                        gradePointer=0.00;
                        subpointer=subCredit*gradePointer;
                    }

                    totalSubPointer +=subpointer;
                }

                CGPA=totalSubPointer/totalSubjectCredit;
                TxtVwCGPA.setText("Your current CGPA  :"+CGPA);

            }


        });

        alertBuilder.setNegativeButton(getString(R.string.btnNegative), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertBuilder.show();
    }
}
