package com.lenadog.util;

import android.net.Uri;

public class UriLoader {

    private final Files files;

    public UriLoader(Files files) {
        this.files = files;
    }

    public byte[] load(Uri uri) {
        return files.getAbsoluteFileBytes(uri.getPath());
    }

}
