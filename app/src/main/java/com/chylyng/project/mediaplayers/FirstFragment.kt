package com.chylyng.project.mediaplayers

import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    val mediaPlayer = MediaPlayer()
    lateinit var textureView:TextureView
    lateinit var mSurface:Surface

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textureView = requireActivity().findViewById(R.id.textureView)
        textureView.surfaceTextureListener = object :TextureView.SurfaceTextureListener{
            /**
             * Invoked when a [TextureView]'s SurfaceTexture is ready for use.
             *
             * @param surface The surface returned by
             * [android.view.TextureView.getSurfaceTexture]
             * @param width The width of the surface
             * @param height The height of the surface
             */
            override fun onSurfaceTextureAvailable(
                surface: SurfaceTexture,
                width: Int,
                height: Int
            ) {
                mSurface = Surface(surface)
                mediaPlayer.apply {
                    setSurface(mSurface)
                    setDataSource(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath+"/cc.m4v")
                    //setDataSource("storge/emulated/0/77355_2.mp3")
                    prepareAsync()
                    setOnPreparedListener {
                        start()
                    }
                }

            }

            /**
             * Invoked when the [SurfaceTexture]'s buffers size changed.
             *
             * @param surface The surface returned by
             * [android.view.TextureView.getSurfaceTexture]
             * @param width The new width of the surface
             * @param height The new height of the surface
             */
            override fun onSurfaceTextureSizeChanged(
                surface: SurfaceTexture,
                width: Int,
                height: Int
            ) {

            }

            /**
             * Invoked when the specified [SurfaceTexture] is about to be destroyed.
             * If returns true, no rendering should happen inside the surface texture after this method
             * is invoked. If returns false, the client needs to call [SurfaceTexture.release].
             * Most applications should return true.
             *
             * @param surface The surface about to be destroyed
             */
            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                return true
            }

            /**
             * Invoked when the specified [SurfaceTexture] is updated through
             * [SurfaceTexture.updateTexImage].
             *
             * @param surface The surface just updated
             */
            override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {

            }

        }


    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}