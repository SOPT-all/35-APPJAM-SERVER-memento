package com.official.memento.global.util;

public class EisenhowerDistanceUtil {

    // (1,1)로 부터 distance 계산
    public static double getDistance(final double x, final double y) {
        return Math.sqrt(Math.pow(1 - x, 2) * 0.7 + Math.pow(1 - y, 2) * 0.3);
    }

}
