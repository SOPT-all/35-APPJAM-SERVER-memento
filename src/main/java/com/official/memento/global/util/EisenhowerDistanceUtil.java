package com.official.memento.global.util;

public class EisenhowerDistanceUtil {

    public static double getDistance(double x, double y) {
        return Math.sqrt(Math.pow(1 - x, 2) * 0.7 + Math.pow(1 - y, 2) * 0.3);
    }

}
