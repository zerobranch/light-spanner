package zerobranch.lightspanner

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BlurMaskFilter
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.text.Layout
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.widget.TextView
import androidx.annotation.*
import androidx.annotation.IntRange
import zerobranch.lightspanner.custom.SimpleClickableSpan
import zerobranch.lightspanner.custom.LinesLeadingMarginSpan
import java.util.*

object Spans {
    // Actions
    fun clickable(
        textView: TextView,
        clickListener: (() -> Unit),
        touchListener: ((Boolean, TextPaint) -> Unit)?,
        highlightColor: Int = Color.TRANSPARENT
    ): ClickableSpan {
        textView.movementMethod = LinkMovementMethod.getInstance()
        textView.highlightColor = highlightColor
        return SimpleClickableSpan(textView, clickListener, touchListener)
    }

    // Paragraph
    fun alignNormal() = AlignmentSpan.Standard(Layout.Alignment.ALIGN_NORMAL)

    fun alignOpposite() = AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE)

    fun alignCenter() = AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER)

    fun align(align: Layout.Alignment) = AlignmentSpan.Standard(align)

    fun leadingMargin(
        @FloatRange(from = 0.0) first: Float,
        @FloatRange(from = 0.0) rest: Float,
        dimensionType: DimensionType = DimensionType.PX
    ) = LeadingMarginSpan.Standard(dimensionType.toPx(first), dimensionType.toPx(rest))

    fun leadingMargin(
        @FloatRange(from = 0.0) every: Float,
        dimensionType: DimensionType = DimensionType.PX
    ) = LeadingMarginSpan.Standard(dimensionType.toPx(every))

    fun linesLeadingMargin(
        @FloatRange(from = 0.0) margin: Float,
        @IntRange(from = 0) lines: Int,
        dimensionType: DimensionType = DimensionType.PX
    ) = LinesLeadingMarginSpan(dimensionType.toPx(margin), lines)

    @RequiresApi(Build.VERSION_CODES.Q)
    fun lineHeight(
        @FloatRange(from = 0.0) height: Float,
        dimensionType: DimensionType = DimensionType.PX
    ) = LineHeightSpan.Standard(dimensionType.toPx(height))

    fun tabStop(
        @FloatRange(from = 0.0) offset: Float,
        dimensionType: DimensionType = DimensionType.PX
    ) = TabStopSpan.Standard(dimensionType.toPx(offset))

    fun bullet() = BulletSpan()

    fun bullet(
        @FloatRange(from = 0.0) gapWidth: Float,
        dimensionType: DimensionType = DimensionType.PX
    ) = BulletSpan(dimensionType.toPx(gapWidth))

    fun bullet(
        @FloatRange(from = 0.0) gapWidth: Float,
        @ColorInt color: Int,
        dimensionType: DimensionType = DimensionType.PX
    ) = BulletSpan(dimensionType.toPx(gapWidth), color)

    @RequiresApi(Build.VERSION_CODES.P)
    fun bullet(
        @FloatRange(from = 0.0) gapWidth: Float,
        @ColorInt color: Int,
        @FloatRange(from = 0.0) bulletRadius: Float,
        dimensionType: DimensionType = DimensionType.PX
    ) = BulletSpan(dimensionType.toPx(gapWidth), color, dimensionType.toPx(bulletRadius))

    fun subscript() = SubscriptSpan()

    fun superscript() = SuperscriptSpan()

    fun suggestion(
        locale: Locale,
        suggestions: Array<String>,
        flags: Int
    ) = SuggestionSpan(locale, suggestions, flags)

    fun suggestion(
        context: Context,
        suggestions: Array<String>,
        flags: Int
    ) = SuggestionSpan(context, suggestions, flags)

    fun suggestion(
        context: Context,
        locale: Locale,
        suggestions: Array<String>,
        flags: Int,
        notificationTargetClass: Class<*>
    ) = SuggestionSpan(context, locale, suggestions, flags, notificationTargetClass)

    fun textAppearance(
        context: Context,
        @StyleRes appearance: Int
    ) = TextAppearanceSpan(context, appearance)

    // Appearance
    @RequiresApi(Build.VERSION_CODES.Q)
    fun lineBackground(@ColorInt color: Int) = LineBackgroundSpan.Standard(color)

    fun textSizeSp(
        size: Float,
        dimensionType: DimensionType = DimensionType.PX
    ) = AbsoluteSizeSpan(dimensionType.toPx(size), false)

    fun relativeSize(@FloatRange(from = 0.0) proportion: Float) = RelativeSizeSpan(proportion)

    fun foregroundColor(@ColorInt color: Int) = ForegroundColorSpan(color)

    fun backgroundColor(@ColorInt color: Int) = BackgroundColorSpan(color)

    fun drawableMargin(
        drawable: Drawable,
        padding: Float = 0F,
        dimensionType: DimensionType = DimensionType.PX
    ) = DrawableMarginSpan(drawable, dimensionType.toPx(padding))

    fun iconMargin(
        bitmap: Bitmap,
        padding: Float = 0F,
        dimensionType: DimensionType = DimensionType.PX
    ) = IconMarginSpan(bitmap, dimensionType.toPx(padding))

    fun image(
        context: Context,
        @DrawableRes resourceId: Int,
        verticalAlignment: Int = DynamicDrawableSpan.ALIGN_BOTTOM
    ) = ImageSpan(context, resourceId, verticalAlignment)

    fun image(
        context: Context,
        uri: Uri,
        verticalAlignment: Int = DynamicDrawableSpan.ALIGN_BOTTOM
    ) = ImageSpan(context, uri, verticalAlignment)

    fun image(
        drawable: Drawable,
        source: String,
        verticalAlignment: Int = DynamicDrawableSpan.ALIGN_BOTTOM
    ) = ImageSpan(drawable, source, verticalAlignment)

    fun image(
        drawable: Drawable,
        verticalAlignment: Int = DynamicDrawableSpan.ALIGN_BOTTOM
    ) = ImageSpan(drawable, verticalAlignment)

    fun image(
        context: Context,
        bitmap: Bitmap,
        verticalAlignment: Int = DynamicDrawableSpan.ALIGN_BOTTOM
    ) = ImageSpan(context, bitmap, verticalAlignment)

    @Deprecated(
        "Deprecated", ReplaceWith(
            "image(context, bitmap, verticalAlignment)",
            "android.text.style.ImageSpan"
        )
    )
    fun image(
        bitmap: Bitmap,
        verticalAlignment: Int = DynamicDrawableSpan.ALIGN_BOTTOM
    ) = ImageSpan(bitmap, verticalAlignment)

    fun scaleX(@FloatRange(from = 0.0) proportion: Float) = ScaleXSpan(proportion)

    fun underline() = UnderlineSpan()

    fun normal() = StyleSpan(Typeface.NORMAL)

    fun bold() = StyleSpan(Typeface.BOLD)

    fun italic() = StyleSpan(Typeface.ITALIC)

    fun boldItalic() = StyleSpan(Typeface.BOLD_ITALIC)

    fun blurMaskFilter(
        @FloatRange(from = 0.0) radius: Float,
        blur: BlurMaskFilter.Blur,
        dimensionType: DimensionType = DimensionType.PX
    ) = MaskFilterSpan(BlurMaskFilter(dimensionType.toPx(radius).toFloat(), blur))

    fun quote() = QuoteSpan()

    fun quote(@ColorInt color: Int) = QuoteSpan(color)

    @RequiresApi(Build.VERSION_CODES.P)
    fun quote(
        @ColorInt color: Int,
        @FloatRange(from = 0.0) stripeWidth: Float,
        gapWidth: Float,
        dimensionType: DimensionType = DimensionType.PX
    ) = QuoteSpan(color, dimensionType.toPx(stripeWidth), dimensionType.toPx(gapWidth))

    fun strikethrough() = StrikethroughSpan()

    fun typeface(family: String?) = TypefaceSpan(family)

    @RequiresApi(Build.VERSION_CODES.P)
    fun typeface(typeface: Typeface) = TypefaceSpan(typeface)

    fun url(url: String, textView: TextView? = null): URLSpan {
        textView?.movementMethod = LinkMovementMethod.getInstance()
        return URLSpan(url)
    }
}