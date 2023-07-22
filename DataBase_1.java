package com.example.data_base_app;

import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.GestureDescription;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog.Builder;

import java.security.KeyStore;

public class DataBase_1 extends AppCompatActivity {
    SQLiteDatabase db;
    EditText e1,e2,e3;
    Button save,show,upd,dlt;
    private char title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base1);

        e1 = (EditText) findViewById(R.id.empid);
        e2 = (EditText) findViewById(R.id.empname);
        e3 = (EditText) findViewById(R.id.empsalary);

        save = (Button) findViewById(R.id.bt1);
        show = (Button) findViewById(R.id.bt2);
        upd = (Button) findViewById(R.id.bt3);
        dlt = (Button) findViewById(R.id.bt4);

        db = openOrCreateDatabase("emprecords", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS empregi (empid VARCHAR,empname VARCHAR,empsalary VARCHAR);");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("INSERT INTO empregi VALUES('"+e1.getText().toString()+"','"+e2.getText().toString()+"','"+e3.getText().toString()+"')");
                msgalert("SUCCESS", "Your Data Inserted Successfully");
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = db.rawQuery("SELECT * FROM empregi",null);

                if(c.getCount()==0)
                {
                    msgalert("Empty..!!","There is no record in Table");
                    return;
                }

                StringBuffer buf = new StringBuffer();
                    while(c.moveToNext())
                    {
                        buf.append("Emp Id:"+c.getString(0)+"\n");
                        buf.append("Emp Name:"+c.getString(1)+"\n");
                        buf.append("Emp Salary:"+c.getString(2)+"\n\n");
                    }
                    msgalert("Emp Details:- ",buf.toString());
            }
        });

        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = db.rawQuery("SELECT * FROM empregi WHERE empid='"+e1.getText().toString()+"'",null);
                if (c.moveToNext())
                {
                    db.execSQL("UPDATE empregi SET empname= '"+e2.getText().toString()+"',empsalary='"+e3.getText().toString()+"' WHERE empid='"+e1.getText().toString()+"'");
                }
                else
                {
                    msgalert("Empty..!!","There is No Record In The Table");
                }
                }
        });

        dlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = db.rawQuery("SELECT * FROM empregi WHERE empid='"+e1.getText().toString()+"'",null);
                if (c.moveToNext())
                {
                    db.execSQL("UPDATE empregi SET empname= '"+e2.getText().toString()+"',empsalary='"+e3.getText().toString()+"' WHERE empid='"+e1.getText().toString()+"'");
                }
                else
                {
                    msgalert("Empty..!!","There is No Record In The Table");
                }
            }
        });
    }

    private void msgalert(String Title, String msg) {
        Builder b = new Builder(this);
        b.setCancelable(true);
        b.setTitle(title);
        b.setMessage(msg);
        b.show();
    }

    public void cleardata()
    {
        e1.setText(" ");
        e2.setText(" ");
        e3.setText(" ");
    }
}