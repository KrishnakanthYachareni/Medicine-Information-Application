package com.srinidhi.ecm;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class tab extends TabActivity {
    Bundle f3b;
    Intent intent;
    Intent it2;
    Intent it3;
    Resources res;
    String f4s;
    String s1;
    TabSpec spec;
    TabHost tabhost;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0002R.layout.tablayout);
        this.res = getResources();
        this.tabhost = getTabHost();
        this.f3b = getIntent().getExtras();
        if (this.f3b != null) {
            this.s1 = this.f3b.getString("name");
        }
        this.intent = new Intent().setClass(this, diseaseinfo.class);
        this.intent.putExtra("name", this.s1);
        this.spec = this.tabhost.newTabSpec("Disease Info").setIndicator("Disease Info").setContent(this.intent);
        this.tabhost.addTab(this.spec);
        this.intent = new Intent().setClass(this, next.class);
        this.spec = this.tabhost.newTabSpec("Symptoms").setIndicator("Symptoms").setContent(this.intent);
        this.intent.putExtra("name", this.s1);
        this.tabhost.addTab(this.spec);
        this.intent = new Intent().setClass(this, medication.class);
        this.spec = this.tabhost.newTabSpec("Medication").setIndicator("Medication").setContent(this.intent);
        this.intent.putExtra("name", this.s1);
        this.tabhost.addTab(this.spec);
        this.tabhost.setCurrentTab(0);
    }
}
