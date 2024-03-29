package com.tibor.szucs.puszisgyilkos;

import android.content.Context;
import android.os.Environment;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

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
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.tibor.szucs.puszisgyilkos", appContext.getPackageName());
    }
    @Test
    public void directoriesEqual() {
        File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getParent() + "/BuziMappa/");
        File dire = new File(Environment.getExternalStorageDirectory()+"/BuziMappa/");
        assertEquals(directory.toString(), dire.toString());
    }
}
