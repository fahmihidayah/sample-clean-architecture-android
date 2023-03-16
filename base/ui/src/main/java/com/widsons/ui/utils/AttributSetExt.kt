package com.widsons.ui.utils

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.lang.Exception

/**
 * Created on : November/20/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : SultanQaboos
 */
fun getTypedArray(context: Context?, attrs: AttributeSet?, resId : IntArray, typedArrayResult : (TypedArray) -> Unit) {
    val typedArray = context?.obtainStyledAttributes(attrs, resId)
    typedArray?.let { typedArrayIt ->
        try {
            typedArrayResult(typedArrayIt)
        }
        catch (ex : Exception) {

        }
        finally {
            typedArrayIt.recycle()
        }
    }
}

fun Int.setString(typedArray: TypedArray, textView: TextView) {
    getString(typedArray)?.let {
        textView.setText(it)
    }
}

fun Int.setTextColor(typedArray: TypedArray, textView: TextView) {
    if(typedArray.hasValue(this)) {
        val color = typedArray.getResourceId(this, -1)
        if(color != -1) {
            textView.setTextColor(ContextCompat.getColor(textView.context, color))
        }
    }
}

fun Int.setTextSize(typedArray: TypedArray, textView: TextView) {
    val textSizeResource= typedArray.getResourceId(this, -1)
    if(textSizeResource != -1) {
        textView.setTextSizeResource(textSizeResource)
    }
}

fun Int.setImage(typedArray: TypedArray, imageView: ImageView){
    getResource(typedArray)?.let {
        if(it != -1){
            imageView.setImageResource(it)
        }
    }
}

fun Int.setVisibilityGone(typedArray: TypedArray, view : View) {
    getBoolean(typedArray, true).let {
        if(it) {
            view.visibility = View.VISIBLE
        }
        else {
            view.visibility = View.GONE
        }
    }
}

fun Int.getBoolean(typedArray: TypedArray, default : Boolean = true) = typedArray.getBoolean(this, default)

fun Int.getInt(typedArray: TypedArray, default : Int = 0) = typedArray.getInt(this, default)
fun Int.getFloat(typedArray: TypedArray, default : Float = 0.5f) = typedArray.getFloat(this, default)


fun Int.getColor(typedArray: TypedArray, default : Int = Color.BLACK) = typedArray.getColor(this, default)

fun Int.getString(typedArray: TypedArray) = typedArray.getString(this)

fun Int.getResource(typedArray: TypedArray) = typedArray.getResourceId(this, -1)

