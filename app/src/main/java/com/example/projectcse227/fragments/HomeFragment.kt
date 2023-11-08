package com.example.projectcse227.fragments

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.projectcse227.CerificateActivity1
import com.example.projectcse227.ETaxActivity1
import com.example.projectcse227.GrievenceActivity1
import com.example.projectcse227.MonthlyMeetingActivity1
import com.example.projectcse227.R
import com.example.projectcse227.ReportActivity1
import com.example.projectcse227.YojanaActivity1


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    var mDialog: Dialog? = null
    var grievence: CardView? = null
    var certificate: CardView? = null
    var yojana: CardView? = null
    lateinit var aam_sabha: CardView
    var monthly_meeting: CardView? = null
    var e_tax: CardView? = null
    var report: CardView? = null
    lateinit var root: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_home, container, false)
        mDialog = Dialog(requireActivity())
        aam_sabha = root.findViewById<CardView>(R.id.aam_sabha_card)
        aam_sabha.setOnClickListener(View.OnClickListener { ShowPopUp() })
        grievence = root.findViewById<View>(R.id.grievence_card) as CardView
        grievence!!.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    activity,
                    GrievenceActivity1::class.java
                )
            )
        })
        certificate = root.findViewById<View>(R.id.certificate_card) as CardView
        certificate!!.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    activity,
                    CerificateActivity1::class.java
                )
            )
        })
        yojana = root.findViewById<View>(R.id.yojana_card) as CardView
        yojana!!.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    activity,
                    YojanaActivity1::class.java
                )
            )
        })
        monthly_meeting = root!!.findViewById<View>(R.id.monthly_meeting) as CardView
        monthly_meeting!!.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    activity,
                    MonthlyMeetingActivity1::class.java
                )
            )
        })
        e_tax = root!!.findViewById<View>(R.id.e_tax_card) as CardView
        e_tax!!.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    activity,
                    ETaxActivity1::class.java
                )
            )
        })
        report = root!!.findViewById<View>(R.id.report_card) as CardView
        report!!.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    activity,
                    ReportActivity1::class.java
                )
            )
        })
        return root
    }

    fun ShowPopUp() {
        mDialog?.setContentView(R.layout.gram_sabha_popup)
        mDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialog!!.show()
    }
}