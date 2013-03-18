package com.lenadog.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Strings {

    public static final String AND = "&";
    public static final String EQUALS = "=";
    public static final String EMPTY = "";

    public static boolean isNullOrEmpty(String string) {
        return areAllNullOrEmpty(string);
    }
    
    public static boolean isNotNullOrEmpty(String string) {
        return !areAllNullOrEmpty(string);
    }

    public static boolean areAllNullOrEmpty(String... strings) {
        if (strings == null)
            return true;

        for (String string : strings) {
            if (string != null && !EMPTY.equals(string))
                return false;
        }
        return true;
    }

    public static String[] arrayFrom(List<String> stringList) {
        return stringList.toArray(new String[stringList.size()]);
    }
    
    public static String stringFrom(InputStream inputStream) throws IOException {
        String message;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder builder = new StringBuilder();

        String line = reader.readLine();
        while (line != null) {
            builder.append(line + "\n");
            line = reader.readLine();
        }

        message = builder.toString().trim();

        reader.close();
        inputStream.close();
        return message;

    }

    public static String delimitedStringFrom(List<?> collection, char delimiter) {
        StringBuilder builder = new StringBuilder();
        for (Object item : collection) {
           builder.append(item.toString() + delimiter); 
        }
        String resultingString = builder.toString();
        return resultingString.substring(0, resultingString.length() - 1);
    }

    public static String lastLineOf(String text) {
        final String[] lines = text.split("\n");
        return lines[lines.length - 1];
    }

    public static String emptyIfNull(Object value) {
        return defaultIfNull(value, EMPTY);
    }

    public static String defaultIfNull(Object value, String defaultValue) {
        return (value == null) ? defaultValue : value.toString();
    }

    public static String join(final String delimiter, Object... objects) {
        if (objects == null)
            return "";
        
        StringBuilder stringBuilder = new StringBuilder();
        for (Object o : objects) {
            String string = (o != null) ? o.toString() : "";
            stringBuilder.append(string);
            stringBuilder.append(delimiter);
        }
        
        return stringBuilder.replace(stringBuilder.length()-2, stringBuilder.length()-1, "").toString();
    }

    public static String join(final String delimiter, List<String> strings) {
        StringBuilder stringBuilder = new StringBuilder();
        
        for (int idx = 0; idx < strings.size(); idx++) {
            stringBuilder.append(strings.get(idx));
            if (idx < strings.size() - 1) {
                stringBuilder.append(delimiter);
            }
        }

        return stringBuilder.toString();
    }

    public static <TEntity> String joinNonEmpty(final String delimiter, TEntity... entities) {
        final ArrayList<String> nonEmptyObjects = new ArrayList<String>();
        for (TEntity entity : entities) {
            addIfHasValue(entity, nonEmptyObjects);
        }

        return Strings.join(delimiter, nonEmptyObjects);
    }

    public static <TEntity> String joinNonEmpty(final String delimeter, final ArrayList<TEntity> entities) {
        return Strings.joinNonEmpty(delimeter, entities.toArray());
    }

    private static <TEntity> void addIfHasValue(final TEntity entity, final ArrayList<String> nonEmptyObjects) {
        if (null == entity)
            return;

        final String value = entity.toString();

        if (!Strings.areAllNullOrEmpty(value)) {
            nonEmptyObjects.add(value);
        }
    }

    public static String parenthesize(int number) {
        return " (" + number + ")";
    }
    
}

