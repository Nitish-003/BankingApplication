package com.example.bankingapplication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import androidx.activity.enableEdgeToEdge
import android.app.AlertDialog
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Rating : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rating)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find the submit button
        val submitButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val suggestionsEditText = findViewById<EditText>(R.id.editTextTextMultiLine) // FIXED
        val complaintsEditText = findViewById<EditText>(R.id.complaintTextMultiLine) // FIXED
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val backArrow = findViewById<ImageView>(R.id.backArrow)

        val toolbarTitle = findViewById<TextView>(R.id.toolbarTitle)
        val username = intent.getStringExtra("username")
        toolbarTitle.text = "Welcome $username"

        // Function to update progress bar dynamically
        fun updateProgress() {
            var progress = 0
            if (ratingBar.rating > 0) progress += 40 // Rating completed = 40%
            if (suggestionsEditText.text.toString().isNotEmpty()) progress += 30 // Suggestions = 30%
            if (complaintsEditText.text.toString().isNotEmpty()) progress += 30 // Complaints = 30%
            progressBar.progress = progress
        }

        // Handle click event
        submitButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Submission Successful")
                .setMessage("Thank you for your feedback!")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss() // Close the dialog
                }
                .show()

            suggestionsEditText.setText("")
            complaintsEditText.setText("")
            ratingBar.rating = 0f
            updateProgress()
        }

        // Update progress when rating is changed
        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            updateProgress()
        }

        // TextWatcher to update progress when user types or deletes text
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                updateProgress()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        suggestionsEditText.addTextChangedListener(textWatcher)
        complaintsEditText.addTextChangedListener(textWatcher)

        backArrow.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("username", "Nitish")
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.nav_deposit -> {
                    val intent = Intent(this, bookFD::class.java)
                    intent.putExtra("username", "Nitish")
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.nav_web -> {
                    val intent = Intent(this, Rating::class.java)
                    intent.putExtra("username", "Nitish")
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }


    }
}
