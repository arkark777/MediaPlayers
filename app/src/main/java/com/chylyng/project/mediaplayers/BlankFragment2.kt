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
lateinit var textureView2: TextureView
lateinit var mSurface2: Surface
var ijkMediaPlayer2: IjkMediaPlayer? = null

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ijkMediaPlayer2 = IjkMediaPlayer()
        textureView2 = requireActivity().findViewById(R.id.textureView3)
        textureView2.surfaceTextureListener = object :TextureView.SurfaceTextureListener{
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

                ijkMediaPlayer2?.apply {
                    setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 0)
                    setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 0)
                    setOption(
                        IjkMediaPlayer.OPT_CATEGORY_PLAYER,
                        "mediacodec_mpeg4",
                        1
                    )
                    setSurface(mSurface)
                    isLooping = true
                    dataSource = (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath+"/test.mp4")
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
        ijkMediaPlayer2?.let {
            it.stop()
            it.release()
        }
    }
}