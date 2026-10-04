package com.tvstreaming.italy

import android.app.PictureInPictureParams
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Rational
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import com.tvstreaming.italy.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding
    private var exoPlayer: ExoPlayer? = null
    private var channelUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE

        binding.tvChannelTitle.text = intent.getStringExtra("CHANNEL_NAME") ?: "Canale"
        channelUrl = intent.getStringExtra("CHANNEL_URL") ?: ""

        initializePlayer()
    }

    private fun initializePlayer() {
        if (channelUrl.isEmpty()) return

        exoPlayer = ExoPlayer.Builder(this).build().also { player ->
            binding.playerView.player = player

            val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()
                .setConnectTimeoutMs(15000)
                .setReadTimeoutMs(15000)
                .setUserAgent("Mozilla/5.0 (Linux; Android 10) AppleWebKit/537.36")

            val mediaSource = HlsMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(channelUrl))

            player.setMediaSource(mediaSource)
            player.prepare()
            player.playWhenReady = true

            player.addListener(object : Player.Listener {
                override fun onPlayerError(error: PlaybackException) {
                    binding.tvError.visibility = View.VISIBLE
                    binding.tvError.text =
                        "Impossibile riprodurre il canale.\n" +
                        "Il flusso potrebbe non essere piu' disponibile."
                }

                override fun onPlaybackStateChanged(state: Int) {
                    when (state) {
                        Player.STATE_BUFFERING -> binding.progressBar.visibility = View.VISIBLE
                        Player.STATE_READY -> binding.progressBar.visibility = View.GONE
                        Player.STATE_ENDED -> {
                            player.seekTo(0)
                            player.play()
                        }
                        else -> Unit
                    }
                }
            })
        }
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && !isInPictureInPictureMode) {
            exoPlayer?.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer?.release()
        exoPlayer = null
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            enterPictureInPictureMode(
                PictureInPictureParams.Builder().setAspectRatio(Rational(16, 9)).build()
            )
        }
    }

    override fun onPictureInPictureModeChanged(
        isInPip: Boolean,
        newConfig: Configuration
    ) {
        super.onPictureInPictureModeChanged(isInPip, newConfig)
        binding.playerView.useController = !isInPip
    }
}
