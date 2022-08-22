package com.example.notekeeper

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_note_list.*

// The COMMENTED OUT LINES SHOWS HOW TO IMPLEMENT A LIST VIEW AND POPULATE A SPINNER FROM AN ARRAY THE OTHER CODES ARE USED TO IMPLEMENT A RECYCLER VIEW

class NoteListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        val toolBar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolBar)
        //Respond to button taps

        val fab: View = findViewById(R.id.floatingActionButton)
        fab.setOnClickListener { view ->
            val activityIntent = Intent(this,MainActivity::class.java)
            startActivity(activityIntent)
        }

        listItems.layoutManager = LinearLayoutManager(this)
        listItems.adapter = NoteRecyclerAdapter(this, DataManager.notes)


      //  val listView: ListView = findViewById(R.id.userList)

//Populating a listView
        val adapter = ArrayAdapter(this,
        android.R.layout.simple_list_item_1,DataManager.notes)
       // listView.adapter = adapter
//
//        listView.setOnItemClickListener { parent, view, position, id ->
//            val activityIntent = Intent(this, MainActivity::class.java)
//            activityIntent.putExtra(NOTE_POSITION,position)
//            startActivity(activityIntent)
//        }
//
    }


    override fun onResume() {
        super.onResume()
//        ( userList.adapter as ArrayAdapter<NoteInfo>).notifyDataSetChanged()
        listItems.adapter?.notifyDataSetChanged()
    }
}