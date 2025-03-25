chatapp/
├── ChatApp.kt                      // Application entry point (@HiltAndroidApp)
├── data
│   ├── local
│   │   ├── dao
│   │   │   ├── UserDao.kt          // User DAO for local database operations
│   │   │   ├── FriendDao.kt        // Friend DAO for local database operations
│   │   │   ├── GroupDao.kt         // Group DAO for local database operations
│   │   │   ├── MessageDao.kt       // Message DAO for local database operations
│   │   │   └── ChatRoomDao.kt      // ChatRoom DAO for local database operations
│   │   ├── entity
│   │   │   ├── User.kt             // User entity (Room database)
│   │   │   ├── Friend.kt           // Friend entity (Room database)
│   │   │   ├── Group.kt            // Group entity (Room database)
│   │   │   ├── Message.kt          // Message entity (Room database)
│   │   │   ├── ChatRoom.kt         // ChatRoom entity (Room database)
│   │   │   └── ApiResponse.kt      // General API response model
│   │   └── ChatDatabase.kt         // Room database configuration
│   ├── remote
│   │   ├── api
│   │   │   ├── UserApi.kt          // User-related API requests
│   │   │   ├── FriendApi.kt        // Friend-related API requests
│   │   │   ├── GroupApi.kt         // Group-related API requests
│   │   │   ├── MessageApi.kt       // Message-related API requests
│   │   │   └── ChatRoomApi.kt      // ChatRoom-related API requests
│   │   ├── response
│   │   │   ├── UserResponse.kt     // Response model for user data
│   │   │   ├── FriendResponse.kt   // Response model for friend data
│   │   │   ├── GroupResponse.kt    // Response model for group data
│   │   │   ├── MessageResponse.kt  // Response model for message data
│   │   │   └── ChatRoomResponse.kt // Response model for chat room data
│   │   ├── request
│   │   │   ├── UserRequest.kt      // Request model for user-related data
│   │   │   ├── FriendRequest.kt    // Request model for friend-related data
│   │   │   ├── GroupRequest.kt     // Request model for group-related data
│   │   │   ├── MessageRequest.kt   // Request model for message-related data
│   │   │   └── ChatRoomRequest.kt  // Request model for chat room-related data
│   │   └── interceptor
│   │       └── LoggingInterceptor.kt // Network request logging interceptor
├── repository
│   ├── UserRepository.kt           // User-related repository logic
│   ├── FriendRepository.kt         // Friend-related repository logic
│   ├── GroupRepository.kt          // Group-related repository logic
│   ├── MessageRepository.kt        // Message-related repository logic
│   └── ChatRoomRepository.kt       // ChatRoom-related repository logic
├── model
│   ├── User.kt                     // User model for local storage
│   ├── Friend.kt                   // Friend model for local storage
│   ├── Group.kt                    // Group model for local storage
│   ├── Message.kt                  // Message model for local storage
│   ├── ChatRoom.kt                 // ChatRoom model for local storage
│   ├── ApiResponse.kt              // General API response model
│   ├── PageResult.kt               // Pagination model for API responses
│   └── Token.kt                    // Token management model
├── viewmodel
│   ├── UserViewModel.kt            // ViewModel for user-related logic
│   ├── FriendViewModel.kt          // ViewModel for friend-related logic
│   ├── GroupViewModel.kt           // ViewModel for group-related logic
│   ├── MessageViewModel.kt         // ViewModel for message-related logic
│   └── ChatRoomViewModel.kt        // ViewModel for chat room-related logic
├── ui
│   ├── screens
│   │   ├── LoginScreen.kt          // UI screen for user login
│   │   ├── RegisterScreen.kt       // UI screen for user registration
│   │   ├── HomeScreen.kt           // Main UI screen after login
│   │   ├── FriendListScreen.kt     // UI screen for displaying friend list
│   │   ├── ChatScreen.kt           // UI screen for displaying chat interface
│   │   ├── GroupCreateScreen.kt    // UI screen for creating groups
│   │   ├── GroupMembersScreen.kt   // UI screen for managing group members
│   │   ├── ProfileScreen.kt        // UI screen for displaying user profile
│   │   ├── SettingsScreen.kt       // UI screen for settings
│   │   ├── ForgotPasswordScreen.kt // UI screen for password recovery
│   │   └── ChatHistoryScreen.kt    // UI screen for displaying chat history
│   ├── components
│   │   ├── MessageItem.kt          // Component for displaying individual messages
│   │   ├── FriendItem.kt           // Component for displaying individual friends
│   │   ├── GroupItem.kt            // Component for displaying individual group info
│   │   └── ProfileItem.kt          // Component for displaying profile info
│   └── nav
│       └── ChatAppNavGraph.kt      // Navigation graph for the app's routes
├── websocket
│   ├── ChatWebSocketClient.kt      // WebSocket client for handling real-time messaging
│   ├── WebSocketMessageHandler.kt // Handles incoming WebSocket messages
│   └── StompManager.kt             // Manages STOMP protocol over WebSocket
├── di
│   └── AppModule.kt                // Dependency injection module (Dagger/Hilt)
├── utils
│   ├── TokenManager.kt             // Utility class for managing authentication tokens
│   ├── FileUtils.kt               // Utility class for handling file operations
│   ├── TimeFormatter.kt           // Utility class for formatting time
│   └── ErrorHandler.kt            // Utility class for error handling and displaying messages


业务模块（用户管理、好友管理、群组管理、聊天功能、设置中心、WebSocket 推送）
功能闭环（用户注册、登录逻辑、缓存功能、交互功能）


功能闭环（用户注册、登录逻辑、缓存功能、交互功能）进行组织，确保代码的模块化和可扩展性。

