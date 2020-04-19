package zerobranch.lightspanner

import android.os.Build
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan
import android.widget.TextView
import androidx.annotation.RequiresApi

class Spanner(private val textView: TextView) {
    private val spannableStringBuilder = SpannableStringBuilder()
    private val pendingActions = mutableListOf<() -> (Unit)>()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun customWithSmallCaps(text: String, vararg parameters: SpanParams) {
        toSmallCaps()
        custom(text, *parameters)
    }

    fun custom(
        text: String,
        vararg parameters: SpanParams
    ) {
        spannableStringBuilder.append(text)

        parameters.forEach { parameter ->
            setParameters(
                parameter.start,
                parameter.end,
                parameter.span
            )
        }

        install()
    }

    fun newLine(): Spanner {
        spannableStringBuilder.appendln()
        return this
    }

    fun all(vararg parameters: Any): Spanner {
        pendingActions.add {
            val start = 0
            val end = spannableStringBuilder.length

            setParameters(start, end, *parameters)
        }

        return this
    }

    fun appendln(text: String, vararg parameters: Any): Spanner {
        val start = spannableStringBuilder.length
        val end = start + text.length

        spannableStringBuilder.appendln(text)

        setParameters(start, end, *parameters)

        return this
    }

    fun append(text: String, vararg parameters: Any): Spanner {
        val start = spannableStringBuilder.length
        val end = start + text.length

        spannableStringBuilder.append(text)

        setParameters(start, end, *parameters)

        return this
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun toSmallCaps(): Spanner {
        pendingActions.add {
            val multiSpans = smallCaps(spannableStringBuilder.toString())

            multiSpans.forEach { multiSpan ->
                setParameters(multiSpan.start, multiSpan.end, multiSpan.span)
            }

            textView.fontFeatureSettings = "smcp"
        }
        return this
    }

    fun install() {
        pendingActions.forEach { it.invoke() }
        textView.text = spannableStringBuilder
    }

    private fun smallCaps(text: String): List<SpanParams> {
        val stringBuilder = StringBuilder()

        var startPos = -1
        var endPos = -1

        val multiSpans = mutableListOf<SpanParams>()

        text.forEachIndexed { index, char ->
            stringBuilder.append(char)

            if (!char.isLetter() && !char.isWhitespace() && startPos == -1) {
                startPos = index
            } else if ((char.isLetter() || char.isWhitespace()) && startPos != -1) {
                endPos = index
            }

            if (index == text.length - 1) {
                endPos = text.length
            }

            if (startPos != -1 && endPos != -1) {
                multiSpans.add(
                    SpanParams(
                        startPos,
                        endPos,
                        RelativeSizeSpan(0.8f)
                    )
                )
                startPos = -1
                endPos = -1
                stringBuilder.setLength(0)
            }
        }

        return multiSpans
    }

    private fun setParameters(start: Int, end: Int, vararg parameters: Any) {
        parameters.forEach { parameter ->
            spannableStringBuilder.setSpan(
                parameter,
                start,
                end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
}