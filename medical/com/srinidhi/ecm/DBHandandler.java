package com.srinidhi.ecm;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHandandler extends SQLiteOpenHelper {
    private static String DB_NAME;
    private static String DB_PATH;
    private final Context myContext;
    String f0s;
    ArrayList<String> sw;

    static {
        DB_PATH = "/data/data/com.srinidhi.ecm/databases/";
        DB_NAME = "medicalinfo.sqlite";
    }

    public DBHandandler(Context context) {
        super(context, DB_NAME, null, 1);
        this.f0s = "";
        this.myContext = context;
    }

    public int onCreateDataBase() throws IOException {
        if (checkDatabase()) {
            return 0;
        }
        System.out.println("onCreateDataBase method execution starts");
        getReadableDatabase();
        copyDataBase();
        return 1;
    }

    private boolean checkDatabase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, 0);
        } catch (Exception e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        if (checkDB != null) {
            return true;
        }
        return false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = this.myContext.getAssets().open(DB_NAME);
        OutputStream myOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] buffer = new byte[1024];
        while (true) {
            int length = myInput.read(buffer);
            if (length <= 0) {
                myOutput.flush();
                myOutput.close();
                myInput.close();
                return;
            }
            myOutput.write(buffer, 0, length);
        }
    }

    public void onCreate(SQLiteDatabase db) {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void openDataBase() throws SQLException {
        SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, 1);
    }

    public ArrayList<String> getNames(SQLiteDatabase db) {
        ArrayList<String> list = new ArrayList();
        list.add("Select Disease Name");
        Cursor c = db.rawQuery("SELECT distinct dname FROM diseasetable", null);
        if (c != null) {
            c.moveToFirst();
            do {
                list.add(c.getString(c.getColumnIndex("dname")));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public ArrayList<String> getsymptoms(SQLiteDatabase mydatabase2) {
        ArrayList<String> list = new ArrayList();
        list.add("Select Symptom name Name");
        Cursor c = mydatabase2.rawQuery("SELECT distinct sname FROM symptoms order by sname ASC", null);
        if (c != null) {
            c.moveToFirst();
            do {
                list.add(c.getString(c.getColumnIndex("sname")));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public String getInfo(SQLiteDatabase mydatabase2, String s1) {
        Cursor c = mydatabase2.rawQuery("SELECT  dinfo FROM diseasetable  where dname='" + s1 + "' ", null);
        if (c != null && c.moveToFirst()) {
            this.f0s = c.getString(c.getColumnIndex("dinfo"));
        }
        c.close();
        return this.f0s;
    }

    public String getmInfo(SQLiteDatabase mydatabase2, String s1) {
        Cursor c = mydatabase2.rawQuery("SELECT  minfo FROM diseasetable  where dname='" + s1 + "' ", null);
        if (c != null && c.moveToFirst()) {
            this.f0s = c.getString(c.getColumnIndex("minfo"));
            System.out.print("hey.......how r u" + this.f0s);
        }
        c.close();
        return this.f0s;
    }

    public String getsInfo(SQLiteDatabase mydatabase2, String s1) {
        Cursor c = mydatabase2.rawQuery("SELECT  sinfo FROM diseasetable  where dname='" + s1 + "' ", null);
        if (c != null && c.moveToFirst()) {
            this.f0s = c.getString(c.getColumnIndex("sinfo"));
            System.out.print("hey.......how r u" + this.f0s);
        }
        c.close();
        return this.f0s;
    }

    public ArrayList<Integer> getdiagNames(SQLiteDatabase mydatabase2, String s2, String s3, String s4, String s5) {
        ArrayList<Integer> list = new ArrayList();
        String P_query123 = "select sid from symptoms where sname='" + s2 + "' union select sid from symptoms where sname='" + s3 + "'union select sid from symptoms where sname='" + s4 + "' union select sid from symptoms where sname='" + s5 + "'";
        System.out.println("hi am.." + P_query123);
        Cursor c = mydatabase2.rawQuery(P_query123, null);
        c.moveToFirst();
        if (c != null) {
            c.moveToFirst();
            do {
                list.add(Integer.valueOf(c.getInt(c.getColumnIndex("sid"))));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public ArrayList<String> getdiagids(SQLiteDatabase mydatabase2, int i1, int i2, int i3, int i4) {
        ArrayList<String> list = new ArrayList();
        try {
            System.out.println("hello" + i1 + i2 + i3);
            String P_query123 = "SELECT dname from diseasetable inner join dissymp on dissymp.did=diseasetable.did where dissymp.sid in ('" + i1 + "','" + i2 + "','" + i3 + "','" + i4 + "') group by dissymp.did having count()>=4";
            Cursor c = mydatabase2.rawQuery(P_query123, null);
            System.out.println("hi am.." + P_query123);
            c.moveToFirst();
            if (c != null) {
                do {
                    list.add(c.getString(c.getColumnIndex("dname")));
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
        }
        return list;
    }
}
