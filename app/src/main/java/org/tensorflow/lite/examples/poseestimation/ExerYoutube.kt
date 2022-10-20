package org.tensorflow.lite.examples.poseestimation

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.VideoView

class ExerYoutube : AppCompatActivity() {

    var video: VideoView? = null
    private var mediaPlayer: MediaPlayer? = null
    lateinit var startbutton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exer_youtube)

        video = findViewById(R.id.videoView)
        startbutton = findViewById(R.id.buttonSTART)

        val intent = intent
        val user_id = intent.getStringExtra("id")
        val exer_name = intent.getStringExtra("exer_name")

        //video?.setVideoURI(Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"))

        // val VIDEO_PATH = "android.resource://" + "com.pickersoft.myvideoview" + "/" + R.raw.sample2
        val VIDEO_PATH = "android.resource://" + "org.tensorflow.lite.examples.poseestimation" + "/" +R.raw.sample2
        var uri: Uri = Uri.parse(VIDEO_PATH)
        video?.setVideoURI(uri)

        startbutton.setOnClickListener {
            val nextIntent = Intent(this@ExerYoutube, InsertSetRep::class.java) // 다음 화면으로 넘어가기 위한 인텐트 객체 생성
            nextIntent.putExtra("id", user_id)
            nextIntent.putExtra("exer_name", exer_name)
            startActivity(nextIntent)
        }
    }

    fun onPlay(view: View) {
        // Play button click!
        video?.start()
    }
    fun onStop(view: View) {
        // Stop button click!
        video?.pause()
    }

//    fun onStart(view: View){
//
//        // 사운드 재생
////        if (mediaPlayer == null) {
////            mediaPlayer = MediaPlayer.create(this, R.raw.start)
////        }
////        mediaPlayer?.start()
//
//        // 운동페이지로 인텐트 이동
//        val nextIntent = Intent(this, InsertSetRep::class.java) // 다음 화면으로 넘어가기 위한 인텐트 객체 생성
//        nextIntent.putExtra("id", user_id)
//        nextIntent.putExtra("exer_name", "exer_three")
//        startActivity(intent)
//    }
}