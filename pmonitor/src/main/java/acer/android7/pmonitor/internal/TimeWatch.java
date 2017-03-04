package acer.android7.pmonitor.internal;

import java.util.concurrent.TimeUnit;

/**
 * 性能监控计时类
 * Created by xiaozheng on 2017/2/18.
 */

public class TimeWatch {
    private long startTime;
    private long endTime;
    private long elapsedTime;

    private void reset() {
        startTime = 0;
        endTime = 0;
        elapsedTime = 0;
    }

    public void start() {
        reset();

        startTime = System.nanoTime();
    }

    public void end() {
        if (startTime != 0) {
            endTime = System.nanoTime();

            elapsedTime = endTime - startTime;
        } else {
            reset();
        }
    }

    public long getTotalTime() {
        return (elapsedTime != 0) ? TimeUnit.NANOSECONDS.toMillis(elapsedTime) : 0;
    }

}
