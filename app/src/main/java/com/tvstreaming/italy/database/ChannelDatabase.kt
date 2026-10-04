package com.tvstreaming.italy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Channel::class], version = 1, exportSchema = false)
abstract class ChannelDatabase : RoomDatabase() {

    abstract fun channelDao(): ChannelDao

    companion object {
        @Volatile
        private var INSTANCE: ChannelDatabase? = null

        fun getDatabase(context: Context): ChannelDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChannelDatabase::class.java,
                    "tv_streaming_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
