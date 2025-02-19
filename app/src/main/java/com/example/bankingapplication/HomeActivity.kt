package com.example.bankingapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
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

        val backArrow = findViewById<ImageView>(R.id.backArrow)
        val menuArrow = findViewById<ImageView>(R.id.menuArrow)
        val toolbarTitle = findViewById<TextView>(R.id.toolbarTitle)
        val webImage = findViewById<ImageView>(R.id.webImage)
        val mapImage = findViewById<ImageView>(R.id.mapImage)
        val dialImage = findViewById<ImageView>(R.id.dialImage)
        val shareImage = findViewById<ImageView>(R.id.shareImage)
        val rateImage = findViewById<ImageView>(R.id.rateImage)

        val balanceTextView = findViewById<TextView>(R.id.balanceTextView)
        val balanceSwitch = findViewById<Switch>(R.id.switch1)

        val username = intent.getStringExtra("username")
        toolbarTitle.text = "Welcome $username"

        backArrow.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        rateImage.setOnClickListener {
            val intent = Intent(this, Rating::class.java)
            startActivity(intent)
            finish()
        }

        webImage.setOnClickListener {
            val intent = Intent(this, WebPage::class.java)
            intent.putExtra("url", "https://www.google.com/")
            startActivity(intent)
        }

        mapImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:12.9716,77.5946?q=ChristUniversity"))
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
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

        // Set initial switch text
        balanceSwitch.text = "Show Balance"

        // Handle balance visibility and switch text update
        balanceSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                balanceTextView.text = "500.00" // Show balance when switch is ON
                balanceSwitch.text = "Show Balance"
            } else {
                balanceTextView.text = "XXXX" // Hide balance when switch is OFF
                balanceSwitch.text = "Hide Balance"
            }
        }





    }
}
