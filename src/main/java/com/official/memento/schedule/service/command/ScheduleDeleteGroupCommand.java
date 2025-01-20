package com.official.memento.schedule.service.command;

public record ScheduleDeleteGroupCommand(
        long memberId,
        long scheduleId,
        String scheduleGroupId
) {
    public static ScheduleDeleteGroupCommand of(final long memberId, final long scheduleId, final String scheduleGroupId) {
        return new ScheduleDeleteGroupCommand(memberId, scheduleId, scheduleGroupId);
    }
}
