package com.chatapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chatapp.data.local.entity.Group

@Dao
interface GroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(group: Group)

    @Query("SELECT * FROM group_table WHERE groupId = :groupId LIMIT 1")
    suspend fun getGroupById(groupId: String): Group?
}
