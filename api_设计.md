

可同时参考服务端开发给的接口封装：
data class ApiResponse<T>(
val timestamp: Long = System.currentTimeMillis(),
val code: Int = 200,
val message: String = "success",
val data: T? = null
)

data class PageResult<T>(
val content: List<T>, // 当前页数据
val page: Int,        // 当前页码
val size: Int,        // 每页大小
val total: Int        // 总记录数
)

下面是所有的接口以及参考返回。



✅ 1. 用户管理模块 API
1.1 注册接口
URL: /api/user/register
请求方式: POST
请求参数:



{
"nickname": "小明",
"password": "password123",
"role": "USER"  // 可选，默认为 USER
}
返回示例:



{
"code": 200,
"message": "注册成功",
"data": {
"userId": "193039", // 系统生成的用户 ID
"password": "password123"
}
}
1.2 登录接口
URL: /api/user/login
请求方式: POST
请求参数:



{
"userId": "193039",
"password": "password123"
}
返回示例:



{
"code": 200,
"message": "登录成功",
"data": {
"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
"refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNzkyMDgiLCJyb2xlIjoiQURNSU4i...",
"userInfo": {
"userId": "193039",
"nickname": "小明",
"avatarUrl": "http://minio-server/chatapp/avatars/193039-avatar.png",
"role": "USER"
}
}
}
1.3 刷新 Token 接口
URL: /api/user/refresh
请求方式: POST
请求参数:



{
"refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNzkyMDgiLCJyb2xlIjoiQURNSU4i..."
}
返回示例:



