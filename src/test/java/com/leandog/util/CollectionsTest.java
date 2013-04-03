package com.leandog.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lenadog.util.Collections;

public class CollectionsTest {

    ArrayList<Integer> list1 = new ArrayList<Integer>();
    ArrayList<Integer> list2 = new ArrayList<Integer>();
    ArrayList<Integer> list3 = new ArrayList<Integer>();

    @Before
    public void setup() {
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);

        list3.add(5);
        list3.add(6);

    }

    @Test
    public void itCanCombineMultipleLists() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(5);
        list.add(6);

        assertThat(Collections.combine(list1, list2, list3), equalTo(list));
    }

    @Test
    public void itCanMergeWithoutDuplications() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        assertThat(Collections.merge(list1, list2, list3), equalTo(list));
    }

}
