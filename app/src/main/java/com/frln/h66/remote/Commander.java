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

        Log.e("Should perform", action ? command.commandTitle:command.commandTitleReverse);
        switch (command.commandHash) {
            case "7f1b6c8706d5ace695ff5aca9320d795":
                Utils.WifiHotspot.performOp(context, action);
                break;
            case "70a751700823a55bf4c1d1d36b0300fc":
                Utils.Security.unlockOrLockDevice(context, action);
                break;
            default:
                break;
        }
    }
}
