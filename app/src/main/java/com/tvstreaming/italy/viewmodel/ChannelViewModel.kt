package com.tvstreaming.italy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tvstreaming.italy.database.Channel
import com.tvstreaming.italy.repository.ChannelRepository
import kotlinx.coroutines.launch

class ChannelViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ChannelRepository(application)

    val allChannels: LiveData<List<Channel>> = repository.allChannels

    init {
        viewModelScope.launch {
            repository.preloadChannelsIfNeeded()
        }
    }

    fun addChannel(name: String, url: String, category: String) {
        viewModelScope.launch {
            repository.addChannel(
                Channel(name = name, streamUrl = url, category = category, isPreloaded = false)
            )
        }
    }

    fun deleteChannel(channel: Channel) {
        viewModelScope.launch { repository.deleteChannel(channel) }
    }

    fun toggleFavorite(channel: Channel) {
        viewModelScope.launch { repository.toggleFavorite(channel) }
    }
}
