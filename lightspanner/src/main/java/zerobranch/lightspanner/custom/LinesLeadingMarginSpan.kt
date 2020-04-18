package zerobranch.lightspanner.custom

import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.style.LeadingMarginSpan

open class LinesLeadingMarginSpan(
    private var margin: Int,
    private var lines: Int
) : LeadingMarginSpan.LeadingMarginSpan2 {

    override fun getLeadingMargin(first: Boolean) = margin.takeIf { first } ?: 0

    override fun drawLeadingMargin(
        c: Canvas, p: Paint, x: Int, dir: Int,
        top: Int, baseline: Int, bottom: Int, text: CharSequence,
        start: Int, end: Int, first: Boolean, layout: Layout
    ) {}

    override fun getLeadingMarginLineCount() = lines
}