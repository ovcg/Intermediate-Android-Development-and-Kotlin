package com.example.notes.ui.notes

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.notes.foundations.BaseFragment
import com.example.notes.models.Note
import kotlinx.android.synthetic.main.fragment_notes.view.*

class NotesListView @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(ctx, attrs, defStyleAttr) {

    private lateinit var mAdapter: NotesAdapter
    private lateinit var touchActionDelegate: BaseFragment.TouchActionDelegate
    private lateinit var dataActionDelegate: NotesListViewContract

    fun initView(
        touchActionDelegate: BaseFragment.TouchActionDelegate,
        daDelegate: NotesListViewContract
    ) {
        setDelegate(touchActionDelegate, daDelegate)
        setUpView()
    }

    private fun setDelegate(
        touchActionDelegate: BaseFragment.TouchActionDelegate,
        daDelegate: NotesListViewContract
    ) {
        this.touchActionDelegate = touchActionDelegate
        dataActionDelegate = daDelegate
    }

    private fun setUpView() {
        mAdapter = NotesAdapter(
            touchActionDelegate = touchActionDelegate,
            dataActionDelegate = dataActionDelegate
        )

        with(recyclerView) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = mAdapter
        }

    }

    fun updateList(list: MutableList<Note>) {
        mAdapter.updateList(list)
    }
}