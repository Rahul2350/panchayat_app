package com.example.projectcse227

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Calendar
import com.example.projectcse227.databinding.ActivityMainBinding

class GrievenceRegActivity1 : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding : ActivityMainBinding
    private lateinit var database : DatabaseReference
    var send: Button? = null
    var mtoolbar: Toolbar? = null
    var spinner: Spinner? = null
    var fullname: TextInputEditText? = null
    var address: TextInputEditText? = null
    var phonenumber: TextInputEditText? = null
    var wardnumber: TextInputEditText? = null
    var grivence: TextInputEditText? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grievence_reg)
        fullname = findViewById<TextInputEditText>(R.id.fullname)
        address = findViewById<TextInputEditText>(R.id.address)
        phonenumber = findViewById<TextInputEditText>(R.id.phonenumber)
        wardnumber = findViewById<TextInputEditText>(R.id.wardnumber)
        grivence = findViewById<TextInputEditText>(R.id.grivence)
        send = findViewById<View>(R.id.send) as Button?
        send!!.setOnClickListener { addgrivence() }
        mtoolbar = findViewById<View>(R.id.g2toolbar) as Toolbar?
        mtoolbar!!.title = "Grievence Registration"
        mtoolbar!!.setNavigationIcon(R.drawable.ic_arrow_back)
        mtoolbar!!.setNavigationOnClickListener {
            startActivity(Intent(this@GrievenceRegActivity1, GrievenceActivity1::class.java))
            finish()
        }
        spinner = findViewById<View>(R.id.categorySpinner) as Spinner?
        val categories: MutableList<String> = ArrayList()
        categories.add("Water Supply")
        categories.add("Leakage Repair")
        categories.add("Hand Pump Repair")
        categories.add("Light Repair")
        categories.add("Drainage Cleaning")
        categories.add("Washroom Cleaning")
        categories.add("Waste Management")
        categories.add("Others")
        categories.add("Instructions")
        val dataAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner?.setAdapter(dataAdapter)
    }

    private fun addgrivence() {
        val saveCurrentTime: String
        val saveCurrentDate: String
        val calForDate = Calendar.getInstance()
        val currentDate = SimpleDateFormat("MMM dd, yyyy")
        saveCurrentDate = currentDate.format(calForDate.time)
        val currentTime = SimpleDateFormat("HH:mm:ss a")
        saveCurrentTime = currentTime.format(calForDate.time)
        val gid = saveCurrentDate + saveCurrentTime
        val cartListRef: DatabaseReference =


            FirebaseDatabase.getInstance().getReference().child("Data")
        val grivMap = HashMap<String, Any>()
        grivMap["fid"] = gid
        grivMap["fullname"] = fullname?.getText().toString()
        grivMap["address"] = address?.getText().toString()
        grivMap["ward"] = wardnumber?.getText().toString()
        grivMap["phonenumber"] = phonenumber?.getText().toString()
        grivMap["grivence"] = grivence?.getText().toString()
        grivMap["date"] = saveCurrentDate
        grivMap["time"] = saveCurrentTime
        grivMap["category"] = spinner?.getSelectedItem().toString()
        cartListRef.child("Grivence Data")
            .child(gid)
            .updateChildren(grivMap)
            .addOnCompleteListener(object : OnCompleteListener<Void?> {
                override fun onComplete(p0: Task<Void?>) {
                    if (p0.isSuccessful) {
                        Toast.makeText(
                            this@GrievenceRegActivity1,
                            "Complaint is added",
                            Toast.LENGTH_LONG
                        ).show()
                        val intent =
                            Intent(this@GrievenceRegActivity1, GrievenceActivity1::class.java)
                        startActivity(intent)
                        finish()
                    }
                    TODO("Not yet implemented")
                }


            })
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val item: String = parent.getItemAtPosition(position).toString()
        Toast.makeText(parent.getContext(), "Selected: $item", Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
    override fun onBackPressed() {
        super.onBackPressed()
        val i = Intent(this@GrievenceRegActivity1, GrievenceActivity1::class.java)
        startActivity(i)
        finish()
    }
}