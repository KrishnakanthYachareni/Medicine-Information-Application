package com.srinidhi.ecm;

import android.app.ListActivity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;

public class intermediate extends ListActivity {
    SQLiteDatabase Mydatabase;
    int i1;
    int i2;
    int i3;
    int i4;
    int i5;
    int i6;
    DBHandandler myDbHelper;
    ArrayList<Integer> f1s;
    String s1;
    String s10;
    String s11;
    String s2;
    String s3;
    String s4;
    String s5;
    String s6;
    String s7;
    String s8;
    String s9;
    ArrayList<String> sd;
    ArrayList<String> stattionlist;
    ArrayList<String> sw;

    /* renamed from: com.srinidhi.ecm.intermediate.1 */
    class C00041 implements OnItemClickListener {
        C00041() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            String s123 = (String) intermediate.this.sd.get(position);
            Toast.makeText(intermediate.this.getApplicationContext(), "You selected " + s123 + " disease", 1).show();
            Intent myIntent = new Intent(intermediate.this.getApplicationContext(), tab.class);
            myIntent.putExtra("name", s123);
            intermediate.this.startActivity(myIntent);
        }
    }

    public intermediate() {
        this.s2 = "";
        this.s3 = "";
        this.s4 = "";
        this.s5 = "";
        this.s6 = "";
        this.s7 = "";
        this.s8 = "";
        this.s9 = "";
        this.s10 = "";
        this.s11 = "";
        this.i1 = 0;
        this.i2 = 0;
        this.i3 = 0;
        this.i4 = 0;
        this.i5 = 0;
        this.i6 = 0;
    }

    public void onCreate(Bundle savedInstanceState) {
        int i;
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        this.sw = new ArrayList();
        if (b != null) {
            this.sw = b.getStringArrayList("value");
        }
        this.myDbHelper = new DBHandandler(this);
        FetchingData();
        this.f1s = this.myDbHelper.getdiagNames(this.Mydatabase, (String) this.sw.get(0), (String) this.sw.get(1), (String) this.sw.get(2), (String) this.sw.get(3));
        System.out.println("ajdfbfd " + this.f1s.get(0));
        for (i = 0; i < this.f1s.size(); i++) {
            System.out.println("Hey...?" + this.f1s.get(i));
            this.i1 = ((Integer) this.f1s.get(0)).intValue();
            this.i2 = ((Integer) this.f1s.get(1)).intValue();
            this.i3 = ((Integer) this.f1s.get(2)).intValue();
            this.i4 = ((Integer) this.f1s.get(3)).intValue();
        }
        this.sd = this.myDbHelper.getdiagids(this.Mydatabase, this.i1, this.i2, this.i3, this.i4);
        if (this.sd.isEmpty()) {
            Toast.makeText(getApplicationContext(), "NO Diseas with those symtomps", 0).show();
            String url = "https://www.google.com/search?q=" + ((String) this.sw.get(0)) + "," + ((String) this.sw.get(1)) + "," + ((String) this.sw.get(2)) + "," + ((String) this.sw.get(3));
            i = new Intent("android.intent.action.VIEW");
            i.setData(Uri.parse(url));
            startActivity(i);
            return;
        }
        ListView lv = getListView();
        lv.setAdapter(new ArrayAdapter(getApplicationContext(), 17367043, this.sd));
        lv.setOnItemClickListener(new C00041());
    }

    private void FetchingData() {
        try {
            this.myDbHelper.onCreateDataBase();
            try {
                this.myDbHelper.openDataBase();
                this.Mydatabase = this.myDbHelper.getWritableDatabase();
                System.out.println("executed");
            } catch (SQLException sqle) {
                throw sqle;
            }
        } catch (IOException e) {
            throw new Error("Unable to create database");
        }
    }
}
