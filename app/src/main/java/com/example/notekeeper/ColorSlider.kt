package com.example.notekeeper

 import android.content.Context
 import android.graphics.Canvas
 import android.graphics.Color
 import android.graphics.Paint
 import android.graphics.drawable.Drawable
 import android.util.AttributeSet
 import android.util.TypedValue
 import android.widget.SeekBar
 import androidx.core.content.ContextCompat


 class ColorSlider @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.appcompat.R.attr.seekBarStyle,
    defStyleRes: Int = 0)
    :androidx.appcompat.widget.AppCompatSeekBar(context, attrs, defStyleAttr){

    private var colors: ArrayList<Int> = arrayListOf(Color.RED, Color.YELLOW, Color.BLUE)

   private val w = getPixelValueFromDp(16f) // Width of color swatch
    private val h = getPixelValueFromDp(16f) // Height of color swatch
   private val halfW = if(w >= 0) w / 2f else 1f
    private val halfH = if(h >= 0) h / 2f else 1f
    private val paint = Paint()
    private var noColorDrawable: Drawable? = null
    set(value) {
        w2 = value?.intrinsicWidth ?: 0
        h2 = value?.intrinsicHeight ?: 0
        halfW2 = if (w2 >= 0) w2 / 2 else 1
        halfH2 = if (h2 >= 0) h2 / 2 else 1
        value?.setBounds(-halfW2, -halfH2, halfW2, halfH2)
        field = value
    }
    var w2 = 0
    private var h2 = 0
    private var halfH2 = 1
    private var halfW2 = 1

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorSlider)
        try {
            colors = typedArray.getTextArray(R.styleable.ColorSlider_colors)
                .map {
                    Color.parseColor(it.toString())
                } as ArrayList<Int> /* = java.util.ArrayList<kotlin.Int> */
        } finally {
            typedArray.recycle()
        }

        colors.add(0, android.R.color.transparent)
        max = colors.size - 1
        progressBackgroundTintList = ContextCompat.getColorStateList(context,
        android.R.color.transparent)
        progressTintList = ContextCompat.getColorStateList(context,
        android.R.color.transparent)
        splitTrack = false
        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom + getPixelValueFromDp(10f).toInt())
        // The course was 16f had to modify it to 10f to suit my screen size
        thumb = context.getDrawable(R.drawable.ic_color_slider_thumb )
        noColorDrawable = context.getDrawable(R.drawable.ic_no_color)

        setOnSeekBarChangeListener(object: OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                listeners.forEach {
                    it(colors[progress])
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
      }

    var selectedColorValue: Int = android.R.color.transparent
    set(value){
        val index = colors.indexOf(value)
        if (index == 1){
            progress = 0
        }else{
            progress = index
        }
    }
    private var listeners: ArrayList<(Int) -> Unit> = arrayListOf()
            fun addListener(function:(Int) -> Unit){
                listeners.add(function)
            }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawTickMark(canvas)
    }

    private fun drawTickMark(canvas: Canvas?) {
        canvas?.let {
            val count = colors.size
            val saveCount = canvas.save()
            canvas.translate(paddingLeft.toFloat(), (height/2).toFloat() +  getPixelValueFromDp(10f))
            // The course was 16f had to modify it to 10f to suit my screen size
            if(count > 1){
// Calculating spacing in btw each square
                val spacing = (width - paddingLeft - paddingRight) / (count - 1).toFloat()
                for (i in 0 until count) {
                    if (i == 0) {
                        noColorDrawable?.draw(canvas)
                    } else {
                        paint.color = colors[i]
                        canvas.drawRect(
                            -halfW, -halfH, halfW, halfH, paint)
                    }
                    canvas.translate(spacing, 0f)
                }
                canvas.restoreToCount(saveCount)
            }
        }
    }

    private fun getPixelValueFromDp(value: Float): Float{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
        value, context.resources.displayMetrics)
    }
}

