package com.example.notes.ui.create

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notes.R
import java.lang.RuntimeException

class CreateNoteFragment : Fragment() {

    private var listener: CreateTaskFragment.OnFragmentInteractorListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CreateTaskFragment.OnFragmentInteractorListener){
            listener = context
        }else{
            throw RuntimeException("$context must implement OnFragmentInteractorListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractorListener{
        fun onFragmentInteraction()
    }

    companion object {

        fun newInstance() = CreateNoteFragment()
    }
}