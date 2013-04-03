package com.leandog.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lenadog.util.Strings;

public class StringsTest {

   @Test
   public void isNullOrEmptyAppliesToManyStrings() {
       assertTrue(Strings.areAllNullOrEmpty("","",""));
       assertTrue(Strings.areAllNullOrEmpty(null,"",null));
       
   }
   
   @Test
   public void isNullOrEmptyAppliesToOneString() {
       String nullString = null;
       assertTrue(Strings.areAllNullOrEmpty(nullString));
       assertTrue(Strings.areAllNullOrEmpty(""));
   }
    
}
