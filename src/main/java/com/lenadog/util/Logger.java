package com.lenadog.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class Logger {

    private FlurryDelegate flurryDelegate;
    private boolean shouldLogToConsole = false;

    public Logger(FlurryDelegate flurryDelegate) {
        this.flurryDelegate = flurryDelegate;
    }

    public void startSession(Activity activity) {
        flurryDelegate.onStartSession(activity);
        flurryDelegate.onEvent("Page View: " + activity.getClass().getName());
    }

    public void endSession(Context context) {
        flurryDelegate.onEndSession(context);
    }

    public void logEvent(String message) {
        flurryDelegate.logEvent(message);
    }

    public void logSevereError(Exception exception) {
        logToConsole(exception);
        flurryDelegate.onError("Severe", exception.getMessage(), exception.getClass().getName());
    }

    public void logIgnoredError(Exception exception) {
        logToConsole(exception);
        flurryDelegate.onError("Ignored", exception.getMessage(), exception.getClass().getName());
    }
    
    public void enableConsoleLogging() {
        shouldLogToConsole = true;
    }

    private void logToConsole(Exception exception) {
        if (shouldLogToConsole) {
            Log.e(Logger.class.getName(), exception.toString(), exception);
        }
    }
}
