package com.chatapp.websocket

import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

class ChatWebSocketClient(serverUri: URI) : WebSocketClient(serverUri) {

    override fun onOpen(handshakedata: ServerHandshake?) {
        println("WebSocket connection opened: $handshakedata")
        // 可在此处发送初始化消息或订阅命令
    }

    override fun onMessage(message: String?) {
        println("Received message: $message")
        // 将消息传递给消息处理器进行统一处理
        if (message != null) {
            WebSocketMessageHandler.handleMessage(message)
        }
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        println("WebSocket connection closed. Code: $code, Reason: $reason, Remote: $remote")
        // 可以在此处实现重连逻辑或通知用户连接已断开
    }

    override fun onError(ex: Exception?) {
        println("WebSocket error: ${ex?.message}")
        // 错误日志记录，必要时进行异常上报
    }
}
