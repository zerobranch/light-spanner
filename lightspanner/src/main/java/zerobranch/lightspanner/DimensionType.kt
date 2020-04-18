package zerobranch.lightspanner

import android.content.res.Resources
import android.util.TypedValue

enum class DimensionType {
    PX, DP, SP;

    fun toPx(value: Float): Int {
        val metrics = Resources.getSystem().displayMetrics

        val unit = when (this) {
            DP -> TypedValue.COMPLEX_UNIT_DIP
            SP -> TypedValue.COMPLEX_UNIT_SP
            else -> TypedValue.COMPLEX_UNIT_PX
        }

        return TypedValue.applyDimension(unit, value, metrics).toInt()
    }
}