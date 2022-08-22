package com.example.notekeeper.ui.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notekeeper.itemActivityViewModel

class CourseViewModel : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    init {
        gettext()
    }

private fun gettext(){
    _text.value =  "Our testing text"
}

}