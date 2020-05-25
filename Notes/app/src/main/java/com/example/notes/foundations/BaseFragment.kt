package com.example.notes.foundations

import android.content.Context
import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {

    lateinit var touchActionDelegate: TouchActionDelegate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TouchActionDelegate){
            touchActionDelegate = context
        }
    }

    interface TouchActionDelegate{
        fun onAddButtonClicked(value: String)
    }
}