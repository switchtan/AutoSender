package test;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimeSleepTest {

    @Test
    public void getTime() throws InterruptedException {
        TimeSleep timeSleep=new TimeSleep();
        timeSleep.getTime();
    }
}