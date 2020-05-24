package alex.example.org

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class YoutubeActivity : YouTubeBaseActivity(),YouTubePlayer.OnInitializedListener {
 val TAG="YoutubeActivity"
    val YOUTUBE_VIDEO_ID="UXuar3bp_VA"
     val YOUTUBE_PLAYLIST="RDUXuar3bp_VA"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)
        val layout = findViewById<ConstraintLayout>(R.id.activity_youtube)
        val playerView=YouTubePlayerView(this)
        playerView.layoutParams=ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)
        playerView.initialize(getString(R.string.GOOGLE_API_KEY),this)

    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        youTubePlayer: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        Log.d(TAG,"onInitializationSuccess:provider is ${provider?.javaClass}")
        Log.d(TAG,"onInitializationSuccess:youTubePlayer is ${youTubePlayer?.javaClass}")
        Toast.makeText(this,"Initialized Youtube Player succcessfully",Toast.LENGTH_SHORT).show()
        if(!wasRestored)
        {
            youTubePlayer?.loadVideo(YOUTUBE_VIDEO_ID)
        }
        else{
            youTubePlayer?.play()
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        youTubeInitializationResult: YouTubeInitializationResult?
    ) {
        val REQUEST_CODE=0
        if(youTubeInitializationResult?.isUserRecoverableError==true)
        {
            youTubeInitializationResult.getErrorDialog(this,REQUEST_CODE).show()
        }
        else{
            val errorMessage="There was a error initializing theYoutubePlayer($youTubeInitializationResult)"
            Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show()
        }
    }
}
