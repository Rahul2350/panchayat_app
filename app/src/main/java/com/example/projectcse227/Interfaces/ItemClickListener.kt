package com.example.projectcse227.Interfaces

import android.view.View

interface ItemClickListener {
    fun onClick(view: View?, position: Int, isLongClick: Boolean)
}