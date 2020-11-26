package com.duranun.multicolortextview

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import com.github.duranun.multicolortextview.R
import java.util.regex.Matcher
import java.util.regex.Pattern

class MultiColorTextView : AppCompatTextView {

    private var coloredTextIsBold = DEFAULT_TEXT_BOLD_TYPE
    private var colorArrays: IntArray? = null
    private var colorStartTag: String = DEFAULT_START_TAG
    private var colorEndTag: String = DEFAULT_END_TAG
    private var originText: String = ""

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, deStyleAttr: Int) : super(
        context,
        attrs,
        deStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        originText = text.toString()
        Log.e("MultiTextView",originText)

        val a = context.obtainStyledAttributes(attrs, R.styleable.MultiColorTextView)
        coloredTextIsBold =
            a.getBoolean(R.styleable.MultiColorTextView_coloredTextIsBold, false)
        val colorListArray = a.getResourceId(R.styleable.MultiColorTextView_colorIntArray, 0)
        colorStartTag =
            a.getString(R.styleable.MultiColorTextView_startTag) ?: DEFAULT_START_TAG
        colorEndTag = a.getString(R.styleable.MultiColorTextView_endTag) ?: DEFAULT_END_TAG
        if (colorListArray != 0)
            colorArrays = resources.getIntArray(colorListArray)
        a.recycle()

        initView()
    }


    private fun initView() {

        val regexList = Regex("$colorStartTag(.+?)$colorEndTag")
        val matchers: MutableList<Matcher> = mutableListOf()
        val matches = regexList.findAll(originText)
        matches.forEach {
            matchers.add(Pattern.compile(it.groupValues[1]).matcher(originText))
        }
        val spannedText = originText.replace(Regex("<[^>]+>\\s +(?=<)|<[^>]+>"), "")
        val spannable = SpannableString(spannedText)
        for (i in 0 until matchers.size) {
            if (matchers[i].find()) {
                val span = Pattern.compile(matchers[i].pattern().toString()).matcher(spannable)
                if (span.find())
                    colorArrays?.let { intIds ->
                        if (intIds.isNotEmpty()) {
                            spannable.setSpan(
                                ForegroundColorSpan(intIds[i]),
                                span.start(),
                                span.end(),
                                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                            if (coloredTextIsBold) spannable.setSpan(
                                StyleSpan(Typeface.BOLD),
                                span.start(),
                                span.end(),
                                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                        }
                    }
            }
        }
        setText(spannable, BufferType.SPANNABLE)
    }

    fun setColoredTextIsBold(isBold: Boolean) {
        this.coloredTextIsBold = isBold
        requestLayout()
        invalidate()
    }

    fun setColorsListArray(intArray: Int) {
        try {
            this.colorArrays = resources.getIntArray(intArray)
            initView()
       } catch (ex: Exception) {
            throw IllegalArgumentException("Resource not found")
        }
    }

    fun setStartTag(startTag: String) {
        this.colorStartTag = startTag
        initView()
    }

    fun setEndTag(endTag: String) {
        this.colorEndTag = endTag
        initView()
    }

    companion object {
        private const val DEFAULT_START_TAG = "<color>"
        private const val DEFAULT_END_TAG = "</color>"
        private const val DEFAULT_TEXT_BOLD_TYPE=false
    }
}