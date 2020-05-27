package zerobranch.lightspanner.custom

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView

class SimpleClickableSpan(
    private val textView: TextView,
    private val clickListener: (() -> Unit),
    private val touchListener: ((Boolean, TextPaint) -> Unit)?,
    private val isUnderlineText: Boolean
) : ClickableSpan() {
    override fun onClick(textView: View) {
        clickListener.invoke()
    }

    override fun updateDrawState(textPaint: TextPaint) {
        super.updateDrawState(textPaint)
        textPaint.isUnderlineText = isUnderlineText
        touchListener?.invoke(textView.isPressed, textPaint)
        textView.invalidate()
    }
}