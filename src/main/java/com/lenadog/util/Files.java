package com.lenadog.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;

public class Files {

    private final Context context;
    private final Logger logger;

    public Files(Context context, Logger logger) {
        this.context = context;
        this.logger = logger;
    }

    private void close(Closeable closeable) {
        try {
            if (closeable != null)
                closeable.close();
        } catch (IOException e) {
            logger.logIgnoredError(e);
        }
    }

    public Uri copyToApplicationsDirectory(Uri uri) {
        if (uri == null)
            return null;

        Uri resultingUri = null;
        File fileToCopyFrom = new File(uri.getPath());
        if (fileToCopyFrom.exists()) {
            writeFileBytes(fileToCopyFrom.getName(), getFileBytes(fileToCopyFrom), Context.MODE_PRIVATE);
            resultingUri = Uri.fromFile(getRelativeFile(fileToCopyFrom.getName()));
        }

        return resultingUri;
    }

    public String createUniqueVoiceMemoPathWithoutExtension() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/voice_memo_"
                + SystemClock.elapsedRealtime();
    }

    public void delete(List<String> expectedUris) {
        for (String uri : expectedUris) {
            delete(uri);
        }
    }

    public boolean delete(String fileName) {
        File file = new File(fileName);
        if (file.isAbsolute())
            return file.delete();
        return context.deleteFile(fileName);
    }

    public byte[] getBytesOfFile(String filePath) {
        return getFileBytes(getRelativeFile(filePath));
    }

    public byte[] getAbsoluteFileBytes(String filePath) {
        return getFileBytes(new File(filePath));
    }

    public File getRelativeFile(String filename) {
        return new File(getFilesDir(), filename);
    }

    public File getFilesDir() {
        return context.getFilesDir();
    }

    public boolean has(String fileName) {
        return getRelativeFile(fileName).exists();
    }

    public String[] list() {
        return getFilesDir().list();
    }

    public void writeFileBytes(String fileName, byte[] bytes, int writeMode) {
        FileOutputStream fileStream = null;

        try {
            fileStream = context.openFileOutput(fileName, writeMode);
            if (bytes.length > 0)
                fileStream.write(bytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(fileStream);
        }

    }

    private byte[] getFileBytes(File file) {
        FileInputStream openFileInput = null;

        try {
            openFileInput = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            openFileInput.read(bytes);
            return bytes;
        } catch (FileNotFoundException e) {
            return new byte[] {};
        } catch (IOException e) {
            throw new RuntimeException(e);

        } finally {
            this.close(openFileInput);
        }
    }

}
