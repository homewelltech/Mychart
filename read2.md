ChatApp
├── app
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── chatapp
│   │   │   │           ├── api
│   │   │   │           │   ├── UserApiService.kt            // 用户相关接口
│   │   │   │           │   ├── MessageApiService.kt         // 消息相关接口
│   │   │   │           │   ├── FriendApiService.kt          // 好友相关接口
│   │   │   │           │   └── GroupApiService.kt           // 群组相关接口
│   │   │   │           ├── repository
│   │   │   │           │   ├── UserRepository.kt            // 用户数据处理
│   │   │   │           │   ├── MessageRepository.kt         // 消息数据处理
│   │   │   │           │   ├── FriendRepository.kt          // 好友数据处理
│   │   │   │           │   └── GroupRepository.kt           // 群组数据处理
│   │   │   │           ├── service
│   │   │   │           │   ├── UserService.kt               // 用户业务逻辑
│   │   │   │           │   ├── MessageService.kt            // 消息业务逻辑
│   │   │   │           │   ├── FriendService.kt             // 好友业务逻辑
│   │   │   │           │   └── GroupService.kt              // 群组业务逻辑
│   │   │   │           ├── model
│   │   │   │           │   ├── User.kt                      // 用户模型
│   │   │   │           │   ├── Friend.kt                    // 好友模型
│   │   │   │           │   ├── Group.kt                     // 群组模型
│   │   │   │           │   ├── ChatMessage.kt               // 聊天消息模型
│   │   │   │           │   ├── ApiResponse.kt               // API 响应封装
│   │   │   │           │   ├── SendMessageRequest.kt        // 发送消息请求模型
│   │   │   │           │   └── PageResult.kt                // 分页结果模型
│   │   │   │           ├── viewmodel
│   │   │   │           │   ├── UserViewModel.kt             // 用户视图模型
│   │   │   │           │   ├── ChatViewModel.kt             // 聊天视图模型
│   │   │   │           │   ├── FriendViewModel.kt           // 好友视图模型
│   │   │   │           │   └── GroupViewModel.kt            // 群组视图模型
│   │   │   │           ├── ui
│   │   │   │           │   ├── screens
│   │   │   │           │   │   ├── ChatScreen.kt             // 聊天界面
│   │   │   │           │   │   ├── ContactListScreen.kt      // 联系人列表界面
│   │   │   │           │   │   ├── SettingsScreen.kt        // 设置界面
│   │   │   │           │   │   ├── ChatListScreen.kt         // 聊天列表界面
│   │   │   │           │   │   └── GroupScreen.kt            // 群组界面
│   │   │   │           │   └── components
│   │   │   │           │       ├── ChatBubble.kt             // 聊天气泡组件
│   │   │   │           │       ├── ChatInputField.kt         // 聊天输入框组件
│   │   │   │           │       ├── ChatTopBar.kt             // 聊天顶部栏组件
│   │   │   │           │       └── SettingsItem.kt           // 设置项组件
│   │   │   │           ├── di
│   │   │   │           │   ├── AppModule.kt                 // Hilt 注入模块
│   │   │   │           │   └── WebSocketModule.kt           // WebSocket 配置模块
│   │   │   │           └── utils
│   │   │   │               ├── ErrorHandler.kt               // 错误处理工具类
│   │   │   │               └── NetworkUtils.kt               // 网络相关工具类
│   │   │   └── res
│   │   │       ├── drawable
│   │   │       │   ├── default_avatar.png                   // 默认头像图片
│   │   │       │   └── background.png                      // 背景图
│   │   │       ├── layout
│   │   │       │   └── activity_main.xml                   // 主界面布局文件
│   │   │       ├── values
│   │   │       │   ├── colors.xml                          // 颜色配置
│   │   │       │   ├── strings.xml                         // 字符串资源
│   │   │       │   └── themes.xml                          // 主题配置
│   │   │       └── mipmap
│   │   │           └── ic_launcher.png                     // 应用图标
│   └── build.gradle.kts
└── settings.gradle
