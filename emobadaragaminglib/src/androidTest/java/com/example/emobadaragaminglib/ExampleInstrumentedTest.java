package com.example.emobadaragaminglib;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ListIterator;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        ArrayList<String > arr = new ArrayList<>(Arrays.asList("a","b","c"));
        ListIterator<String> itr = arr.listIterator(arr.size());
        while(itr.hasPrevious()){
            System.out.println(itr.previous());
        }
        assertEquals("com.example.emobadaragaminglib.test", appContext.getPackageName());

    }
}
