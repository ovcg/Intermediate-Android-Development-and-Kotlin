package com.example.notes.ui.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notes.R
import com.example.notes.ui.navigation.NavigationActivity
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        intent.getStringExtra(NavigationActivity.fragmentValueKey).run {
            textView.text = if (this == NavigationActivity.fragmentValueTask){
                "this is a task"
            }
            else if(this == NavigationActivity.fragmentValueNote){
                "this is a note"
            }else{
                "something went wrong"
            }
        }
    }
}
