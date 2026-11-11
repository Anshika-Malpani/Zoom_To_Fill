package com.zoomfill.tv;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityService.MagnificationController;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class ZoomService extends AccessibilityService {
    // Change TRIGGER_KEY to use another remote button (e.g. KeyEvent.KEYCODE_PROG_BLUE)
    private static final int TRIGGER_KEY = KeyEvent.KEYCODE_MENU;
    // 1.33x fills a 2.35:1 movie on 16:9; 1.78x fills 4:3 pillarbox + letterbox
    private static final float[] LEVELS = {1.0f, 1.1f, 1.2f, 1.33f, 1.5f, 1.78f, 2.0f};
    private int idx = 0;

    @Override
    protected boolean onKeyEvent(KeyEvent e) {
        if (e.getKeyCode() != TRIGGER_KEY) return false;
        if (e.getAction() == KeyEvent.ACTION_DOWN && e.getRepeatCount() == 0) cycle();
        return true; // consume so the app does not also open its own menu
    }

    private void cycle() {
        idx = (idx + 1) % LEVELS.length;
        MagnificationController mc = getMagnificationController();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mc.setScale(LEVELS[idx], true);
        mc.setCenter(dm.widthPixels / 2f, dm.heightPixels / 2f, true);
        Toast.makeText(this, "Zoom " + LEVELS[idx] + "x", Toast.LENGTH_SHORT).show();
    }

    @Override public void onAccessibilityEvent(AccessibilityEvent event) {}
    @Override public void onInterrupt() {}
}
