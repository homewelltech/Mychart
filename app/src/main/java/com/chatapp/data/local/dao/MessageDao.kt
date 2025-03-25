package com.chatapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chatapp.data.local.entity.Message

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: Message)

    @Query("""
        SELECT * FROM message 
        WHERE (senderId = :userId AND receiverId = :otherId) 
           OR (senderId = :otherId AND receiverId = :userId)
        ORDER BY timestamp DESC
    """)
    suspend fun getChatMessages(userId: String, otherId: String): List<Message>
}
