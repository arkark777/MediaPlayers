package com.chylyng.project.mediaplayers

import android.graphics.SurfaceTexture
import android.os.Bundle
import android.os.Environment
import android.view.*
import androidx.fragment.app.Fragment
import tv.danmaku.ijk.media.player.IjkMediaPlayer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var textureView: TextureView
lateinit var mSurface: Surface
var ijkMediaPlayer: IjkMediaPlayer? = null

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ijkMediaPlayer = IjkMediaPlayer()
        textureView = requireActivity().findViewById(R.id.textureView2)
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

                ijkMediaPlayer?.apply {
                    setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 0)
                    setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1)
                    setOption(
                        IjkMediaPlayer.OPT_CATEGORY_PLAYER,
                        "mediacodec_mpeg4",
                        1
                    )
                    isLooping = true
                    setSurface(mSurface)
                    dataSource = (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath+"/cc.m4v")
                    prepareAsync()
                    setOnPreparedListener {
                        it.start()
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

    override fun onStop() {
        super.onStop()
        ijkMediaPlayer?.let {
            it.stop()
            it.release() }


    }
}