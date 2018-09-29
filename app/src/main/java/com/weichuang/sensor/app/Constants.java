package com.weichuang.sensor.app;

import android.graphics.Color;

/**
 * @author Forrest
 * @date 2018/8/12.
 */

public class Constants {

    /**
     * Bugly APP ID
     */
    public static final String BUGLY_ID = "c30519ac65";

    /**
     * 2s间隔
     */
    public static final long DOUBLE_INTERVAL_TIME = 2000;

    /**
     * fragment tags
     */
    public static final String HOSPITAL = "hospital";
    public static final String TRAIN = "train";
    public static final String SETTING = "setting";
    public static final String[] TAGS = {HOSPITAL, TRAIN, SETTING};

    /**
     * 每次扫描时间
     */
    public static long SCAN_DURATION =10 *1000;

    /**
     * Tab colors
     */
    public static final int[] TAB_COLORS = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };
}
