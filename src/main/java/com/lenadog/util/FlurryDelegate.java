package com.lenadog.util;

import android.content.Context;

import com.flurry.android.FlurryAgent;

public class FlurryDelegate {
    
    private String appToken;

    public FlurryDelegate(String appToken) {
        this.appToken = appToken;
    }

    public void onStartSession(Context context) {
       FlurryAgent.onStartSession(context, appToken); 
    }

    public void onEvent(String event) {
        FlurryAgent.onEvent(event);
    }

    public void onEndSession(Context context) {
        FlurryAgent.onEndSession(context);
    }

    public void logEvent(String message) {
        FlurryAgent.logEvent(message);
    }

    public void onError(String string, String message, String name) {
        FlurryAgent.onError(string, message, name);
    }
}
