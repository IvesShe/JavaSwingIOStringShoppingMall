package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;

public class Clock {

    // 設定時間格式
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 獲取當前格式化時間
    public static String getFormattedCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }

    // 開始自動更新並顯示時間
    public static void startAutoUpdateClock(JLabel lblTime) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = new Runnable() {
            @Override
            public void run() {
                // 獲取並顯示當前時間
                String formattedTime = getFormattedCurrentTime();
                lblTime.setText(formattedTime);
            }
        };

        // 每秒執行一次任務
        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }
}
