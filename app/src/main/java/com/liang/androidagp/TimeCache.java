package com.liang.androidagp;

import java.util.HashMap;
import java.util.Map;

public class TimeCache {
    private static final Map<String, Long> mStartTimes = new HashMap<>();
    private static final Map<String, Long> mEndTimes = new HashMap<>();

    public static void putStartTime(String methodName, String className) {
        mStartTimes.put(methodName + "," + className, System.currentTimeMillis());
    }

    public static void putEndTime(String methodName, String className) {
        mEndTimes.put(methodName + "," + className, System.currentTimeMillis());
        printlnTime(methodName, className);
    }

    private static void printlnTime(String methodName, String className) {
        String key = methodName + "," + className;
        if (!mStartTimes.containsKey(key) || !mEndTimes.containsKey(key)) {
            System.out.println("key =" + key + "not exist");
        }
        long currTime = mEndTimes.get(key) - mStartTimes.get(key);
        System.out.println("className =" + className + " methodName =" + methodName + "，time consuming " + currTime + "  ms");
    }
}
