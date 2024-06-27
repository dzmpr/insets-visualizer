package ru.cookedapp.insetsvisualizer

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.EnumSet

internal class MainViewModel : ViewModel() {

    val enabledInsets: StateFlow<EnumSet<InsetsType>>
        field: MutableStateFlow<EnumSet<InsetsType>> = MutableStateFlow(EnumSet.noneOf(InsetsType::class.java))

    val isDialogOpened: StateFlow<Boolean>
        field: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun onDialogOpenClicked() {
        isDialogOpened.update { true }
    }

    fun onDialogDismissed() {
        isDialogOpened.update { false }
    }

    fun onInsetStateChanged(insetType: InsetsType) {
        enabledInsets.update { currentInsets ->
            if (insetType in currentInsets) {
                currentInsets.remove(insetType)
            } else {
                currentInsets.add(insetType)
            }
            currentInsets
        }
    }
}