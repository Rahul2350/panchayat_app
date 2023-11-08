package com.example.projectcse227

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectcse227.Adapters.GrivenceAdapter
import com.example.projectcse227.Interfaces.ItemClickListener
import com.example.projectcse227.Models.GrivenceModel
import com.example.projectcse227.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class GrievenceActivity1 : AppCompatActivity() {
    var mtoolbar: Toolbar? = null
    var mfab: FloatingActionButton? = null
    var recyclerView: RecyclerView? = null
    lateinit var grivenceList: MutableList<GrivenceModel>
    lateinit var grivenceAdapter: GrivenceAdapter
    lateinit var databaseReference: DatabaseReference

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grievence)

        mtoolbar = findViewById<View>(R.id.g1toolbar) as Toolbar?
        mtoolbar!!.title = "Grievance & Suggestions"
        mtoolbar!!.setNavigationIcon(R.drawable.ic_arrow_back)
        mtoolbar!!.setNavigationOnClickListener {
            startActivity(Intent(this@GrievenceActivity1, DashboardActivity1::class.java))
            finish()
        }

        mfab = findViewById<View>(R.id.fab) as FloatingActionButton?
        mfab?.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@GrievenceActivity1,
                    GrievenceRegActivity1::class.java
                )
            )
        })

        recyclerView = findViewById(R.id.rv_data)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        grivenceList = ArrayList()
        grivenceAdapter = GrivenceAdapter(grivenceList, object : ItemClickListener {
            override fun onClick(view: View?, position: Int, isLongClick: Boolean) {
                // Handle item click here if needed
            }
        })
        recyclerView?.adapter = grivenceAdapter
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Data").child("Grivence Data")

        // Retrieve data from Firebase and populate the list
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    grivenceList.clear() // Clear the list before adding data

                    for (snapshot in dataSnapshot.children) {
                        val grivence = snapshot.getValue(GrivenceModel::class.java)
                        if (grivence != null) {
                            grivenceList.add(grivence)
                        }
                    }

                    grivenceAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors
                Toast.makeText(this@GrievenceActivity1, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val i = Intent(this@GrievenceActivity1, DashboardActivity1::class.java)
        startActivity(i)
        finish()
    }
}
