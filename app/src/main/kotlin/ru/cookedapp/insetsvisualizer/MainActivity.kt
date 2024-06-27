package ru.cookedapp.insetsvisualizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.cookedapp.insetsvisualizer.ui.theme.InsetsVisualizerTheme
import java.util.EnumSet
import android.graphics.Color as AndroidColor

internal class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val barsStyle = if (isDarkModeEnabled) {
            SystemBarStyle.dark(AndroidColor.TRANSPARENT)
        } else {
            SystemBarStyle.light(AndroidColor.TRANSPARENT, AndroidColor.TRANSPARENT)
        }
        enableEdgeToEdge(statusBarStyle = barsStyle, navigationBarStyle = barsStyle)
        super.onCreate(savedInstanceState)
        setContent {
            InsetsVisualizerTheme {
                val isDialogOpened by viewModel.isDialogOpened.collectAsStateWithLifecycle()
                val enabledInsets by viewModel.enabledInsets.collectAsStateWithLifecycle()

                AlertDialog(isOpened = isDialogOpened, enabledInsets = enabledInsets)

                Surface(color = MaterialTheme.colorScheme.surfaceContainerLowest) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            enabledInsets.forEach { inset ->
                                InsetTypeRow(inset)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            FilledTonalButton(
                                onClick = viewModel::onDialogOpenClicked,
                            ) {
                                Text(text = "Select insets")
                            }
                        }
                        ActiveInsets(enabledInsets)
                    }
                }
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun AlertDialog(
        isOpened: Boolean,
        enabledInsets: EnumSet<InsetsType>,
    ) {
        if (!isOpened) return
        BasicAlertDialog(
            onDismissRequest = viewModel::onDialogDismissed,
        ) {
            Surface(shape = RoundedCornerShape(12.dp)) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                ) {
                    InsetsType.entries.forEach { insetType ->
                        InsetCheckbox(
                            insetType = insetType,
                            isEnabled = insetType in enabledInsets,
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun InsetCheckbox(insetType: InsetsType, isEnabled: Boolean) {
        var isInsetEnabled by remember { mutableStateOf(isEnabled) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isInsetEnabled,
                onCheckedChange = { newState ->
                    isInsetEnabled = newState
                    viewModel.onInsetStateChanged(insetType)
                }
            )
            Text(
                text = stringResource(insetType.toName()),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }

    @Composable
    fun InsetTypeRow(insetType: InsetsType) {
        Row(
            modifier = Modifier.padding(all = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val onSurfaceVariantColor = MaterialTheme.colorScheme.onSurfaceVariant
            Canvas(modifier = Modifier.size(24.dp)) {
                drawCircle(color = onSurfaceVariantColor)
                drawCircle(color = Color.White, radius = 10.dp.toPx())
                drawCircle(color = insetType.toColor(), radius = 10.dp.toPx())
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(insetType.toName()),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }

    @Composable
    private fun ActiveInsets(enabledInsets: EnumSet<InsetsType>) {
        val density = LocalDensity.current
        val layoutDirection = LocalLayoutDirection.current
        enabledInsets.forEach { insetType ->
            val insetColor = insetType.toColor()
            val insets = insetType.toInsets()
            val topPadding = insets.getTop(density).toFloat()
            val bottomPadding = insets.getBottom(density).toFloat()
            val leftPadding = insets.getLeft(density, layoutDirection).toFloat()
            val rightPadding = insets.getRight(density, layoutDirection).toFloat()

            Canvas(modifier = Modifier.fillMaxSize()) {
                // Top padding
                drawRect(
                    color = insetColor,
                    topLeft = Offset.Zero,
                    size = Size(width = size.width, height = topPadding)
                )
                // Bottom padding
                drawRect(
                    color = insetColor,
                    topLeft = Offset(x = 0f, y = size.height - bottomPadding),
                    size = Size(width = size.width, height = bottomPadding),
                )
                // Left padding
                drawRect(
                    color = insetColor,
                    topLeft = Offset.Zero,
                    size = Size(width = leftPadding, height = size.height)
                )
                // Right padding
                drawRect(
                    color = insetColor,
                    topLeft = Offset(x = size.width - rightPadding, y = 0f),
                    size = Size(width = rightPadding, height = size.height)
                )
            }
        }
    }
}