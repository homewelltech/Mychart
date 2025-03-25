package com.chatapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chatapp.data.local.entity.Friend

@Dao
interface FriendDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFriend(friend: Friend)

    @Query("SELECT * FROM friend WHERE userId = :userId")
    suspend fun getFriendsByUserId(userId: String): List<Friend>
}
