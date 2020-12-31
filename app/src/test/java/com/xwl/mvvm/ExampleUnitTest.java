package com.xwl.mvvm;

import com.xwl.mvvm.base.net.LocalRetrofit;

import org.junit.Assert;
import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        String time = "1609376178431";
        String userAgent = "Agent=PlanTimeApp.Time=" + time;
        String old = "44dbeb225b6996f62fe53bae85a89402292b1c9b";
        String value = LocalRetrofit.sing(time, userAgent);
        System.out.println(value);
        Assert.assertEquals(old, value);
    }
}