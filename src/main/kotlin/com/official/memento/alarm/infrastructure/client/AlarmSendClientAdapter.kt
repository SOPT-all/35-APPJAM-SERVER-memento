package com.official.memento.alarm.infrastructure.client

import com.official.memento.alarm.domain.port.AlarmOutputPort
import com.official.memento.global.stereotype.Adapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBodilessEntity

@Adapter
class AlarmSendClientAdapter : AlarmOutputPort {

    private val webClient = WebClient.create()

    override fun sendAlarm(uri: String, content: String) {
        CoroutineScope(Dispatchers.IO).launch {
            webClient.post()
                .uri(uri)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(AlarmRequestBody(content))
                .retrieve()
                .awaitBodilessEntity()
        }
    }

    data class AlarmRequestBody(
        val content: String
    ) {
    }
}