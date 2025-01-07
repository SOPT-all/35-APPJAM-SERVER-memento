package com.official.memento.alarm.domain.port

interface AlarmOutputPort {
    fun sendAlarm(uri: String, content: String)
}