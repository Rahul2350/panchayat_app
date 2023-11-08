package com.example.projectcse227.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectcse227.Models.GrivenceModel
import com.example.projectcse227.R
import com.example.projectcse227.Interfaces.ItemClickListener

class GrivenceAdapter(
    private val grivenceList: List<GrivenceModel>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<GrivenceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grivence, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val grivence = grivenceList[position]

        holder.mCategory.text = "Category: " + grivence.category
        holder.mFullname.text = "Full Name: " + grivence.fullname
        holder.mWard.text = "Ward Number: " + grivence.ward
        holder.mDetails.text = "Details: " + grivence.grivence
        holder.mDate.text = "Date: " + grivence.date
        holder.mTime.text = "Time: " + grivence.time

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position, false)
        }
    }

    override fun getItemCount(): Int {
        return grivenceList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mCategory: TextView = itemView.findViewById(R.id.grivence_category_id)
        var mFullname: TextView = itemView.findViewById(R.id.grivence_fullname_id)
        var mWard: TextView = itemView.findViewById(R.id.grivence_ward_no)
        var mDetails: TextView = itemView.findViewById(R.id.grivence_details)
        var mDate: TextView = itemView.findViewById(R.id.grivence_date)
        var mTime: TextView = itemView.findViewById(R.id.grivence_time)
    }
}
