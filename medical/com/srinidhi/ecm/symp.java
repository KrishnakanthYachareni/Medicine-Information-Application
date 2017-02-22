package com.srinidhi.ecm;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;

public class symp extends Activity {
    SQLiteDatabase Mydatabase;
    Button b1;
    DBHandandler myDbHelper;
    String f2s;
    String s1;
    String s3;
    int ss;
    ArrayList<String> stattionlist;
    ArrayList<String> sw;

    /* renamed from: com.srinidhi.ecm.symp.1 */
    class C00061 implements OnClickListener {
        C00061() {
        }

        public void onClick(View v) {
            for (int i = 0; i < symp.this.sw.size(); i++) {
                System.out.println("vjjjjjjjjjjjjjjjjjjjjjjjjjjj " + ((String) symp.this.sw.get(i)));
            }
            if (symp.this.sw.size() < 4) {
                Toast.makeText(symp.this.getApplicationContext(), "Please select minimum 4 symptoms to perform diagnosis ", 0).show();
                return;
            }
            Intent i2 = new Intent(symp.this, intermediate.class);
            i2.putExtra("value", symp.this.sw);
            symp.this.startActivity(i2);
        }
    }

    /* renamed from: com.srinidhi.ecm.symp.2 */
    class C00072 implements OnItemClickListener {
        private final /* synthetic */ ListView val$lv;

        C00072(ListView listView) {
            this.val$lv = listView;
        }

        public void onItemClick(AdapterView<?> adapterView, View arg1, int arg2, long arg3) {
            if (this.val$lv.isItemChecked(arg2)) {
                symp.this.sw.add(this.val$lv.getItemAtPosition(arg2).toString());
            } else {
                symp.this.sw.remove(this.val$lv.getItemAtPosition(arg2).toString());
            }
        }
    }

    public symp() {
        this.f2s = "";
        this.s3 = "";
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0002R.layout.symptoms);
        this.b1 = (Button) findViewById(C0002R.id.button1);
        this.sw = new ArrayList();
        this.b1.setOnClickListener(new C00061());
        this.myDbHelper = new DBHandandler(this);
        FetchingData();
        this.stattionlist = this.myDbHelper.getsymptoms(this.Mydatabase);
        ListView lv = (ListView) findViewById(C0002R.id.listView1);
        ArrayAdapter<String> lv1 = new ArrayAdapter(getApplicationContext(), 17367056, this.stattionlist);
        lv.setChoiceMode(2);
        lv.setAdapter(lv1);
        lv.setOnItemClickListener(new C00072(lv));
    }

    public void FetchingData() {
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
