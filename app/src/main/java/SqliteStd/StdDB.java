package SqliteStd;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import model.StdDatabaseModel;

public class StdDB extends SQLiteOpenHelper
{
    public static final String dbName="SubjectDB";
    public static final String dbTable="SubjectTB";
    public static final String colSubId="SubjectId";
    public static final String colSubCode="SubjectCode";
    public static final String colSubName="SubjectName";
    public static final String colSubCredit="SubjectCredit";
    public static final String colSubGrade="SubjectGrade";


    public static  final String strCrtTblSub= "CREATE TABLE "+dbTable+"("+colSubId+"INTEGER PRIMARY KEY ,"+colSubCode+"TEXT ,"+colSubName+"TEXT,"+colSubGrade+"TEXT,"+colSubCredit+"INTEGER)";
    public static  final String strDrpTblSub="DROP TABLE IF EXISTS "+dbTable;

    public StdDB(Context context)
    {
        super(context,dbName,null,1);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(strCrtTblSub);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int i, int i1)
    {
        sqLiteDatabase.execSQL(strDrpTblSub);
        onCreate(sqLiteDatabase);
    }

    public float fnInsert(StdDatabaseModel stdDatabaseModel)
    {
        float result = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(colSubCode, stdDatabaseModel.getStrSubCode());
        values.put(colSubName, stdDatabaseModel.getStrSubName());
        values.put(colSubCredit, stdDatabaseModel.getSubCredit());
        values.put(colSubGrade, stdDatabaseModel.getStrGrade());

        result = db.insert(dbTable, null, values);
        return result;


    }

    public List<StdDatabaseModel> fnGetAllSubject()
    {
        List<StdDatabaseModel> SubjectList=new ArrayList<>();
        String strSelAll="Select * from "+dbTable;
        Cursor cursor=this.getReadableDatabase().rawQuery(strSelAll,null);
        if(cursor.moveToFirst())
        {
            do{
                StdDatabaseModel stdDatabaseModel=new StdDatabaseModel();
                stdDatabaseModel.setStrGrade(cursor.getString(cursor.getColumnIndex(colSubGrade)));
                stdDatabaseModel.setSubCredit(cursor.getInt(cursor.getColumnIndex(colSubCredit)));
                SubjectList.add(stdDatabaseModel);
            }while(cursor.moveToNext());
        }
        return SubjectList;
    }




    }


