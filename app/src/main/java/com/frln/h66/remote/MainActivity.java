package com.frln.h66.remote;

import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    static final int RESULT_ENABLE = 1;

    DevicePolicyManager deviceManger;
    ActivityManager activityManager;
    ComponentName compName;

    @BindView(R.id.timerText)
    TextView timer;

    @BindView(R.id.message)
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock);
        ButterKnife.bind(this);
        ((ApplicationE) getApplication()).activity = this;

        message.setText("Hi, I'm remote. I'll be out of your way in 6 seconds");

        if(this != null){
            CountDownTimer c = new CountDownTimer(6000, 1000) {

                @Override
                public void onTick(long l) {
                    String time = (l/1000) + " s";
                    timer.setText(time);
                }

                @Override
                public void onFinish() {
                    MainActivity.this.finish();
                }
            };
            c.start();
        }

        deviceManger = (DevicePolicyManager)getSystemService(
                Context.DEVICE_POLICY_SERVICE);
        activityManager = (ActivityManager)getSystemService(
                Context.ACTIVITY_SERVICE);
        compName = new ComponentName(this,  AdminReceiver.class);

        if(!isEnabled()){
            enable(true);
        }
        startService(new Intent(this, RemoteService.class));

    }

    private void enable(boolean s){
        if(s) {
            Intent intent = new Intent(DevicePolicyManager
                    .ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,
                    compName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                    "This permission is needed to help lock and unlock the phone");
            startActivityForResult(intent, RESULT_ENABLE);
        }else{
            deviceManger.removeActiveAdmin(compName);
        }
    }

    private boolean isEnabled(){
        return deviceManger.isAdminActive(compName);
    }
}
