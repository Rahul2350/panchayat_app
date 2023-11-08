package com.example.projectcse227

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class CerificateActivity1 : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    private var mRef: DatabaseReference? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var mtoolbar: Toolbar? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cerificate)
        mtoolbar = findViewById<View>(R.id.certitoolbar) as Toolbar?
        mtoolbar!!.title = "Certificate"
        mtoolbar!!.setNavigationIcon(R.drawable.ic_arrow_back)
        mtoolbar!!.setNavigationOnClickListener {
            startActivity(Intent(this@CerificateActivity1, DashboardActivity1::class.java))
            finish()
        }
        mRecyclerView = findViewById<RecyclerView>(R.id.rv_certificate_data)
       mRecyclerView.layoutManager= LinearLayoutManager(getApplicationContext())

        mRef = FirebaseDatabase.getInstance().getReference().child("Cerificate")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val i = Intent(this@CerificateActivity1, DashboardActivity1::class.java)
        startActivity(i)
        finish()
    }

//    override fun onStart() {
//        super.onStart()
//        val options: FirebaseRecyclerOptions<CertificateModel> = Builder<CertificateModel>()
//            .setQuery(mRef, CertificateModel::class.java)
//            .build()
//        val adapter: FirebaseRecyclerAdapter<CertificateModel, CertificateAdapter> =
//            object : FirebaseRecyclerAdapter<CertificateModel?, CertificateAdapter?>(options) {
//                protected fun onBindViewHolder(
//                    holder: CertificateAdapter,
//                    i: Int,
//                    model: CertificateModel
//                ) {
//                    holder.mCertificateName.setText(model.getCertificate_name())
//                    holder.mIssueby.setText(model.getIssued_by())
//                    holder.mCommitee.setText(model.getCommitee())
//                    //                        holder.mCertificateUrl.setText(model.getCertificate_url());
//                    holder.itemView.setOnClickListener(View.OnClickListener {
//                        val intent = Intent(getApplicationContext(), CerActivity::class.java)
//                        intent.putExtra("certificate_url", model.getCertificate_url())
//                        startActivity(intent)
//                    })
//                }
//
//                fun onCreateViewHolder(
//                    parent: ViewGroup,
//                    viewType: Int
//                ): CertificateAdapter {
//                    val view: View = LayoutInflater.from(parent.context)
//                        .inflate(R.layout.certificate_row, parent, false)
//                    return CertificateAdapter(view)
//                }
//            }
//        mRecyclerView?.setAdapter(adapter)
//        adapter.startListening()
//    }
}