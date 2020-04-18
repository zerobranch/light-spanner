package zerobranch.lightspanner.custom

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView

class SimpleClickableSpan(
    private val textView: TextView,
    private val clickListener: (() -> Unit),
    private val touchListener: ((Boolean, TextPaint) -> Unit)?
) : ClickableSpan() {
    override fun onClick(textView: View) {
        clickListener.invoke()
    }

    override fun updateDrawState(textPaint: TextPaint) {
        super.updateDrawState(textPaint)
        touchListener?.invoke(textView.isPressed, textPaint)
    }
}