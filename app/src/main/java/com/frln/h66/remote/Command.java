package com.frln.h66.remote;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * @author fdamilola on 31/01/2017.
 * @contact fdamilola@gmail.com +2348166200715
 */

public class Command {
    @SerializedName("lastSessionId") public String lastSessionId;
    @SerializedName("commandTitleReverse") public String commandTitleReverse;
    @SerializedName("commandHash") public String commandHash;
    @SerializedName("commandTitle") public String commandTitle;
    @SerializedName("index") public String index;
    @SerializedName("current_status") public boolean current_status;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
