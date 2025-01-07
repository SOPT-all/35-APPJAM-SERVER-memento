package com.official.memento.alarm.controller

import com.official.memento.alarm.controller.dto.AlarmRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "Alarm", description = "알람 API")
interface AlarmApiDocs {

    @Operation(
        summary = "Discord 알람 전송",
        description = "Discord 알람을 전송합니다.",
        responses = [
            ApiResponse(responseCode = "204", description = "알람 전송 성공"),
            ApiResponse(responseCode = "400", description = "알람 전송 실패")
        ]
    )
    suspend fun sendAlarm(
        @RequestBody request: AlarmRequest
    ) : ResponseEntity<Unit>
}