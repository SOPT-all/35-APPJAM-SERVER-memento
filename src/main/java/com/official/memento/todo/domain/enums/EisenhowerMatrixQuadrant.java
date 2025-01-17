package com.official.memento.todo.domain.enums;

import java.util.Arrays;

//x축 : 긴급도, y축 : 중요도
public enum EisenhowerMatrixQuadrant {
    QUADRANT_1(PriorityType.IMMEDIATE, 0.25, 0.25),
    QUADRANT_2(PriorityType.HIGH, 0.75, 0.25),
    QUADRANT_3(PriorityType.MEDIUM,0.25, 0.75),
    QUADRANT_4(PriorityType.LOW, 0.75, 0.75);

    private final PriorityType priorityType;
    private final double xCenter;
    private final double yCenter;

    EisenhowerMatrixQuadrant(final PriorityType priorityType, final double xCenter, final double yCenter) {
        this.priorityType = priorityType;
        this.xCenter = xCenter;
        this.yCenter = yCenter;
    }

    public PriorityType getPriorityType() {
        return priorityType;
    }

    public double getXCenter() {
        return xCenter;
    }

    public double getYCenter() {
        return yCenter;
    }

    public static EisenhowerMatrixQuadrant findQuadrant(double x, double y) {
        return Arrays.stream(EisenhowerMatrixQuadrant.values())
                .filter(quadrant -> quadrant.getXCenter() == x && quadrant.getYCenter() == y)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 좌표입니다: x=" + x + ", y=" + y));
    }
}
