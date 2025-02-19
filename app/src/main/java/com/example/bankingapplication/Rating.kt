package com.example.bankingapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RatingBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val suggestionsEditText = findViewById<EditText>(R.id.editTextTextMultiLine) // FIXED
        val complaintsEditText = findViewById<EditText>(R.id.complaintTextMultiLine) // FIXED
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        // Function to update progress bar dynamically
        fun updateProgress() {
            var progress = 0
            if (ratingBar.rating > 0) progress += 40 // Rating completed = 40%
            if (suggestionsEditText.text.toString().isNotEmpty()) progress += 30 // Suggestions = 30%
            if (complaintsEditText.text.toString().isNotEmpty()) progress += 30 // Complaints = 30%
            progressBar.progress = progress
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
    }
}
