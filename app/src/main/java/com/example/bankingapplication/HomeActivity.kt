package com.example.bankingapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

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

        println("Hello Home")

        val menuArrow = findViewById<ImageView>(R.id.menuArrow)
        val toolbarTitle = findViewById<TextView>(R.id.toolbarTitle)
        val webImage = findViewById<ImageView>(R.id.webImage)
        val mapImage = findViewById<ImageView>(R.id.mapImage)
        val dialImage = findViewById<ImageView>(R.id.dialImage)
        val shareImage = findViewById<ImageView>(R.id.shareImage)
        val rateImage = findViewById<ImageView>(R.id.rateImage)
        val depositImage = findViewById<ImageView>(R.id.depositImage)

        val balanceTextView = findViewById<TextView>(R.id.balanceTextView)
        val balanceSwitch = findViewById<Switch>(R.id.switch1)

        val username = intent.getStringExtra("username")
        toolbarTitle.text = "Welcome $username"

        val backArrow = findViewById<ImageView>(R.id.backArrow)
        backArrow.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        menuArrow.setOnClickListener { view ->
            val popupMenu = PopupMenu(this, view)
            popupMenu.menuInflater.inflate(R.menu.appbar_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.nav_home -> { // "Book FD" option
                        val intent = Intent(this, bookFD::class.java)
                        intent.putExtra("username", "Nitish")
                        startActivity(intent)
                        true
                    }
                    R.id.nav_deposit -> { // "Log out" option
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }

            popupMenu.show()
        }


        rateImage.setOnClickListener {
            val intent = Intent(this, Rating::class.java)
            intent.putExtra("username", "Nitish")
            startActivity(intent)
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

        shareImage.setOnClickListener { view ->
            val popupMenu = PopupMenu(this, view)
            popupMenu.menuInflater.inflate(R.menu.share_menu, popupMenu.menu) // Assuming your XML file is named share_menu.xml

            popupMenu.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.nav_sms -> { // SMS Option
                        val smsIntent = Intent(Intent.ACTION_VIEW)
                        smsIntent.data = Uri.parse("sms:") // Opens SMS app
                        smsIntent.putExtra("sms_body", "Hi Christite!!, I am using banking application")
                        startActivity(smsIntent)
                        true
                    }
                    R.id.nav_mail -> { // Email Option
                        val emailIntent = Intent(Intent.ACTION_SEND)
                        emailIntent.type = "message/rfc822"
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Banking Application")
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi Christite!!, I am using banking application")
                        try {
                            startActivity(Intent.createChooser(emailIntent, "Send via Email"))
                        } catch (ex: Exception) {
                            Toast.makeText(this, "No Email app found", Toast.LENGTH_SHORT).show()
                        }
                        true
                    }
                    else -> false
                }
            }

            popupMenu.show()
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

        depositImage.setOnClickListener {
            val intent = Intent(this, bookFD::class.java)
            intent.putExtra("username", "Nitish")
            startActivity(intent)
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("username", "Nitish")
                    startActivity(intent)
                    true
                }
                R.id.nav_deposit -> {
                    val intent = Intent(this, bookFD::class.java)
                    intent.putExtra("username", "Nitish")
                    startActivity(intent)
                    true
                }
                R.id.nav_web -> {
                    val intent = Intent(this, Rating::class.java)
                    intent.putExtra("username", "Nitish")
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }



    }
}
