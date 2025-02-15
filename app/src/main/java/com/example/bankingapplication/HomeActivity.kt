package com.example.bankingapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var backArrow = findViewById<ImageView>(R.id.backArrow);
        var menuArrow = findViewById<ImageView>(R.id.menuArrow);
        var toolbarTitle = findViewById<TextView>(R.id.toolbarTitle);
        var webImage = findViewById<ImageView>(R.id.webImage);
        var mapImage = findViewById<ImageView>(R.id.mapImage);
        var dialImage = findViewById<ImageView>(R.id.dialImage);
        var shareImage = findViewById<ImageView>(R.id.shareImage);

        var username = intent.getStringExtra("username");
        toolbarTitle.text = "Welcome $username";

        backArrow.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent);
            finish();
        }

        webImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
            startActivity(intent);
        }
        mapImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:12.9716,77.5946?q=ChristUniversity"));
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent);
        }
        dialImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:8348713192")
            startActivity(intent)
        }

        shareImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, "Hi Christite!!, I am using banking application")
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share via"))
        }


    }
}