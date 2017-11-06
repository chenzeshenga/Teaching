package com.gs.timer;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/10/26.
 */
public class TimerTest {

    public static void main(String... args) {
        Timer timer = new Timer();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, Calendar.OCTOBER, 26, 10, 34, 0);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时任务启动");
            }
        }, calendar.getTime());
        timer.cancel();

        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("任务启动");
            }
        }, 10 * 1000);
    }

}
