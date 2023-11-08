package com.example.projectcse227

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.util.Calendar

class MonthlyMeetingActivity1 : AppCompatActivity(), View.OnClickListener {
    var mtoolbar: Toolbar? = null
    var btnDatePicker: Button? = null
    var btnTimePicker: Button? = null
    var btnSet: Button? = null
    var txtDate: EditText? = null
    var txtTime: EditText? = null
    private var mYear = 0
    private var mMonth = 0
    private var mDay = 0
    private var mHour = 0
    private var mMinute = 0
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monthly_meeting)
        mtoolbar = findViewById<View>(R.id.meetingtoolbar) as Toolbar
        mtoolbar!!.title = "Monthly Meeting"
        mtoolbar!!.setNavigationIcon(R.drawable.ic_arrow_back)
        mtoolbar!!.setNavigationOnClickListener {
            startActivity(Intent(this@MonthlyMeetingActivity1, DashboardActivity1::class.java))
            finish()
        }
        btnDatePicker = findViewById<View>(R.id.btn_date) as Button
        btnTimePicker = findViewById<View>(R.id.btn_time) as Button
        txtDate = findViewById<View>(R.id.in_date) as EditText
        txtTime = findViewById<View>(R.id.in_time) as EditText
        btnSet = findViewById<View>(R.id.set) as Button
        btnDatePicker!!.setOnClickListener(this)
        btnTimePicker!!.setOnClickListener(this)
        btnSet!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v === btnDatePicker) {

            // Get Current Date
            val c = Calendar.getInstance()
            mYear = c[Calendar.YEAR]
            mMonth = c[Calendar.MONTH]
            mDay = c[Calendar.DAY_OF_MONTH]
            val datePickerDialog = DatePickerDialog(this,
                { view, year, monthOfYear, dayOfMonth -> txtDate!!.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year) },
                mYear,
                mMonth,
                mDay
            )
            datePickerDialog.show()
        }
        if (v === btnTimePicker) {

            // Get Current Time
            val c = Calendar.getInstance()
            mHour = c[Calendar.HOUR_OF_DAY]
            mMinute = c[Calendar.MINUTE]

            // Launch Time Picker Dialog
            val timePickerDialog = TimePickerDialog(this,
                { view, hourOfDay, minute -> txtTime!!.setText("$hourOfDay:$minute") },
                mHour,
                mMinute,
                false
            )
            timePickerDialog.show()
        }
        if (v === btnSet) {
            Toast.makeText(this, "Meeting is scheduled", Toast.LENGTH_SHORT).show()
        }
    }
}