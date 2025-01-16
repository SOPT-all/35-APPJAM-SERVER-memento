package com.official.memento.plan.infrastructure

import com.official.memento.global.stereotype.Adapter
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.reactive.function.client.WebClient

@Adapter
class ClaudeAiChatClientAdapter(
    private val webClient: WebClient,
    @Value("\${claude.ai.api-key}")
    private val claudeAiApiKey: String,
) {
    companion object {
        private const val CLAUDE_AI_URL = "https://api.anthropic.com/v1/messages"
        private const val CLAUDE_AI_API_KEY_HEADER = "x-api-key"
        private const val CLAUDE_AI_VERSION_HEADER = "anthropic-version"
        private const val CLAUDE_AI_VERSION_VALUE = "2023-06-01"
    }

    fun completeChat(prompt: String) {
        val requestBody =
            mapOf(
                "model" to "claude-3-5-sonnet-20241022",
                "max_tokens" to 1024,
                "messages" to
                    listOf(
                        mapOf(
                            "role" to "user",
                            "content" to prompt,
                        ),
                    ),
            )

        webClient.post()
            .uri(CLAUDE_AI_URL)
            .header(CLAUDE_AI_API_KEY_HEADER, claudeAiApiKey)
            .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .header(CLAUDE_AI_VERSION_HEADER, CLAUDE_AI_VERSION_VALUE)
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(Void::class.java)
            .block()
    }
}
