package com.chylyng.project.mediaplayers

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.RESIZE_MODE_FILL
import com.google.android.exoplayer2.ui.PlayerView

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
        //playerView2 = requireActivity().findViewById<PlayerView>(R.id.pl2)
//        requireActivity().findViewById<Button>(R.id.bt2).setOnClickListener {
//            exoPlayer.release()
//        }
        exoPlayer = SimpleExoPlayer.Builder(requireActivity()).build()

        exoPlayer.apply {
            playWhenReady = true
            setMediaItem(
                MediaItem.fromUri(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath+"/test.mp4")

            )

        }
        playerView.player = exoPlayer
        playerView.useController = false
        playerView.resizeMode = RESIZE_MODE_FILL
        exoPlayer.prepare()
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