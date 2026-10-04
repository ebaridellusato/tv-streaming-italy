package com.tvstreaming.italy.repository

import android.app.Application
import com.tvstreaming.italy.database.Channel
import com.tvstreaming.italy.database.ChannelDao
import com.tvstreaming.italy.database.ChannelDatabase
import com.tvstreaming.italy.data.PreloadedChannels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChannelRepository(application: Application) {

    private val channelDao: ChannelDao = ChannelDatabase.getDatabase(application).channelDao()

    val allChannels = channelDao.getAllChannels()

    suspend fun preloadChannelsIfNeeded() = withContext(Dispatchers.IO) {
        if (channelDao.countPreloaded() == 0) {
            channelDao.insertAll(PreloadedChannels.getChannels())
        }
    }

    suspend fun addChannel(channel: Channel) = withContext(Dispatchers.IO) {
        channelDao.insert(channel)
    }

    suspend fun deleteChannel(channel: Channel) = withContext(Dispatchers.IO) {
        channelDao.delete(channel)
    }

    suspend fun toggleFavorite(channel: Channel) = withContext(Dispatchers.IO) {
        channelDao.setFavorite(channel.id, !channel.isFavorite)
    }
}
