package ru.cookedapp.insetsvisualizer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import java.util.EnumSet

@Composable
internal fun InsetsView(insets: EnumSet<InsetsType>) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val typeToInsets = insets.associateWith { it.toInsets() }
    Canvas(modifier = Modifier.fillMaxSize()) {
        typeToInsets.forEach { (type, insets) ->
            drawInsets(
                color = type.toColor(),
                top = insets.getTop(density).toFloat(),
                bottom = insets.getBottom(density).toFloat(),
                left = insets.getLeft(density, layoutDirection).toFloat(),
                right = insets.getRight(density, layoutDirection).toFloat(),
            )
        }
    }
}

fun DrawScope.drawInsets(
    color: Color,
    top: Float,
    bottom: Float,
    left: Float,
    right: Float,
) {
    // Top inset
    if (top > 0) {
        drawRect(
            color = color,
            topLeft = Offset.Zero,
            size = Size(width = size.width, height = top)
        )
    }
    // Bottom inset
    if (bottom > 0) {
        drawRect(
            color = color,
            topLeft = Offset(x = 0f, y = size.height - bottom),
            size = Size(width = size.width, height = bottom),
        )
    }
    // Left inset
    if (left > 0) {
        drawRect(
            color = color,
            topLeft = Offset.Zero,
            size = Size(width = left, height = size.height)
        )
    }
    // Right padding
    if (right > 0) {
        drawRect(
            color = color,
            topLeft = Offset(x = size.width - right, y = 0f),
            size = Size(width = right, height = size.height)
        )
    }
}