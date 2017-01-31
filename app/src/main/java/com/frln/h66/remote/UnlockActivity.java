package com.frln.h66.remote;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UnlockActivity extends AppCompatActivity implements Done {

    @BindView(R.id.timerText)
    TextView timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(this.getLocalClassName(), "ActivityStarted");
        setContentView(R.layout.activity_unlock);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(this.getLocalClassName(), "ResumeActivityStarted");
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        KeyguardManager manager = (KeyguardManager) this.getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock lock = manager.newKeyguardLock("abc");
        lock.disableKeyguard();
        this.complete();
    }

    @Override
    public void complete() {
        if(this != null){
            Snackbar.make(this.findViewById(android.R.id.content), "Remote (Yes, me!) successfully (well, somewhat) unlocked your device. In fact, " +
                    "I think I have done more than Buhari in terms of promises and action.. " +
                    "I will be out of your way shortly....", Snackbar.LENGTH_INDEFINITE).show();
            CountDownTimer c = new CountDownTimer(Utils.Constants.display_time, 1000) {

                @Override
                public void onTick(long l) {
                    String time = (l/1000) + " s";
                    timer.setText(time);
                }

                @Override
                public void onFinish() {
                    UnlockActivity.this.finish();
                }
            };
            c.start();
        }
    }
}

interface Done {
    void complete();
}