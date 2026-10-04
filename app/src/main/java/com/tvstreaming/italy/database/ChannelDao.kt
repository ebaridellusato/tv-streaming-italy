package com.tvstreaming.italy.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ChannelDao {

    @Query("SELECT * FROM channels ORDER BY isPreloaded DESC, name ASC")
    fun getAllChannels(): LiveData<List<Channel>>

    @Query("SELECT COUNT(*) FROM channels WHERE isPreloaded = 1")
    suspend fun countPreloaded(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(channels: List<Channel>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(channel: Channel): Long

    @Update
    suspend fun update(channel: Channel)

    @Delete
    suspend fun delete(channel: Channel)

    @Query("UPDATE channels SET isFavorite = :isFavorite WHERE id = :channelId")
    suspend fun setFavorite(channelId: Int, isFavorite: Boolean)
}
