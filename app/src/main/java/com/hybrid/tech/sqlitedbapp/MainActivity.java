package com.hybrid.tech.sqlitedbapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SQLiteDatabase db;
    private Cursor nameCursor;
    private static int i=0;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
         editText=(EditText)findViewById(R.id.insertNames);

        try {

            SQLiteOpenHelper help = new NameSaveHelper(this);
            db = help.getReadableDatabase();

        } catch (SQLiteException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }




    }

    public void onSaveClicked(View view)
    {
        String str;
str=editText.getText().toString();
        ContentValues name = new ContentValues();
        name.put("NAME", str);
        db.insert("ALLNAMES", null, name);
        Toast.makeText(this,"Saved", Toast.LENGTH_LONG).show();

    }

    public void onShowClicked(View view)
    {
        nameCursor = db.rawQuery("SELECT * FROM ALLNAMES", null);
        nameCursor.moveToNext();

        StringBuilder builder=new StringBuilder();

        for(int j=0;j<nameCursor.getCount();j++)
        {


            builder.append(nameCursor.getString(1));
            builder.append("\n");
            nameCursor.moveToNext();
        }
        TextView tv=(TextView)findViewById(R.id.names);
        tv.setText(builder.toString());

    }


}
