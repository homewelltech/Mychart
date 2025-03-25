package com.chatapp.websocket

object WebSocketMessageHandler {
    /**
     * 处理接收到的 WebSocket 消息
     */
    fun handleMessage(message: String) {
        // 解析消息，比如使用 JSON 序列化库（如 kotlinx.serialization 或 Gson）
        println("Handling message: $message")
        // 根据业务需求解析消息内容并分发到相应模块
        // 示例：如果消息是聊天内容，可以调用 ChatViewModel 相关方法更新 UI
    }
}
