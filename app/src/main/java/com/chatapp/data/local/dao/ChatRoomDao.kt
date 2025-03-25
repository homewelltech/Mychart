package com.chatapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chatapp.data.local.entity.ChatRoom

@Dao
interface ChatRoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChatRoom(chatRoom: ChatRoom)

    @Query("SELECT * FROM chatroom WHERE id = :roomId LIMIT 1")
    suspend fun getChatRoomById(roomId: String): ChatRoom?
}
