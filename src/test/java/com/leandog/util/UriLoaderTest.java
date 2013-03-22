package com.leandog.util;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import android.net.Uri;

import com.lenadog.util.Files;
import com.lenadog.util.UriLoader;

public class UriLoaderTest {

    Uri uri = mock(Uri.class);
    Files files = mock(Files.class);
    UriLoader loader = new UriLoader(files);

    @Test
    public void loadGetsPathFromUri() {
        loader.load(uri);

        verify(uri).getPath();
    }

    @Test
    public void loadGetsFileBytesFormFilePath() {
        when(uri.getPath()).thenReturn("/mnt/sdcard/DCIM/image.jpg");
        loader.load(uri);
        verify(files).getAbsoluteFileBytes(eq("/mnt/sdcard/DCIM/image.jpg"));
    }
}
