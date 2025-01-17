package com.official.memento.schedule.service.command;

public record ScheduleDeleteAllCommand(
        long memberId,
        long scheduleId,
        String scheduleGroupId
) {
    public static ScheduleDeleteAllCommand of(final long memberId, final long scheduleId, final String scheduleGroupId) {
        return new ScheduleDeleteAllCommand(memberId, scheduleId, scheduleGroupId);
    }
}
