package com.official.memento.schedule.service.command;

public record ScheduleDeleteCommand (
        long memberId,
        long scheduleId
){
    public static ScheduleDeleteCommand of(final long memberId,final long scheduleId){
        return new ScheduleDeleteCommand(memberId, scheduleId);
    }
}
