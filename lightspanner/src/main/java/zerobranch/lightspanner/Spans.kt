package zerobranch.lightspanner

import android.content.Context
import android.content.res.Resources.NotFoundException
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
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import zerobranch.lightspanner.custom.CustomTypefaceSpan
import zerobranch.lightspanner.custom.LinesLeadingMarginSpan
import zerobranch.lightspanner.custom.SimpleClickableSpan
import java.util.*

object Spans {
    // Actions
    fun clickable(
        textView: TextView,
        clickListener: (() -> Unit),
        touchListener: ((Boolean, TextPaint) -> Unit)? = null,
        @ColorInt highlightColor: Int = Color.TRANSPARENT,
        isUnderlineText: Boolean = true
    ): ClickableSpan {
        textView.movementMethod = LinkMovementMethod.getInstance()
        textView.highlightColor = highlightColor
        return SimpleClickableSpan(textView, clickListener, touchListener, isUnderlineText)
    }

    fun url(url: String, textView: TextView? = null): URLSpan {
        textView?.movementMethod = LinkMovementMethod.getInstance()
        return URLSpan(url)
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
        @ColorInt colorInt: Int,
        dimensionType: DimensionType = DimensionType.PX
    ) = BulletSpan(dimensionType.toPx(gapWidth), colorInt)

    fun bullet(
        context: Context,
        @FloatRange(from = 0.0) gapWidth: Float,
        @ColorRes colorResId: Int,
        dimensionType: DimensionType = DimensionType.PX
    ) = BulletSpan(dimensionType.toPx(gapWidth), ContextCompat.getColor(context, colorResId))

    @RequiresApi(Build.VERSION_CODES.P)
    fun bullet(
        @FloatRange(from = 0.0) gapWidth: Float,
        @ColorInt colorInt: Int,
        @FloatRange(from = 0.0) bulletRadius: Float,
        dimensionType: DimensionType = DimensionType.PX
    ) = BulletSpan(dimensionType.toPx(gapWidth), colorInt, dimensionType.toPx(bulletRadius))

    @RequiresApi(Build.VERSION_CODES.P)
    fun bullet(
        context: Context,
        @FloatRange(from = 0.0) gapWidth: Float,
        @ColorRes colorResId: Int,
        @FloatRange(from = 0.0) bulletRadius: Float,
        dimensionType: DimensionType = DimensionType.PX
    ) = BulletSpan(
        dimensionType.toPx(gapWidth),
        ContextCompat.getColor(context, colorResId),
        dimensionType.toPx(bulletRadius)
    )

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
    fun lineBackground(@ColorInt colorInt: Int) = LineBackgroundSpan.Standard(colorInt)

    @RequiresApi(Build.VERSION_CODES.Q)
    fun lineBackground(
        context: Context,
        @ColorRes colorResId: Int
    ) = LineBackgroundSpan.Standard(ContextCompat.getColor(context, colorResId))

    fun textSize(
        size: Float,
        dimensionType: DimensionType = DimensionType.PX
    ) = AbsoluteSizeSpan(dimensionType.toPx(size), false)

    fun textSize(
        context: Context,
        @DimenRes dimenResId: Int
    ) = AbsoluteSizeSpan(context.resources.getDimensionPixelSize(dimenResId), false)

    fun relativeSize(@FloatRange(from = 0.0) proportion: Float) = RelativeSizeSpan(proportion)

    fun foregroundColor(@ColorInt colorInt: Int) = ForegroundColorSpan(colorInt)

    fun foregroundColor(
        context: Context,
        @ColorRes colorResId: Int
    ) = ForegroundColorSpan(ContextCompat.getColor(context, colorResId))

    fun textColor(@ColorInt colorInt: Int) = foregroundColor(colorInt)

    fun textColor(
        context: Context,
        @ColorRes colorResId: Int
    ) = foregroundColor(context, colorResId)

    fun backgroundColor(@ColorInt colorInt: Int) = BackgroundColorSpan(colorInt)

    fun backgroundColor(
        context: Context,
        @ColorRes colorResId: Int
    ) = BackgroundColorSpan(ContextCompat.getColor(context, colorResId))

    fun drawableMargin(
        drawable: Drawable,
        padding: Float = 0F,
        dimensionType: DimensionType = DimensionType.PX
    ) = DrawableMarginSpan(drawable, dimensionType.toPx(padding))

    fun drawableMargin(
        context: Context,
        @DrawableRes drawableResId: Int,
        padding: Float = 0F,
        dimensionType: DimensionType = DimensionType.PX
    ) : DrawableMarginSpan {
        val drawable = ContextCompat.getDrawable(context, drawableResId)
            ?: throw NotFoundException(
                "Unable to find resource ID #0x" + Integer.toHexString(drawableResId)
            )

        return DrawableMarginSpan(drawable, dimensionType.toPx(padding))
    }

    fun iconMargin(
        bitmap: Bitmap,
        padding: Float = 0F,
        dimensionType: DimensionType = DimensionType.PX
    ) = IconMarginSpan(bitmap, dimensionType.toPx(padding))

    fun image(
        context: Context,
        @DrawableRes drawableResId: Int,
        verticalAlignment: Int = DynamicDrawableSpan.ALIGN_BOTTOM
    ) = ImageSpan(context, drawableResId, verticalAlignment)

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
        context: Context,
        @DrawableRes drawableResId: Int,
        source: String,
        verticalAlignment: Int = DynamicDrawableSpan.ALIGN_BOTTOM
    ) : ImageSpan {
        val drawable = ContextCompat.getDrawable(context, drawableResId)
            ?: throw NotFoundException(
                "Unable to find resource ID #0x" + Integer.toHexString(drawableResId)
            )

        return ImageSpan(drawable, source, verticalAlignment)
    }

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

    fun strikethrough() = StrikethroughSpan()

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
        @ColorInt colorInt: Int,
        @FloatRange(from = 0.0) stripeWidth: Float,
        gapWidth: Float,
        dimensionType: DimensionType = DimensionType.PX
    ) = QuoteSpan(colorInt, dimensionType.toPx(stripeWidth), dimensionType.toPx(gapWidth))

    @RequiresApi(Build.VERSION_CODES.P)
    fun quote(
        context: Context,
        @ColorRes colorResId: Int,
        @FloatRange(from = 0.0) stripeWidth: Float,
        gapWidth: Float,
        dimensionType: DimensionType = DimensionType.PX
    ) = QuoteSpan(
        ContextCompat.getColor(context, colorResId),
        dimensionType.toPx(stripeWidth),
        dimensionType.toPx(gapWidth)
    )

    fun typeface(family: String?) = TypefaceSpan(family)

    @RequiresApi(Build.VERSION_CODES.P)
    fun typeface(typeface: Typeface) = TypefaceSpan(typeface)

    fun font(typeface: Typeface) = CustomTypefaceSpan(typeface)

    fun font(
        context: Context,
        @FontRes fontResId: Int
    ) = CustomTypefaceSpan(ResourcesCompat.getFont(context, fontResId))
}