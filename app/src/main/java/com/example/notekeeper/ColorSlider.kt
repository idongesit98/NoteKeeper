package com.example.notekeeper

import android.content.Context
import android.util.AttributeSet
import android.widget.SeekBar



class ColorSlider @JvmOverloads constructor(context: Context,
                                            attrs: AttributeSet? = null,
                                            defStyleAttr: Int = R.attr.seekBarStyle,
                                            defStyleRes: Int = 0)
    : SeekBar(context, attrs, defStyleAttr, defStyleRes)
