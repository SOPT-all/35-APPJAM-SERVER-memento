package com.official.memento.alarm.service.command

data class AlarmSendCommand(
    val uri: String,
    val content: String
) {
}