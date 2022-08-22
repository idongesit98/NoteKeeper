package com.example.notekeeper

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {
    private  var  notePosition = POSITION_NOT_SET
    private var isNewNote = false
    private var isCancelling = false
    private var noteColor: Int = Color.TRANSPARENT
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val toolBar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolBar)
        val spinner:Spinner = findViewById(R.id.spinnerCourses)

        val adapterCourse = ArrayAdapter(
            this,android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList())
        // Specify the layout to use when the list of choices appears
        adapterCourse.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        // Apply the adapter to the spinner
        spinner.adapter = adapterCourse

        notePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET) ?:
            intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET)

       if (notePosition != POSITION_NOT_SET )
            displayNote()

        else{
            DataManager.notes.add(NoteInfo())
           notePosition = DataManager.notes.lastIndex
       }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(NOTE_POSITION, notePosition)
    }

    private fun displayNote() {
      val note = DataManager.notes[notePosition]
        textNoteText.setText(note.text)
        textNoteTitle.setText(note.text)
        noteColor = note.color

        val coursePosition = DataManager.courses.values.indexOf(note.course )
        spinnerCourses.setSelection(coursePosition)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds item to the action bar if it is present
        val inflater: MenuInflater = menuInflater
            inflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Handle action bar items click here.The actionbar will automatically click on the home/up
        // button, so long as you specify a parent activity in Android Manifest.xml
        return when(item.itemId){
            R.id.action_setting -> true
            R.id.action_next ->{
               moveNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun moveNext():Boolean{
       ++notePosition
     displayNote()
       invalidateOptionsMenu()
        return true

   }
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (notePosition >= DataManager.notes.lastIndex){
            val menuItem =menu?.findItem(R.id.action_next)
            if (menuItem != null){
                menuItem.icon = getDrawable(R.drawable.ic_baseline_block_24)
                menuItem.isEnabled = false
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    private fun saveNote() {
        val note = DataManager.notes[notePosition]
        note.title = textNoteTitle.text.toString()
        note.text = textNoteText.text.toString()
        note.course = spinnerCourses.selectedItem as CourseInfo
        note.color = this.noteColor
    }
}