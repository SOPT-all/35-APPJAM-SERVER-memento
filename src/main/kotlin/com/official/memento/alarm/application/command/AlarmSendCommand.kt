package com.official.memento.alarm.application.command

data class AlarmSendCommand(
    val uri: String,
    val content: String
) {
}