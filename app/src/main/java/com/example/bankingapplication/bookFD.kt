package com.example.bankingapplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class bookFD : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var editTextAccountName: EditText
    private lateinit var spinnerAccountType: Spinner
    private lateinit var radioGroup: RadioGroup
    private lateinit var editTextStartDate: EditText
    private lateinit var checkBoxTnC: CheckBox
    private lateinit var fabSubmit: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_book_fd)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI elements
        editTextAccountName = findViewById(R.id.editTextText2)
        spinnerAccountType = findViewById(R.id.spinnerAccountType)
        radioGroup = findViewById(R.id.radioGroup)
        editTextStartDate = findViewById(R.id.editTextStartDate)
        checkBoxTnC = findViewById(R.id.checkBoxTnC)
        fabSubmit = findViewById(R.id.fabSubmit)

        val toolbarTitle = findViewById<TextView>(R.id.toolbarTitle)
        val username = intent.getStringExtra("username")
        toolbarTitle.text = "Welcome $username"

        val backArrow = findViewById<ImageView>(R.id.backArrow)
        backArrow.setOnClickListener {
            finish()
        }

        // Spinner for Account Type
        val accountTypes = arrayOf("Savings", "Current", "Fixed Deposit")
        val accountAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, accountTypes)
        spinnerAccountType.adapter = accountAdapter

        // Date Picker Dialog for Start Date
        editTextStartDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                editTextStartDate.setText(selectedDate)
            }, year, month, day)

            datePickerDialog.show()
        }

        // Floating Action Button Click Listener
        fabSubmit.setOnClickListener {
            if (!checkBoxTnC.isChecked) {
                Toast.makeText(this, "Please accept Terms and Conditions", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            AlertDialog.Builder(this)
                .setTitle("Submission Successful")
                .setMessage("Your Fixed Deposit request has been submitted!")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()

            // Reset Fields
            editTextAccountName.setText("")
            editTextStartDate.setText("")
            checkBoxTnC.isChecked = false
            spinnerAccountType.setSelection(0)
            radioGroup.clearCheck()
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
