package com.frln.h66.remote;

import android.content.Context;
import android.util.Log;

/**
 * @author fdamilola on 31/01/2017.
 * @contact fdamilola@gmail.com +2348166200715
 */

public class Utils {

    public static class Constants{
        public static long display_time = 15000;
    }

    public static class WifiHotspot{
        public static void performOp(Context context, boolean onOff){
            Log.e("Fact?", "Starts performing....wH");
            ApManager.switchWifi(context, onOff);
        }
    }

    public static class Security{
        public static void unlockOrLockDevice(Context context, boolean unlockLock){
            Log.e("Fact?", "Starts performing....ud");
            KeyGuardManager.lockUnlock(context, unlockLock);

        }
    }

}
