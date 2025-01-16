package com.official.memento.todo.domain.enums;

public enum EisenhowerMatrixQuadrant {
    QUADRANT_1(PriorityType.HIGH, .25, 0.25),
    QUADRANT_2(PriorityType.IMMEDIATE, 0.25, 0.25),
    QUADRANT_3(PriorityType.MEDIUM,.25, 0.25),
    QUADRANT_4(PriorityType.LOW, 0.25, 0.25);

    private final PriorityType priorityType;
    private final double xCenter;
    private final double yCenter;

    EisenhowerMatrixQuadrant(PriorityType priorityType, double xCenter, double yCenter) {
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
}
