package com.chylyng.project.mediaplayers

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.media2.exoplayer.external.ExoPlayerFactory
import androidx.navigation.fragment.findNavController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.rtsp.RtspMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    lateinit var playerView: PlayerView
    lateinit var playerView2: PlayerView
    lateinit var exoPlayer: SimpleExoPlayer
    lateinit var exoPlayer2: SimpleExoPlayer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playerView = requireActivity().findViewById<PlayerView>(R.id.playerView)
        playerView2 = requireActivity().findViewById<PlayerView>(R.id.pl2)
        requireActivity().findViewById<Button>(R.id.bt2).setOnClickListener {
            exoPlayer.release()
        }
        exoPlayer = SimpleExoPlayer.Builder(requireActivity()).build()
        exoPlayer2 = SimpleExoPlayer.Builder(requireActivity()).build()

        exoPlayer.apply {
            playWhenReady = false
            setMediaItem(
                MediaItem.fromUri(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath+"/cc.m4v")

            )
            play()
            lifecycleScope.launch {
                delay(8000L)
                prepare()
            }


        }
        playerView.player = exoPlayer
//        exoPlayer2.apply {
//            playWhenReady = false
//            setMediaItem(
//                MediaItem.fromUri("/storage/emulated/0/cc.lookr.resource/95106.mp4")
//
//            )
//            prepare()
//            seekTo(100L)
//        //playWhenReady = true
//            repeatMode = Player.REPEAT_MODE_ALL
//        }
//        playerView2.player = exoPlayer2
        //exoPlayer.play()
    }

    override fun onStop() {
        super.onStop()
        exoPlayer.stop()
        exoPlayer.release()
    }
}