package zerobranch.example

import android.graphics.Bitmap
import android.graphics.BlurMaskFilter
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.style.DynamicDrawableSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_main.*
import zerobranch.lightspanner.DimensionType
import zerobranch.lightspanner.SpanParams
import zerobranch.lightspanner.Spanner
import zerobranch.lightspanner.Spans

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        example1()
        example2()
        example3()
        example4()

        clickable()
        url()
        alignNormal()
        alignOpposite()
        alignCenter()
        leadingMargin()
        linesLeadingMargin()
        lineHeight()
        tabStop()
        bullet()
        subscript()
        superscript()
        textAppearance()
        textSize()
        lineBackground()
        relativeSize()
        foregroundColor()
        backgroundColor()
        drawableMargin()
        iconMargin()
        image()
        scaleX()
        strikethrough()
        underline()
        normal()
        bold()
        italic()
        boldItalic()
        blurMaskFilter()
        quote()
        typeface()
        typeface2()
        typeface3()
    }

    private fun example1() {
        Spanner(ex_1)
            .appendln("Example 1", Spans.bold())
            .newLine()
            .appendln("About the moon", Spans.bold())
            .append("The")
            .append(" Moon ", Spans.boldItalic(), Spans.foregroundColor(ContextCompat.getColor(this, R.color.green)))
            .append("is an astronomical body orbiting Earth as its only natural satellite.")
            .all(Spans.textSize(12f, DimensionType.SP))
            .install()
    }

    private fun example2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Spanner(ex_2)
                .appendln("SmallCaps example", Spans.bold())
                .newLine()
                .append("The ")
                .append("Moon", Spans.underline(), Spans.italic(), Spans.backgroundColor(Color.DKGRAY), Spans.foregroundColor(Color.WHITE))
                .append(" is an astronomical body orbiting Earth as its only natural satellite. ")
                .all(Spans.textSize(13f, DimensionType.SP))
                .toSmallCaps()
                .install()
        }
    }

    private fun example3() {
        Spanner(ex_3)
            .custom(
                "Example with a custom positions\n\nThe Moon is an astronomical body orbiting Earth as its only natural satellite.",
                SpanParams(0, 31, Spans.bold()),
                SpanParams(37, 41, Spans.boldItalic()),
                SpanParams(37, 41, Spans.underline()),
                SpanParams(37, 41, Spans.foregroundColor(Color.RED))
            )
    }

    private fun example4() {
        Spanner(ex_4)
            .appendln("Custom spans example", StyleSpan(Typeface.BOLD))
            .newLine()
            .append("The ")
            .append("Moon", UnderlineSpan(), StyleSpan(Typeface.ITALIC))
            .append(" is an astronomical body orbiting Earth as its only natural satellite. ")
            .install()
    }

    private fun clickable() {
        Spanner(text_view_1)
            .appendln("clickable", Spans.bold())
            .newLine()
            .append(
                "Clickable text",
                Spans.clickable(
                    text_view_1,
                    { Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show() },
                    { isPressed, textPaint ->
                        if (isPressed) {
                            textPaint.color = Color.RED
                            textPaint.isUnderlineText = true
                        } else {
                            textPaint.isUnderlineText = false
                        }
                    }
                )
            )
            .install()
    }

    private fun url() {
        Spanner(text_view_101)
            .appendln("url", Spans.bold())
            .newLine()
            .append(
                "Google",
                Spans.url("https://google.com", text_view_101)
            )
            .install()
    }

    private fun alignNormal() {
        Spanner(text_view_2)
            .appendln("alignNormal", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite. It is the fifth-largest satellite in the Solar System, and by far the largest among planetary satellites relative to the size of the planet that it orbits (its primary).",
                Spans.alignNormal()
            )
            .install()
    }

    private fun alignOpposite() {
        Spanner(text_view_3)
            .appendln("alignOpposite", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite. It is the fifth-largest satellite in the Solar System, and by far the largest among planetary satellites relative to the size of the planet that it orbits (its primary).",
                Spans.alignOpposite()
            )
            .install()
    }

    private fun alignCenter() {
        Spanner(text_view_4)
            .appendln("alignCenter", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite. It is the fifth-largest satellite in the Solar System, and by far the largest among planetary satellites relative to the size of the planet that it orbits (its primary).",
                Spans.alignCenter()
            )
            .install()
    }

    private fun leadingMargin() {
        Spanner(text_view_5)
            .appendln("leadingMargin", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite. It is the fifth-largest satellite in the Solar System, and by far the largest among planetary satellites relative to the size of the planet that it orbits (its primary).",
                Spans.leadingMargin(40F, 20F, DimensionType.DP)
            )
            .install()
    }

    private fun linesLeadingMargin() {
        Spanner(text_view_6)
            .appendln("linesLeadingMargin", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite. It is the fifth-largest satellite in the Solar System, and by far the largest among planetary satellites relative to the size of the planet that it orbits (its primary).",
                Spans.linesLeadingMargin(30F, 3, DimensionType.DP)
            )
            .install()
    }

    private fun lineHeight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Spanner(text_view_7)
                .appendln("lineHeight", Spans.bold())
                .newLine()
                .append(
                    "The Moon is an astronomical body orbiting Earth as its only natural satellite. It is the fifth-largest satellite in the Solar System, and by far the largest among planetary satellites relative to the size of the planet that it orbits (its primary).",
                    Spans.lineHeight(27F, DimensionType.SP)
                )
                .install()
        }
    }

    private fun tabStop() {
        Spanner(text_view_08)
            .appendln("tabStop", Spans.bold())
            .newLine()
            .append(
                "Number\t25\nWeight\t250.12\nTotal\t1039",
                Spans.tabStop(150F, DimensionType.DP)
            )
            .install()
    }

    private fun bullet() {
        Spanner(text_view_8)
            .appendln("bullet", Spans.bold())
            .newLine()
            .appendln(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.bullet(24f, Color.RED, DimensionType.DP)
            )
            .appendln(
                "It is the fifth-largest satellite in the Solar System, and by far the largest among planetary satellites relative to the size of the planet that it orbits (its primary).",
                Spans.bullet(24f, Color.RED, DimensionType.DP)
            )
            .install()
    }

    private fun subscript() {
        Spanner(text_view_9)
            .appendln("subscript", Spans.bold())
            .newLine()
            .append("log")
            .append("2", Spans.subscript(), Spans.textSize(12F, DimensionType.SP))
            .install()
    }

    private fun superscript() {
        Spanner(text_view_10)
            .appendln("superscript", Spans.bold())
            .newLine()
            .append("x")
            .append("2", Spans.superscript(), Spans.textSize(12F, DimensionType.SP))
            .install()
    }

    private fun textAppearance() {
        Spanner(text_view_11)
            .appendln("textAppearance", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.textAppearance(this, android.R.style.TextAppearance_Medium)
            )
            .install()
    }

    private fun textSize() {
        Spanner(text_view_12)
            .appendln("textSize", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.textSize(13F, DimensionType.SP)
            )
            .install()
    }

    private fun lineBackground() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Spanner(text_view_13)
                .appendln("lineBackground", Spans.bold())
                .newLine()
                .append(
                    "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                    Spans.lineBackground(Color.GREEN)
                )
                .install()
        }
    }

    private fun relativeSize() {
        Spanner(text_view_14)
            .appendln("relativeSize", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.relativeSize(1.5f)
            )
            .install()
    }

    private fun foregroundColor() {
        Spanner(text_view_15)
            .appendln("foregroundColor", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.foregroundColor(Color.BLUE)
            )
            .install()
    }

    private fun backgroundColor() {
        Spanner(text_view_16)
            .appendln("backgroundColor", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.backgroundColor(Color.LTGRAY)
            )
            .install()
    }

    private fun drawableMargin() {
        Spanner(text_view_17)
            .appendln("drawableMargin", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.drawableMargin(ContextCompat.getDrawable(this, R.drawable.ic_archive)!!, 16F, DimensionType.DP)
            )
            .install()
    }

    private fun iconMargin() {
        val bitmap = Bitmap.createBitmap(20, 20, Bitmap.Config.ARGB_8888)
        bitmap.eraseColor(Color.RED)

        Spanner(text_view_18)
            .appendln("iconMargin", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.iconMargin(bitmap, 6F, DimensionType.DP)
            )
            .install()
    }

    private fun image() {
        Spanner(text_view_19)
            .appendln("image", Spans.bold())
            .newLine()
            .append("The Moon is an astronomical body orbiting Earth as its only natural satellite")
            .append(".", Spans.image(this, R.drawable.ic_archive, DynamicDrawableSpan.ALIGN_BASELINE))
            .install()
    }

    private fun scaleX() {
        Spanner(text_view_20)
            .appendln("image", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.scaleX(2F)
            )
            .install()
    }

    private fun underline() {
        Spanner(text_view_21)
            .appendln("underline", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.underline()
            )
            .install()
    }

    private fun normal() {
        Spanner(text_view_22)
            .appendln("normal", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.normal()
            )
            .install()
    }

    private fun bold() {
        Spanner(text_view_23)
            .appendln("bold", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.bold()
            )
            .install()
    }

    private fun italic() {
        Spanner(text_view_24)
            .appendln("italic", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.italic()
            )
            .install()
    }

    private fun boldItalic() {
        Spanner(text_view_25)
            .appendln("boldItalic", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.boldItalic()
            )
            .install()
    }

    private fun blurMaskFilter() {
        Spanner(text_view_26)
            .appendln("blurMaskFilter", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.blurMaskFilter(2F, BlurMaskFilter.Blur.NORMAL, DimensionType.DP)
            )
            .install()
    }

    private fun quote() {
        Spanner(text_view_27)
            .appendln("blurMaskFilter", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite. It is the fifth-largest satellite in the Solar System, and by far the largest among planetary satellites relative to the size of the planet that it orbits (its primary).",
                Spans.quote(Color.BLUE)
            )
            .install()
    }

    private fun strikethrough() {
        Spanner(text_view_28)
            .appendln("strikethrough", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.strikethrough()
            )
            .install()
    }

    private fun typeface() {
        Spanner(text_view_29)
            .appendln("typeface", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.typeface("monospace")
            )
            .install()
    }

    private fun typeface2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Spanner(text_view_30)
                .appendln("typeface2", Spans.bold())
                .newLine()
                .append(
                    "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                    Spans.typefaceStandard(Typeface.DEFAULT_BOLD)
                )
                .install()
        }
    }

    private fun typeface3() {
        Spanner(text_view_31)
            .appendln("typeface3", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.typeface(ResourcesCompat.getFont(this, R.font.roboto_medium)!!)
            )
            .install()
    }
}
