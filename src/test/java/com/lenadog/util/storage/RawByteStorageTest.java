package com.lenadog.util.storage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import android.content.Context;

import com.lenadog.util.Files;

public class RawByteStorageTest {

    private static final String ENCODED_FORM_OF_TEST_URL = "http---some-where-someFileName.txt";
    private static final String TEST_URL = "http://some/where/someFileName.txt";

    @Test
    public void generatesABaseNameForAURL() throws Exception {
        RawByteStorage storage = new RawByteStorage(null);
        final String baseName = storage.baseNameForURL("https://www.erieinsurance.com/eMobile/Agency/Logo/XX9999");
        assertThat(baseName, is(equalTo("https---www.erieinsurance.com-eMobile-Agency-Logo-XX9999")));
    }

    @Test
    public void writesBlobToFilesystem() throws Exception {
        final Files files = mock(Files.class);
        RawByteStorage storage = new RawByteStorage(files);

        storage.write("https://foo/bar", new byte[] { 1, 2, 3 });
        verify(files).writeFileBytes(eq("https---foo-bar"), eq(new byte[] { 1, 2, 3 }), eq(Context.MODE_PRIVATE));
    }

    @Test
    public void writeBlobToFileSystemWorldReadable() {
        final Files files = mock(Files.class);
        RawByteStorage storage = new RawByteStorage(files);

        storage.writeWorldReadable("https://foo/bar", new byte[] { 1, 2, 3 });
        verify(files).writeFileBytes(eq("https---foo-bar"), eq(new byte[] { 1, 2, 3 }), eq(Context.MODE_WORLD_READABLE));
    }

    @Test
    public void readsBlobFromFilesystem() throws Exception {
        final Files files = mock(Files.class);
        RawByteStorage storage = new RawByteStorage(files);

        when(files.getBytesOfFile(eq("https---foo-bar"))).thenReturn(new byte[] { 3, 2, 1 });

        final byte[] data = storage.read("https://foo/bar");
        assertThat(data, is(equalTo(new byte[] { 3, 2, 1 })));
    }

    @Test
    public void getsFileFromRelativePath() {
        String fileName = TEST_URL;
        Files files = mock(Files.class);
        RawByteStorage storage = new RawByteStorage(files);

        storage.getLocalPath(fileName);

        verify(files).getRelativeFile(eq(ENCODED_FORM_OF_TEST_URL));
    }

    @Test
    public void hasLocalShouldAskFilesForExsits() {
        String fileName = TEST_URL;
        Files files = mock(Files.class);
        RawByteStorage storage = new RawByteStorage(files);

        storage.has(fileName);

        verify(files).has(eq(ENCODED_FORM_OF_TEST_URL));
    }
}