{
"code": 200,
"message": "Token 刷新成功",
"data": {
"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
}
1.4 获取用户信息接口
URL: /api/user/info
请求方式: POST
请求参数: 无（JWT Token 会通过 Authorization 头传递）
返回示例:



{
"code": 200,
"message": "获取用户信息成功",
"data": {
"userId": "193039",
"nickname": "小明",
"avatarUrl": "http://minio-server/chatapp/avatars/193039-avatar.png",
"role": "USER"
}
}


✅ 2. 好友管理模块 API
2.1 添加好友接口
URL: /api/friends/add

请求方式: POST

请求参数:

~~
~~

{
"receiverId": "193039",  // 接收方用户ID
"status": "SENT"         // 消息发送状态
}
返回示例:




{
"code": 200,
"message": "好友添加成功"
}
2.2 移除好友接口
URL: /api/friends/remove

请求方式: POST

请求参数:




{
"receiverId": "193039",  // 接收方用户ID
"status": "DELETED"      // 消息删除状态
}
返回示例:




{
"code": 200,
"message": "好友删除成功"
}
2.3 获取好友列表接口
URL: /api/friends

请求方式: POST

请求参数:




{
"userId": "193038", // 当前用户ID
"page": 1,          // 页码
"size": 20          // 每页好友数量
}
返回示例:




{
"code": 200,
"message": "好友列表获取成功",
"data": {
"total": 100,
"friends": [
{
"userId": "193039",
"nickname": "小红"
},
{
"userId": "193040",
"nickname": "小明"
}
]
}
}
✅ 3. 群组管理模块 API
3.1 创建群组接口
URL: /api/groups/create

请求方式: POST

请求参数:




{
"name": "Chat Group",      // 群组名称
"ownerId": "193038",       // 群主用户ID
"status": "ACTIVE"         // 群组状态
}
返回示例:




{
"code": 200,
"message": "群组创建成功"
}
3.2 加入群组接口
URL: /api/groups/addMember

请求方式: POST

请求参数:




{
"groupId": 1,             // 群组ID
"userId": "193039",       // 用户ID
"status": "MEMBER"        // 用户状态：MEMBER, ADMIN
}
返回示例:




{
"code": 200,
"message": "成员添加成功"
}
3.3 移除群组成员接口
URL: /api/groups/removeMember

请求方式: POST

请求参数:




{
"groupId": 1,             // 群组ID
"userId": "193039",       // 用户ID
"status": "REMOVED"       // 用户状态：REMOVED
}
返回示例:




{
"code": 200,
"message": "成员移除成功"
}
3.4 设置群组公告接口
URL: /api/groups/setAnnouncement

请求方式: POST

请求参数:




{
"groupId": 1,             // 群组ID
"announcement": "群组公告内容", // 公告内容
"status": "ACTIVE"        // 公告状态
}
返回示例:




{
"code": 200,
"message": "群组公告已更新"
}
3.5 获取群组成员接口
URL: /api/groups/members

请求方式: POST

请求参数:




{
"groupId": 1,             // 群组ID
"page": 1,                // 页码
"size": 20                // 每页成员数量
}
返回示例:




{
"code": 200,
"message": "群组成员列表获取成功",
"data": {
"total": 100,
"members": [
{
"userId": "193039",
"nickname": "小红"
},
{
"userId": "193040",
"nickname": "小明"
}
]
}
}



✅ 4. 消息管理模块 API
4.1 发送消息接口
URL: /api/messages/send
请求方式: POST
请求参数:



{
"type": "TEXT",        // 消息类型，支持 TEXT, MEDIA, FILE 等
"content": "你好",     // 消息内容，文本消息时为实际文本，媒体或文件时为 URL
"receiverId": "193039", // 接收方用户ID
"status": "SENT"       // 消息状态，SENT: 已发送, DELIVERED: 已送达, READ: 已读
}
返回示例:



{
"code": 200,
"message": "消息发送成功"
}
4.2 发送媒体消息接口
URL: /api/messages/send/media
请求方式: POST
请求参数:



{
"type": "MEDIA",         // 消息类型：MEDIA
"content": "http://media.url", // 媒体资源 URL
"receiverId": "193039",  // 接收方用户ID
"status": "SENT"         // 消息状态，SENT: 已发送, DELIVERED: 已送达, READ: 已读
}
返回示例:



{
"code": 200,
"message": "媒体消息发送成功"
}
4.3 发送文件消息接口
URL: /api/messages/send/file
请求方式: POST
请求参数:



{
"type": "FILE",          // 消息类型：FILE
"content": "http://file.url",  // 文件资源 URL
"receiverId": "193039",  // 接收方用户ID
"status": "SENT"         // 消息状态，SENT: 已发送, DELIVERED: 已送达, READ: 已读
}
返回示例:



{
"code": 200,
"message": "文件消息发送成功"
}
4.4 获取聊天记录接口
URL: /api/messages/history
请求方式: POST
请求参数:



{
"senderId": "193038",    // 发送方用户ID
"receiverId": "193039",  // 接收方用户ID
"page": 1,               // 页码
"size": 20               // 每页记录数
}
返回示例:



{
"code": 200,
"message": "获取聊天记录成功",
"data": {
"total": 100,  // 总记录数
"messages": [
{
"messageId": 1,
"type": "TEXT",
"content": "你好",
"senderId": "193038",
"receiverId": "193039",
"status": "READ",
"timestamp": "2025-03-22T10:00:00"
},
{
"messageId": 2,
"type": "FILE",
"content": "http://file.url",
"senderId": "193038",
"receiverId": "193039",
"status": "DELIVERED",
"timestamp": "2025-03-22T10:01:00"
}
]
}
}
4.5 撤回消息接口
URL: /api/messages/revoke
请求方式: POST
请求参数:



{
"messageId": 12345,   // 消息ID
"senderId": "193038", // 发送方用户ID
"status": "REVOKED"   // 撤回状态
}
返回示例:



{
"code": 200,
"message": "消息撤回成功"
}


✅ WebSocket 模块相关要求和示例
5.1 WebSocket 连接要求
连接地址: /ws

协议: STOMP over WebSocket

客户端发送请求:

连接：ws://localhost:8085/ws

订阅：/topic/chat/{receiverId}

发送消息：/app/chat

消息格式：




{
"type": "TEXT",     // 消息类型（TEXT, MEDIA, FILE）
"content": "你好！", // 消息内容
"senderId": "193038",
"receiverId": "193039",
"status": "SENT"     // 消息状态（SENT, DELIVERED, READ）
}
5.2 WebSocket 消息推送
订阅与推送：
客户端可以订阅 "/topic/chat/{receiverId}" 来接收消息推送。

服务器端推送到 "/topic/chat/{receiverId}"：

示例：



{
"type": "TEXT",           // 消息类型：TEXT, MEDIA, FILE
"content": "你好，今天怎么样？", // 消息内容
"senderId": "193038",     // 发送者ID
"receiverId": "193039",   // 接收者ID
"status": "SENT",          // 消息状态：SENT, DELIVERED, READ
"timestamp": "2025-03-22T10:00:00"
}
服务器端推送消息：
kotlin


webSocketService.sendToUser(receiverId, "/topic/chat/$receiverId", message)
5.3 WebSocket 发送消息接口
URL: /api/messages/send

请求方式: POST

请求参数:




{
"type": "TEXT",           // 消息类型：TEXT, MEDIA, FILE
"content": "你好！",      // 消息内容
"senderId": "193038",     // 发送方用户ID
"receiverId": "193039",   // 接收方用户ID
"status": "SENT"          // 消息状态：SENT, DELIVERED, READ
}
返回示例:




{
"code": 200,
"message": "消息发送成功"
}