package com.chatapp.websocket

class StompManager(private val webSocketClient: ChatWebSocketClient) {

    /**
     * 订阅指定用户的消息推送
     */
    fun subscribe(receiverId: String) {
        val subscribeMessage = """
            {
                "command": "SUBSCRIBE",
                "destination": "/topic/chat/$receiverId"
            }
        """.trimIndent()
        webSocketClient.send(subscribeMessage)
        println("Sent subscription message for receiverId: $receiverId")
    }

    /**
     * 发送消息到服务器（可扩展 STOMP 格式封装）
     */
    fun sendMessage(message: String) {
        // 如需要可在此处封装 STOMP 消息格式
        webSocketClient.send(message)
        println("Sent message: $message")
    }
}
