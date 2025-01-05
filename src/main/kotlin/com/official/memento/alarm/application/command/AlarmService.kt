package com.official.memento.alarm.application.command

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBodilessEntity

interface AlarmSendUseCase {
    fun send(command: AlarmSendCommand)
}

@Service
class AlarmService : AlarmSendUseCase {

    private val webClient = WebClient.create()

    // TODO : url parameter 제거, 환경변수로 등록
    override fun send(command: AlarmSendCommand) {
        CoroutineScope(Dispatchers.IO).launch {
            webClient.post()
                .uri(command.uri)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(AlarmRequestBody(command.content))
                .retrieve()
                .awaitBodilessEntity()
        }
    }
}

data class AlarmRequestBody(
    val content: String
) {
}

