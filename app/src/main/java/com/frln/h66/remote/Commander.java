package com.frln.h66.remote;

import android.content.Context;
import android.util.Log;

/**
 * @author fdamilola on 31/01/2017.
 * @contact fdamilola@gmail.com +2348166200715
 */

public class Commander {
    public static void execute(boolean action, Command command, Context context){
        if(command == null || context == null){
            Log.e("Case", "Command or Context null, likely context because android doesn't have sense or context.");
            return;
        }

        Log.e(command.commandHash, action ? command.commandTitle:command.commandTitleReverse);
        switch (command.commandHash) {
            case "5b05a57590f22149666243fde88d0eca":
                Utils.WifiHotspot.performOp(context, action);
                break;
            case "70a751700823a55bf4c1d1d36b0300fc":
                Utils.Security.unlockOrLockDevice(context, action);
                break;
            case "8297127b6822b2840a8d80b28895aab2":
                Utils.Wifi.performOp(context, action);
                break;
            default:
                break;
        }
    }
}
