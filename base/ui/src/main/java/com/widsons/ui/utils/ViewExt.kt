package com.widsons.ui.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.TypefaceSpan
import android.text.style.UnderlineSpan
import android.util.TypedValue
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DimenRes
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.widsons.ui.base.BaseActivity
import com.widsons.ui.base.BaseFragment


/**
 * Created on : November/26/2021
 * Author     : Muhammad Fahmi Hidayah
 * Company    : PiXilApps
 * Project    : SultanQaboos
 */
fun View.getActivity() : Activity? {
    var currentContext = context
    while(currentContext is ContextWrapper){
        if(currentContext is Activity) {
            return currentContext
        }
        currentContext = ((currentContext) as ContextWrapper).baseContext
    }
    return null
}

fun View.hideKeyboard() {
    getActivity()?.currentFocus?.let { view ->
        val imm = getActivity()?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun View.getAppCompatActivity() : AppCompatActivity? {
    if(getActivity() is AppCompatActivity) {
        return getActivity() as AppCompatActivity
    }
    return null
}

fun View.getBaseActivity() : BaseActivity? {
    val activity = getAppCompatActivity()
    return if(activity is BaseActivity) activity
    else null
}

fun View.getString(@StringRes res : Int) = resources.getString(res)

//fun View.findNavController() = getBaseActivity()?.findNavController()
//
//fun View.getNavHostFragment() = getBaseActivity()?.getNavHostFragment()

fun BaseFragment.getBaseActivity() : BaseActivity? {
    return view?.getBaseActivity()
}

fun View.toast(message: String) {
    getActivity()?.toast(message)
}

fun View.toast(@StringRes message: Int) {
    getActivity()?.toast(message)
}

fun Activity.toast(message : String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.toast(@StringRes message : Int) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(message : String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(message : Int) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun View.getRelativeLayoutParams() : RelativeLayout.LayoutParams {
    return this.layoutParams as RelativeLayout.LayoutParams
}

fun TextView.setTextSizeResource(@DimenRes res : Int) {
    setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(res))
}

fun TextView.setFontResource(@FontRes res : Int) {
    val typeFace = ResourcesCompat.getFont(context, res)
    setTypeface(typeFace)
}

fun TextView.setUnderLine() {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

fun TextView.removeUnderLine() {
    setPaintFlags(getPaintFlags() and Paint.UNDERLINE_TEXT_FLAG.inv())

}

fun TextView.setBoldText(targetText : String) {
    val startIndex = text.toString().indexOf(targetText)
    setBoldAt(startIndex, startIndex + targetText.length)
}

fun TextView.setTextColor(targetText: String, color : Int) {
    val startIndex = text.toString().indexOf(targetText)
    setTextColorAt(startIndex, startIndex + targetText.length, color)
}

fun TextView.setUnderlineText() {
    setText(
        SpannableString(text).apply {
            setSpan(UnderlineSpan(), 0, text.length, 0)
        }
    )
}


fun TextView.setTextFont(targetText : String, @FontRes fontId : Int) {
    val startIndex = text.toString().indexOf(targetText)
    setTextFontAt(startIndex, startIndex + targetText.length, fontId)

}

fun TextView.setTextFontAt(start : Int = Int.MIN_VALUE, end : Int = Int.MAX_VALUE, @FontRes fontId : Int) {
    val textResult = SpannableStringBuilder(text)
    val startText = if(start == Int.MIN_VALUE) 0 else start
    val endText = if(end == Int.MAX_VALUE) text.length else end
    ResourcesCompat.getFont(this.context, fontId)?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            textResult.setSpan(TypefaceSpan(it), startText, endText, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        setText(textResult)
    }

}

fun TextView.setTextColorAt(start : Int = Int.MIN_VALUE, end : Int = Int.MAX_VALUE, color : Int) {
    val textResult = SpannableStringBuilder(text)
    val startText = if(start == Int.MIN_VALUE) 0 else start
    val endText = if(end == Int.MAX_VALUE) text.length else end
    textResult.setSpan(ForegroundColorSpan(color), startText, endText, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    setText(textResult)
}

fun TextView.setBoldAt(start : Int = Int.MIN_VALUE, end : Int = Int.MAX_VALUE) {
    val textResult = SpannableStringBuilder(text)
    val startText = if(start == Int.MIN_VALUE) 0 else start
    val endText = if(end == Int.MAX_VALUE) text.length else end
    textResult.setSpan(StyleSpan(Typeface.BOLD), startText, endText, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    setText(textResult)

}

fun View.generateBitmap() : Bitmap {
    val bitmap : Bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas : Canvas = Canvas(bitmap)
    draw(canvas)
    return bitmap
}

fun View.generateBitmap(onFinishGeneratedListener : (Bitmap) -> Unit) {
    this.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            val bitmap : Bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas : Canvas = Canvas(bitmap)
            draw(canvas)
            onFinishGeneratedListener(bitmap)
            viewTreeObserver.removeOnGlobalLayoutListener(this)
        }
    })

}
