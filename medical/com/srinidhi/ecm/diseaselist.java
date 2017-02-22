package com.srinidhi.ecm;

import android.app.ListActivity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;

public class diseaselist extends ListActivity {
    SQLiteDatabase Mydatabase;
    String[] abc;
    DBHandandler myDbHelper;
    ArrayList<String> stattionlist;

    /* renamed from: com.srinidhi.ecm.diseaselist.1 */
    class C00031 implements OnItemClickListener {
        C00031() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            String s1 = (String) diseaselist.this.stattionlist.get(position);
            Toast.makeText(diseaselist.this.getApplicationContext(), "You selected " + s1 + " disease", 1).show();
            Intent myIntent = new Intent(diseaselist.this.getApplicationContext(), tab.class);
            myIntent.putExtra("name", s1);
            diseaselist.this.startActivity(myIntent);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.myDbHelper = new DBHandandler(this);
        FetchingData();
        this.stattionlist = this.myDbHelper.getNames(this.Mydatabase);
        ListView lv = getListView();
        lv.setAdapter(new ArrayAdapter(getApplicationContext(), 17367043, this.stattionlist));
        lv.setOnItemClickListener(new C00031());
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
