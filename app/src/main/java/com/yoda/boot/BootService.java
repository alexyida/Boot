package com.yoda.boot;

import android.app.IntentService;
import android.content.Intent;

import java.io.DataOutputStream;

public class BootService extends IntentService {

    /**
     * A constructor is required, and must call the super IntentService(String)
     * constructor with a name for the worker thread.
     */
    public BootService() {
        super("BootService");
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            String[] commands = {"/system/bin/noodle_wrapper.sh"};
            Process p = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(p.getOutputStream());
            for (String tmpCmd : commands) {
                os.writeBytes(tmpCmd+"\n");
            }
            os.writeBytes("exit\n");
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}