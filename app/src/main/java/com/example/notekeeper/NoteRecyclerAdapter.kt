package com.example.notekeeper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRecyclerAdapter(private val context: Context, private val notes: List<NoteInfo>):
    RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_note_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val note = notes[position]
        holder.textCourse?.text = note.course?.title
        holder.textTitle?.text = note.title
        holder.notePosition = position
        holder.color.setBackgroundColor(note.color)
    }

    override fun getItemCount() = notes.size
// inner allows the class below to access the properties of the class above
   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textCourse: TextView? = itemView.findViewById(R.id.textCourse)
        val textTitle: TextView? = itemView.findViewById(R.id.textTitle)
        var notePosition = 0
        var color: View = itemView.findViewById(R.id.noteColor)
        init {
           itemView?.setOnClickListener{
               val intent = Intent(context, MainActivity::class.java)
               intent.putExtra(NOTE_POSITION, notePosition)
               context.startActivity(intent)
           }
       }
    }


}