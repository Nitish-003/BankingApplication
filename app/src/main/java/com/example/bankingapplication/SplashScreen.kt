package com.example.bankingapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val videoView = findViewById<VideoView>(R.id.videoView)
        val videoPath = "android.resource://$packageName/${R.raw.vid}"
        val uri = Uri.parse(videoPath)

        videoView.setVideoURI(uri)

        // Adjust video size dynamically
        videoView.post {
            val metrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(metrics)

            val screenWidth = metrics.widthPixels
            val screenHeight = metrics.heightPixels

            val videoWidth = (screenWidth * 0.9).toInt() // 90% of screen width
            val videoHeight = (videoWidth * 9) / 16 // 16:9 aspect ratio

            videoView.layoutParams.width = videoWidth
            videoView.layoutParams.height = videoHeight
            videoView.requestLayout()
        }

        videoView.setOnCompletionListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        videoView.start()
    }
}
