package ru.cookedapp.insetsvisualizer

data class WindowInsetsData(
    val type: InsetsType,
    val top: Float,
    val bottom: Float,
    val left: Float,
    val right: Float,
) {

    override fun toString(): String = buildString {
        append("(")
        if (top == 0f && bottom == 0f && left == 0f && right == 0f) {
            append("empty")
        } else {
            if (top > 0) append("top: ${top.toInt()},")
            if (bottom > 0) append("bottom: ${bottom.toInt()},")
            if (left > 0) append("left: ${left.toInt()},")
            if (right > 0) append("right: ${right.toInt()},")
            deleteCharAt(lastIndex)
        }
        append(")")
    }
}
