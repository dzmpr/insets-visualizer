package ru.cookedapp.insetsvisualizer

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.captionBarIgnoringVisibility
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.mandatorySystemGestures
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsIgnoringVisibility
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.tappableElement
import androidx.compose.foundation.layout.tappableElementIgnoringVisibility
import androidx.compose.foundation.layout.waterfall
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun InsetsType.toInsets(): WindowInsets = when (this) {
    InsetsType.STATUS_BARS -> WindowInsets.statusBars
    InsetsType.STATUS_BARS_IGNORING_VISIBILITY -> WindowInsets.statusBarsIgnoringVisibility
    InsetsType.NAVIGATION_BARS -> WindowInsets.navigationBars
    InsetsType.NAVIGATION_BARS_IGNORING_VISIBILITY -> WindowInsets.navigationBarsIgnoringVisibility
    InsetsType.CAPTION_BAR -> WindowInsets.captionBar
    InsetsType.CAPTION_BAR_IGNORING_VISIBILITY -> WindowInsets.captionBarIgnoringVisibility
    InsetsType.TAPPABLE_ELEMENT -> WindowInsets.tappableElement
    InsetsType.TAPPABLE_ELEMENT_IGNORING_VISIBILITY -> WindowInsets.tappableElementIgnoringVisibility
    InsetsType.SYSTEM_GESTURES -> WindowInsets.systemBars
    InsetsType.MANDATORY_SYSTEM_GESTURES -> WindowInsets.mandatorySystemGestures
    InsetsType.IME -> WindowInsets.ime
    InsetsType.WATERFALL -> WindowInsets.waterfall
    InsetsType.DISPLAY_CUTOUT -> WindowInsets.displayCutout
    InsetsType.SAFE_DRAWING -> WindowInsets.safeDrawing
    InsetsType.SAFE_GESTURES -> WindowInsets.safeGestures
    InsetsType.SAFE_CONTENT -> WindowInsets.safeContent
}

fun InsetsType.toColor(): Color = when (this) {
    InsetsType.STATUS_BARS -> 0xFFF00000
    InsetsType.STATUS_BARS_IGNORING_VISIBILITY -> 0xFF0F0000
    InsetsType.NAVIGATION_BARS -> 0xFF00F000
    InsetsType.NAVIGATION_BARS_IGNORING_VISIBILITY -> 0xFF000F00
    InsetsType.CAPTION_BAR -> 0xFF0000F0
    InsetsType.CAPTION_BAR_IGNORING_VISIBILITY -> 0xFF00000F
    InsetsType.TAPPABLE_ELEMENT -> 0xFFFF0000
    InsetsType.TAPPABLE_ELEMENT_IGNORING_VISIBILITY -> 0xFF00FF00
    InsetsType.SYSTEM_GESTURES -> 0xFF0000FF
    InsetsType.MANDATORY_SYSTEM_GESTURES -> 0xFFF0000F
    InsetsType.IME -> 0xFFFFF000
    InsetsType.WATERFALL -> 0xFF000FFF
    InsetsType.DISPLAY_CUTOUT -> 0xFF0FFFF0
    InsetsType.SAFE_DRAWING -> 0xFFFF0000
    InsetsType.SAFE_GESTURES -> 0xFF00FF00
    InsetsType.SAFE_CONTENT -> 0xFF0000FF
}.let(::Color).copy(alpha = 0.3f)

@StringRes
fun InsetsType.toName(): Int = when (this) {
    InsetsType.STATUS_BARS -> R.string.inset_type_status_bars
    InsetsType.STATUS_BARS_IGNORING_VISIBILITY -> R.string.inset_type_status_bars_ignoring_visibility
    InsetsType.NAVIGATION_BARS -> R.string.inset_type_navigation_bars
    InsetsType.NAVIGATION_BARS_IGNORING_VISIBILITY -> R.string.inset_type_navigation_bars_ignoring_visibility
    InsetsType.CAPTION_BAR -> R.string.inset_type_caption_bar
    InsetsType.CAPTION_BAR_IGNORING_VISIBILITY -> R.string.inset_type_caption_bar_ignoring_visibility
    InsetsType.TAPPABLE_ELEMENT -> R.string.inset_type_tappable_element
    InsetsType.TAPPABLE_ELEMENT_IGNORING_VISIBILITY -> R.string.inset_type_tappable_element_ignoring_visibility
    InsetsType.SYSTEM_GESTURES -> R.string.inset_type_system_gestures
    InsetsType.MANDATORY_SYSTEM_GESTURES -> R.string.inset_type_mandatory_system_gestures
    InsetsType.IME -> R.string.inset_type_ime
    InsetsType.WATERFALL -> R.string.inset_type_waterfall
    InsetsType.DISPLAY_CUTOUT -> R.string.inset_type_display_cutout
    InsetsType.SAFE_DRAWING -> R.string.inset_type_safe_drawing
    InsetsType.SAFE_GESTURES -> R.string.inset_type_safe_gestures
    InsetsType.SAFE_CONTENT -> R.string.inset_type_safe_content
}