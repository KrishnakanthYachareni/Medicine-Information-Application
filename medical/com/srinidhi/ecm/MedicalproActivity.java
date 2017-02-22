package com.srinidhi.ecm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MedicalproActivity extends Activity {
    Button b1;
    Button b2;

    /* renamed from: com.srinidhi.ecm.MedicalproActivity.1 */
    class C00001 implements OnClickListener {
        C00001() {
        }

        public void onClick(View v) {
            MedicalproActivity.this.startActivity(new Intent(MedicalproActivity.this, diseaselist.class));
        }
    }

    /* renamed from: com.srinidhi.ecm.MedicalproActivity.2 */
    class C00012 implements OnClickListener {
        C00012() {
        }

        public void onClick(View v) {
            MedicalproActivity.this.startActivity(new Intent(MedicalproActivity.this, symp.class));
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0002R.layout.main);
        ((Button) findViewById(C0002R.id.button1)).setOnClickListener(new C00001());
        this.b2 = (Button) findViewById(C0002R.id.button2);
        this.b2.setOnClickListener(new C00012());
    }
}
