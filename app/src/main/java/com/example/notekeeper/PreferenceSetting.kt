package com.example.notekeeper

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notekeeper.ui.course.CourseFragment
import com.example.notekeeper.ui.note.NotesFragment


/**
 * A simple [Fragment] subclass.
 * Use the [PreferenceSetting.newInstance] factory method to
 * create an instance of this fragment.
 */
class PreferenceSetting : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_preference_setting, container, false)
    }




}