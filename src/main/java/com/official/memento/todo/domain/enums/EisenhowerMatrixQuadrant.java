package com.official.memento.todo.domain.enums;

public enum EisenhowerMatrixQuadrant {
    QUADRANT_1(PriorityType.HIGH, 0.25, 0.75),
    QUADRANT_2(PriorityType.IMMEDIATE, 0.75, 0.75),
    QUADRANT_3(PriorityType.MEDIUM,0.75, 0.25),
    QUADRANT_4(PriorityType.LOW, 0.25, 0.25);

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
}
