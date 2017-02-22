package com.srinidhi.ecm;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import java.io.IOException;
import java.sql.SQLException;

public class medication extends Activity {
    SQLiteDatabase Mydatabase;
    DBHandandler myDbHelper;
    String s1;
    String s2;
    String s3;
    String stattionlist;
    TextView tv;
    TextView tv1;

    public medication() {
        this.s1 = "";
        this.s2 = "";
        this.s3 = "";
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.myDbHelper = new DBHandandler(this);
        try {
            FetchingData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setContentView(C0002R.layout.information);
        this.tv = (TextView) findViewById(C0002R.id.TextView02);
        this.tv1 = (TextView) findViewById(C0002R.id.TextView01);
        Bundle b = getIntent().getExtras();
        this.tv1.setText("Prevention and Medication");
        if (b != null) {
            this.s1 = b.getString("name");
        }
        this.stattionlist = this.myDbHelper.getmInfo(this.Mydatabase, this.s1);
        this.tv.setText(this.stattionlist);
    }

    private void FetchingData() throws SQLException {
        try {
            this.myDbHelper.onCreateDataBase();
            this.myDbHelper.openDataBase();
            this.Mydatabase = this.myDbHelper.getWritableDatabase();
            System.out.println("executed");
        } catch (IOException e) {
            throw new Error("Unable to create database");
        }
    }
}
