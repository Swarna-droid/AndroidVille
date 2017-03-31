package com.photon.challenge;

import com.photon.challenge.utilities.AsyncLowCast;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private CountDownLatch signal = null;

    @Test
    public void addition_isCorrect() throws Exception {
        int matrix[][] = {
                {3, 4, 1, 2, 8, 6},
                {6, 1, 8, 2, 7, 4},
                {5, 9, 3, 9, 9, 5},
                {8, 4, 1, 3, 2, 6},
                {3, 7, 2, 8, 6, 4}};

        signal = new CountDownLatch(1);

        new AsyncLowCast(matrix) {
            @Override
            public void onResult(String pathValue, String status, int value) {
                signal.countDown();
                assertEquals(value, 16);
                assertEquals(pathValue, "[1 2 3 4 4 5]");
                assertEquals(status, "Yes");
            }
        }.execute();

        signal.await(2, TimeUnit.SECONDS);

    }
}
