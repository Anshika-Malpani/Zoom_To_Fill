package com.zoomfill.tv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setGravity(Gravity.CENTER);
        l.setPadding(80, 40, 80, 40);
        TextView t = new TextView(this);
        t.setTextSize(24);
        t.setText("Zoom Fill\n\n1. Open Accessibility settings below and turn ON \"Zoom Fill\".\n"
            + "2. Play any video in any app.\n"
            + "3. Press the Menu (\u2630) button on your remote to cycle zoom: 1.0x > 1.1x > 1.2x > 1.33x > 1.5x > 1.78x > 2.0x > back to 1.0x.");
        Button btn = new Button(this);
        btn.setText("Open Accessibility settings");
        btn.setOnClickListener(v -> startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)));
        l.addView(t);
        l.addView(btn);
        setContentView(l);
        btn.requestFocus();
    }
}
