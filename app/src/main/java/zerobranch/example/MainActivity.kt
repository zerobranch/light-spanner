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
        Spanner()
            .appendln("Example 1", Spans.bold())
            .newLine()
            .appendln("About the moon", Spans.bold())
            .append("The")
            .append(" Moon ", Spans.boldItalic(), Spans.foregroundColor(this, R.color.green))
            .append("is an astronomical body orbiting Earth as its only natural satellite.")
            .all(Spans.textSize(12f, DimensionType.SP))
            .installTo(ex_1)
    }

    private fun example2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Spanner()
                .appendln("SmallCaps example", Spans.bold())
                .newLine()
                .append("The ")
                .append("Moon", Spans.underline(), Spans.italic(), Spans.backgroundColor(Color.DKGRAY), Spans.foregroundColor(Color.WHITE))
                .append(" is an astronomical body orbiting Earth as its only natural satellite. ")
                .all(Spans.textSize(13f, DimensionType.SP))
                .toSmallCaps(ex_2)
                .installTo(ex_2)
        }
    }

    private fun example3() {
        Spanner()
            .appendln("Example with a custom positions", Spans.bold())
            .newLine()
            .set(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite.",
                SpanParams(0, 31, Spans.bold()),
                SpanParams(37, 41, Spans.boldItalic(), Spans.underline(), Spans.foregroundColor(Color.RED))
            )
            .installTo(ex_3)
    }

    private fun example4() {
        ex_4.text = Spanner()
            .appendln("Custom spans example", StyleSpan(Typeface.BOLD))
            .newLine()
            .append("The ")
            .append("Moon", UnderlineSpan(), StyleSpan(Typeface.ITALIC))
            .append(" is an astronomical body orbiting Earth as its only natural satellite. ")
            .get()
    }

    private fun clickable() {
        Spanner()
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
            .installTo(text_view_1)
    }

    private fun url() {
        Spanner()
            .appendln("url", Spans.bold())
            .newLine()
            .append(
                "Google",
                Spans.url("https://google.com", text_view_101)
            )
            .installTo(text_view_101)
    }

    private fun alignNormal() {
        Spanner()
            .appendln("alignNormal", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite. It is the fifth-largest satellite in the Solar System, and by far the largest among planetary satellites relative to the size of the planet that it orbits (its primary).",
                Spans.alignNormal()
            )
            .installTo(text_view_2)
    }

    private fun alignOpposite() {
        Spanner()
            .appendln("alignOpposite", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite. It is the fifth-largest satellite in the Solar System, and by far the largest among planetary satellites relative to the size of the planet that it orbits (its primary).",
                Spans.alignOpposite()
            )
            .installTo(text_view_3)
    }

    private fun alignCenter() {
        Spanner()
            .appendln("alignCenter", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite. It is the fifth-largest satellite in the Solar System, and by far the largest among planetary satellites relative to the size of the planet that it orbits (its primary).",
                Spans.alignCenter()
            )
            .installTo(text_view_4)
    }

    private fun leadingMargin() {
        Spanner()
            .appendln("leadingMargin", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite. It is the fifth-largest satellite in the Solar System, and by far the largest among planetary satellites relative to the size of the planet that it orbits (its primary).",
                Spans.leadingMargin(40F, 20F, DimensionType.DP)
            )
            .installTo(text_view_5)
    }

    private fun linesLeadingMargin() {
        Spanner()
            .appendln("linesLeadingMargin", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite. It is the fifth-largest satellite in the Solar System, and by far the largest among planetary satellites relative to the size of the planet that it orbits (its primary).",
                Spans.linesLeadingMargin(30F, 3, DimensionType.DP)
            )
            .installTo(text_view_6)
    }

    private fun lineHeight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Spanner()
                .appendln("lineHeight", Spans.bold())
                .newLine()
                .append(
                    "The Moon is an astronomical body orbiting Earth as its only natural satellite. It is the fifth-largest satellite in the Solar System, and by far the largest among planetary satellites relative to the size of the planet that it orbits (its primary).",
                    Spans.lineHeight(27F, DimensionType.SP)
                )
                .installTo(text_view_7)
        }
    }

    private fun tabStop() {
        Spanner()
            .appendln("tabStop", Spans.bold())
            .newLine()
            .append(
                "Number\t25\nWeight\t250.12\nTotal\t1039",
                Spans.tabStop(150F, DimensionType.DP)
            )
            .installTo(text_view_08)
    }

    private fun bullet() {
        Spanner()
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
            .installTo(text_view_8)
    }

    private fun subscript() {
        Spanner()
            .appendln("subscript", Spans.bold())
            .newLine()
            .append("log")
            .append("2", Spans.subscript(), Spans.textSize(12F, DimensionType.SP))
            .installTo(text_view_9)
    }

    private fun superscript() {
        Spanner()
            .appendln("superscript", Spans.bold())
            .newLine()
            .append("x")
            .append("2", Spans.superscript(), Spans.textSize(12F, DimensionType.SP))
            .installTo(text_view_10)
    }

    private fun textAppearance() {
        Spanner()
            .appendln("textAppearance", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.textAppearance(this, android.R.style.TextAppearance_Medium)
            )
            .installTo(text_view_11)
    }

    private fun textSize() {
        Spanner()
            .appendln("textSize", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.textSize(13F, DimensionType.SP)
            )
            .installTo(text_view_12)
    }

    private fun lineBackground() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Spanner()
                .appendln("lineBackground", Spans.bold())
                .newLine()
                .append(
                    "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                    Spans.lineBackground(Color.GREEN)
                )
                .installTo(text_view_13)
        }
    }

    private fun relativeSize() {
        Spanner()
            .appendln("relativeSize", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.relativeSize(1.5f)
            )
            .installTo(text_view_14)
    }

    private fun foregroundColor() {
        Spanner()
            .appendln("foregroundColor", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.foregroundColor(Color.BLUE)
            )
            .installTo(text_view_15)
    }

    private fun backgroundColor() {
        Spanner()
            .appendln("backgroundColor", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.backgroundColor(Color.LTGRAY)
            )
            .installTo(text_view_16)
    }

    private fun drawableMargin() {
        Spanner()
            .appendln("drawableMargin", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.drawableMargin(this, R.drawable.ic_archive, 16F, DimensionType.DP)
            )
            .installTo(text_view_17)
    }

    private fun iconMargin() {
        val bitmap = Bitmap.createBitmap(20, 20, Bitmap.Config.ARGB_8888)
        bitmap.eraseColor(Color.RED)

        Spanner()
            .appendln("iconMargin", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.iconMargin(bitmap, 6F, DimensionType.DP)
            )
            .installTo(text_view_18)
    }

    private fun image() {
        Spanner()
            .appendln("image", Spans.bold())
            .newLine()
            .append("The Moon is an astronomical body orbiting Earth as its only natural satellite")
            .append(".", Spans.image(this, R.drawable.ic_archive, DynamicDrawableSpan.ALIGN_BASELINE))
            .installTo(text_view_19)
    }

    private fun scaleX() {
        Spanner()
            .appendln("scaleX", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.scaleX(2F)
            )
            .installTo(text_view_20)
    }

    private fun underline() {
        Spanner()
            .appendln("underline", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.underline()
            )
            .installTo(text_view_21)
    }

    private fun normal() {
        Spanner()
            .appendln("normal", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.normal()
            )
            .installTo(text_view_22)
    }

    private fun bold() {
        Spanner()
            .appendln("bold", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.bold()
            )
            .installTo(text_view_23)
    }

    private fun italic() {
        Spanner()
            .appendln("italic", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.italic()
            )
            .installTo(text_view_24)
    }

    private fun boldItalic() {
        Spanner()
            .appendln("boldItalic", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.boldItalic()
            )
            .installTo(text_view_25)
    }

    private fun blurMaskFilter() {
        Spanner()
            .appendln("blurMaskFilter", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.blurMaskFilter(2F, BlurMaskFilter.Blur.NORMAL, DimensionType.DP)
            )
            .installTo(text_view_26)
    }

    private fun quote() {
        Spanner()
            .appendln("quote", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite. It is the fifth-largest satellite in the Solar System, and by far the largest among planetary satellites relative to the size of the planet that it orbits (its primary).",
                Spans.quote(Color.BLUE)
            )
            .installTo(text_view_27)
    }

    private fun strikethrough() {
        Spanner()
            .appendln("strikethrough", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.strikethrough()
            )
            .installTo(text_view_28)
    }

    private fun typeface() {
        Spanner()
            .appendln("typeface (monospace)", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.typeface("monospace")
            )
            .installTo(text_view_29)
    }

    private fun typeface2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Spanner()
                .appendln("typeface (default_bold)", Spans.bold())
                .newLine()
                .append(
                    "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                    Spans.typeface(Typeface.DEFAULT_BOLD)
                )
                .installTo(text_view_30)
        }
    }

    private fun typeface3() {
        Spanner()
            .appendln("font", Spans.bold())
            .newLine()
            .append(
                "The Moon is an astronomical body orbiting Earth as its only natural satellite",
                Spans.font(this, R.font.roboto_medium)
            )
            .installTo(text_view_31)
    }
}
