package com.official.memento.alarm.service.command

import com.official.memento.alarm.domain.port.AlarmOutputPort
import org.springframework.stereotype.Service

interface AlarmSendUseCase {
    fun send(command: AlarmSendCommand)
}

@Service
class AlarmService(
    private val alarmOutputPort: AlarmOutputPort,
) : AlarmSendUseCase {
    // TODO : url parameter 제거, 환경변수로 등록
    override fun send(command: AlarmSendCommand) {
        alarmOutputPort.sendAlarm(command.uri, command.content)
    }
}
