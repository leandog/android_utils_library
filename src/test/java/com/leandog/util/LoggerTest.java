package com.leandog.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import android.app.Activity;

import com.lenadog.util.FlurryDelegate;
import com.lenadog.util.Logger;

public class LoggerTest {

    Activity activity = mock(Activity.class);
    FlurryDelegate flurryDelegate = mock(FlurryDelegate.class);
    Logger logger;

    @Before
    public void setUp() {
        logger = new Logger(flurryDelegate);
    }

    @Test
    public void itLoggerDelegatesStartSessionToFlurryDelegate() {
        logger.startSession(activity);
        verify(flurryDelegate).onStartSession(activity);
    }
}
