package com.official.memento.schedule.conntroller.dto.response;

public record ScheduleCreateResponse() {

    public static ScheduleCreateResponse of(){
        return new ScheduleCreateResponse();
    }
}
