package com.lenadog.util.storage;

import java.io.File;

import android.content.Context;

import com.lenadog.util.Files;

public class RawByteStorage {

    private final Files files;

    public RawByteStorage(Files files) {
        this.files = files;
    }

    public String baseNameForURL(String url) {
        return url.replaceAll("[^a-zA-Z0-9\\.]", "-");
    }

    public void write(String url, byte[] data) {
        files.writeFileBytes(baseNameForURL(url), data, Context.MODE_PRIVATE);
    }

    public void writeWorldReadable(String url, byte[] data) {
        files.writeFileBytes(baseNameForURL(url), data, Context.MODE_WORLD_READABLE);
    }

    public byte[] read(String url) {
        return files.getBytesOfFile(baseNameForURL(url));
    }

    public File getLocalPath(String url) {
        return files.getRelativeFile(baseNameForURL(url));
    }

    public boolean has(String url) {
        return files.has(baseNameForURL(url));
    }

}
