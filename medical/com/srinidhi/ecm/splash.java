package com.srinidhi.ecm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class splash extends Activity {

    /* renamed from: com.srinidhi.ecm.splash.1 */
    class C00051 extends Thread {
        C00051() {
        }

        public void run() {
            for (short logoTimer = (short) 0; logoTimer < (short) 1000; logoTimer = (short) (logoTimer + 100)) {
                C00051.sleep(100);
            }
            try {
                splash.this.startActivity(new Intent(splash.this, MedicalproActivity.class));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                splash.this.finish();
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0002R.layout.main);
        new C00051().start();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onRestart() {
        super.onRestart();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }
}
