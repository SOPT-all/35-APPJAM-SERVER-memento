package com.official.memento.alarm.controller

import com.official.memento.alarm.controller.dto.AlarmRequest
import com.official.memento.alarm.service.command.AlarmSendCommand
import com.official.memento.alarm.service.command.AlarmSendUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AlarmApi(
    private val alarmSendUseCase: AlarmSendUseCase,
) : AlarmApiDocs {
    @PostMapping("/alarm")
    override suspend fun sendAlarm(
        @RequestBody request: AlarmRequest,
    ): ResponseEntity<Unit> {
        alarmSendUseCase.send(
            AlarmSendCommand(
                uri = request.uri,
                content = request.content,
            ),
        )
        return ResponseEntity.noContent().build()
    }
}
