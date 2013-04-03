package com.lenadog.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Collections {

    public static <T> ArrayList<T> arrayListWith(T... t) {
        return new ArrayList<T>(Arrays.asList(t));
    }

    public static boolean isNullOrEmpy(Object[] objects) {

        if (objects == null || objects.length == 0)
            return true;

        for (Object object : objects) {

            if (object != null)
                return false;
        }

        return true;
    }
    
    public static <T> List<T> combine(List<T>... lists) {
        if (lists == null)
            return null;

        ArrayList<T> arrayList = new ArrayList<T>();
        for (List<T> list : lists) {
            if (list != null)
                arrayList.addAll(list);
        }

        return arrayList;
    }

    public static <T> List<T> merge(List<T>... lists) {
        return new ArrayList<T>(new HashSet<T>(combine(lists)));
    }

}
