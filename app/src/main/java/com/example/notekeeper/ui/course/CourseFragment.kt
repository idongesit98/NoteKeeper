package com.example.notekeeper.ui.course

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notekeeper.R
import com.example.notekeeper.databinding.FragmentCoursesBinding


class CourseFragment : Fragment() {

    private var _binding:  FragmentCoursesBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(CourseViewModel::class.java)

        _binding =FragmentCoursesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCourse
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_setting ->{
            }
        }
        return true
    }

}