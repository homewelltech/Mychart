chatapp/
├── ChatApp.kt                      // 应用入口，使用 Hilt 进行依赖注入
├── build.gradle.kts                // Gradle 构建脚本，使用 libs.versions.toml 管理依赖
├── di/
│   └── AppModule.kt                // 提供数据库、网络服务、API 等单例
├── data/
│   ├── local/
│   │   ├── ChatDatabase.kt         // Room 数据库配置，支持离线消息、群组、用户等数据持久化
│   │   ├── dao/
│   │   │   ├── UserDao.kt          // 用户 DAO
│   │   │   ├── FriendDao.kt        // 好友 DAO
│   │   │   ├── GroupDao.kt         // 群组 DAO
│   │   │   ├── MessageDao.kt       // 消息 DAO，支持本地化消息存储与分页加载
│   │   │   └── ChatRoomDao.kt      // 聊天室 DAO
│   │   └── entity/
│   │       ├── User.kt             // 用户实体
│   │       ├── Friend.kt           // 好友实体
│   │       ├── Group.kt            // 群组实体
│   │       ├── Message.kt          // 消息实体
│   │       └── ChatRoom.kt         // 聊天室实体
│   └── remote/
│       ├── api/
│       │   ├── UserApi.kt          // 用户相关 API（注册、登录、Token 刷新、用户信息）
│       │   ├── FriendApi.kt        // 好友管理 API
│       │   ├── GroupApi.kt         // 群管理 API（创建、加入、成员管理、公告更新）
│       │   ├── MessageApi.kt       // 消息管理 API（发送、撤回、聊天记录查询）
│       │   └── ChatRoomApi.kt      // 聊天室相关 API
│       ├── interceptor/
│       │   └── LoggingInterceptor.kt // 网络请求日志拦截器
│       ├── request/
│       │   ├── UserRequest.kt      // 用户注册请求数据模型
│       │   ├── FriendRequest.kt    // 好友请求数据模型
│       │   ├── GroupRequest.kt     // 群组请求数据模型
│       │   ├── MessageRequest.kt   // 消息请求数据模型
│       │   └── ChatRoomRequest.kt  // 聊天室请求数据模型
│       └── response/
│           ├── UserResponse.kt     // 用户响应数据模型
│           ├── FriendResponse.kt   // 好友响应数据模型
│           ├── GroupResponse.kt    // 群组响应数据模型
│           ├── MessageResponse.kt  // 消息响应数据模型
│           └── ChatRoomResponse.kt // 聊天室响应数据模型
├── model/
│   ├── ApiResponse.kt              // 通用 API 响应模型（所有接口统一返回格式）
│   ├── PageResult.kt               // 分页结果模型，支持好友、群组、消息等列表分页加载
│   ├── Token.kt                    // Token 管理模型（结合 TokenManager 实现持久化）
│   ├── User.kt                     // 本地存储用户模型（与 data/local/entity/User.kt 区分业务层模型）
│   ├── Friend.kt                   // 本地存储好友模型
│   ├── Group.kt                    // 本地存储群组模型
│   ├── Message.kt                  // 本地存储消息模型
│   └── ChatRoom.kt                 // 本地存储聊天室模型
├── repository/
│   ├── UserRepository.kt           // 用户业务逻辑，封装注册、登录、Token 刷新及用户数据缓存
│   ├── FriendRepository.kt         // 好友数据处理与缓存逻辑
│   ├── GroupRepository.kt          // 群组数据管理、权限校验与实时更新
│   ├── MessageRepository.kt        // 消息数据处理，整合离线本地存储与在线消息同步
│   └── ChatRoomRepository.kt       // 聊天室相关数据处理
├── utils/
│   ├── TokenManager.kt             // Token 管理工具，建议持久化存储（如 SharedPreferences / DataStore）
│   ├── FileUtils.kt                // 文件操作工具类
│   ├── TimeFormatter.kt            // 时间格式化工具
│   └── ErrorHandler.kt             // 错误处理与提示工具
├── viewmodel/
│   ├── UserViewModel.kt            // 用户注册、登录、Token 管理业务逻辑
│   ├── FriendViewModel.kt          // 好友管理界面逻辑
│   ├── GroupViewModel.kt           // 群管理（创建、加入、成员管理）业务逻辑
│   ├── MessageViewModel.kt         // 消息处理（发送、撤回、聊天记录加载、离线同步）业务逻辑
│   └── ChatRoomViewModel.kt        // 聊天室业务逻辑
├── └── ui/
│       ├── screens/
│       │   ├── LoginScreen.kt         // 登录界面（美化后，支持状态提示、错误反馈和渐变背景）
│       │   ├── RegisterScreen.kt      // 注册界面，集成输入校验和反馈机制
│       │   ├── HomeScreen.kt          // 主页面，展示快捷入口和动态内容
│       │   ├── FriendListScreen.kt    // 好友列表界面，支持加载中和错误状态展示
│       │   ├── ChatScreen.kt          // 聊天界面，包含消息列表、输入框、发送按钮及加载动画
│       │   ├── GroupCreateScreen.kt   // 创建群组界面，支持输入验证与反馈
│       │   ├── GroupMembersScreen.kt  // 群组成员管理界面，展示成员列表及操作入口
│       │   ├── ProfileScreen.kt       // 个人信息界面，支持头像加载和信息展示
│       │   ├── SettingsScreen.kt      // 设置界面，支持开关控制及主题切换
│       │   ├── ForgotPasswordScreen.kt// 密码找回界面，提供邮箱验证与反馈
│       │   └── ChatHistoryScreen.kt   // 聊天记录界面，支持分页加载和动态更新
│       ├── components/
│       │   ├── MessageItem.kt         // 聊天消息项组件，支持卡片风格、圆角和自定义颜色
│       │   ├── FriendItem.kt          // 好友列表项组件，展示用户基本信息和头像
│       │   ├── GroupItem.kt           // 群组成员项组件，展示成员信息和操作按钮
│       │   └── ProfileItem.kt         // 个人信息展示组件，整合头像、昵称和用户 ID 信息
│       └── nav/
│           └── ChatAppNavGraph.kt     // 应用导航图，使用 sealed class 管理各页面路由
└── websocket/
     ├── ChatWebSocketClient.kt      // WebSocket 客户端，实现实时消息收发
     ├── WebSocketMessageHandler.kt  // WebSocket 消息解析与业务处理
     └── StompManager.kt             // STOMP 协议管理器，支持订阅、推送及消息格式封装


业务模块（用户管理、好友管理、群组管理、聊天功能、设置中心、WebSocket 推送）
功能闭环（用户注册、登录逻辑、缓存功能、交互功能）


功能闭环（用户注册、登录逻辑、缓存功能、交互功能）进行组织，确保代码的模块化和可扩展性。

