package com.frln.h66.remote;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author fdamilola on 31/01/2017.
 * @contact fdamilola@gmail.com +2348166200715
 */

public class KeyGuardManager {
    public static void lockUnlock(Context context, boolean unlockLock) {
        SleepManger.sleep(context, unlockLock);
    }


}

class SleepManger {
    static DevicePolicyManager deviceManger;
    static ComponentName compName;

    public static void sleep(final Context context, boolean sleep) {
        deviceManger = (DevicePolicyManager) context.getSystemService(
                Context.DEVICE_POLICY_SERVICE);
        compName = new ComponentName(context, AdminReceiver.class);
        boolean active = deviceManger.isAdminActive(compName);
        if (active) {
            if (!sleep) {
                Log.e("Case", "Locking....");
                deviceManger.lockNow();
            } else {
                Log.e("Case", "UnLocking....");
                Intent dialogIntent = new Intent(context, UnlockActivity.class);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(dialogIntent);
            }
        }

    }
}
