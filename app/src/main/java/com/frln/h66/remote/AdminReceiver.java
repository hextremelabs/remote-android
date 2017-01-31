package com.frln.h66.remote;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author fdamilola on 31/01/2017.
 * @contact fdamilola@gmail.com +2348166200715
 */

public class AdminReceiver extends DeviceAdminReceiver {

    void showToast(Context context, CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        showToast(context, "Remote Admin: enabled");
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return "Wadssdsdsds ds,d sd]s, fs ,fs";
    }

    @Override
    public void onDisabled(Context context, Intent intent) {

    }

    @Override
    public void onPasswordChanged(Context context, Intent intent) {

    }

    @Override
    public void onPasswordFailed(Context context, Intent intent) {

    }

    @Override
    public void onPasswordSucceeded(Context context, Intent intent) {
            //Something here about stealing passwords. I should remind myself later.
    }

}