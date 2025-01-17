package com.official.memento.todo.domain.enums;

import java.util.Arrays;

public enum PriorityType {
    IMMEDIATE(0.5, 0.5),
    MEDIUM(0.5, 0.0),
    HIGH(0.0, 0.5),
    LOW(0.0, 0.0),
    NONE(null,null);

    private final Double xCenter;
    private final Double yCenter;

    PriorityType( final Double xCenter, final Double yCenter) {
        this.xCenter = xCenter;
        this.yCenter = yCenter;
    }

    public Double getXCenter() {
        return xCenter;
    }

    public Double getYCenter() {
        return yCenter;
    }

    public static PriorityType findPriorityType(Double x, Double y) {
        if (x > 1.0 || y > 1.0) {
            throw new IllegalArgumentException("좌표 값이 1을 초과할 수 없습니다: x=" + x + ", y=" + y);
        }
        if (x == null && y == null) {
            return NONE;
        } else {
            return Arrays.stream(PriorityType.values())
                    .filter(priority -> x >= priority.getXCenter() && y >= priority.getYCenter())
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 좌표입니다: x=" + x + ", y=" + y));
        }
    }
}
