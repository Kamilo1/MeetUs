package com.example.meetus

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class AddNewGroupActivity : AppCompatActivity() {

    private lateinit var groupNameEditText: EditText
    private lateinit var availabilityButton: Button
    private lateinit var addMembersEditText: EditText
    private lateinit var searchMembersEditText: EditText
    private lateinit var shareLinkEditText: EditText
    private lateinit var settleAccountCheckBox: CheckBox
    private lateinit var createGroupButton: Button
    private lateinit var cancelButton: Button

    private var startDate: Calendar? = null
    private var endDate: Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_group)

        groupNameEditText = findViewById(R.id.editTextGroupName)
        availabilityButton = findViewById(R.id.buttonSelectAvailability)
        addMembersEditText = findViewById(R.id.editTextAddMembers)
        searchMembersEditText = findViewById(R.id.editTextSearchMembers)
        shareLinkEditText = findViewById(R.id.editTextShareLink)
        settleAccountCheckBox = findViewById(R.id.checkboxSettleAccounts)
        createGroupButton = findViewById(R.id.buttonCreateGroup)
        cancelButton = findViewById(R.id.buttonCancelCreateGroup)

        // Obsługa przycisku "Select Availability" - wybór daty od-do
        availabilityButton.setOnClickListener {
            showStartDatePicker()
        }

        // Obsługa przycisku "Create Group"
        createGroupButton.setOnClickListener {
            val groupName = groupNameEditText.text.toString()
            if (groupName.isEmpty()) {
                Toast.makeText(this, "Please enter a group name", Toast.LENGTH_SHORT).show()
            } else {
                // Logika tworzenia grupy - do zaimplementowania
                Toast.makeText(this, "Group created: $groupName", Toast.LENGTH_SHORT).show()
            }
        }

        // Obsługa przycisku "Cancel"
        cancelButton.setOnClickListener {
            finish()
        }
    }

    // Wyświetlanie dialogu wyboru daty początkowej (od)
    private fun showStartDatePicker() {
        val currentCalendar = Calendar.getInstance()
        val year = currentCalendar.get(Calendar.YEAR)
        val month = currentCalendar.get(Calendar.MONTH)
        val day = currentCalendar.get(Calendar.DAY_OF_MONTH)

        // Dialog wyboru daty początkowej
        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            startDate = Calendar.getInstance()
            startDate?.set(selectedYear, selectedMonth, selectedDay)
            showEndDatePicker() // Po wyborze daty początkowej, pokaż dialog dla daty końcowej
        }, year, month, day)
        datePickerDialog.show()
    }

    // Wyświetlanie dialogu wyboru daty końcowej (do)
    private fun showEndDatePicker() {
        val currentCalendar = Calendar.getInstance()
        val year = currentCalendar.get(Calendar.YEAR)
        val month = currentCalendar.get(Calendar.MONTH)
        val day = currentCalendar.get(Calendar.DAY_OF_MONTH)

        // Dialog wyboru daty końcowej
        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            endDate = Calendar.getInstance()
            endDate?.set(selectedYear, selectedMonth, selectedDay)

            // Wyświetl wybrane daty w Toast lub zaktualizuj UI
            val startDateString = "${startDate?.get(Calendar.DAY_OF_MONTH)}/${startDate?.get(Calendar.MONTH)?.plus(1)}/${startDate?.get(Calendar.YEAR)}"
            val endDateString = "${selectedDay}/${selectedMonth + 1}/${selectedYear}"

            availabilityButton.text = "Selected: $startDateString to $endDateString"
        }, year, month, day)
        datePickerDialog.show()
    }
}
