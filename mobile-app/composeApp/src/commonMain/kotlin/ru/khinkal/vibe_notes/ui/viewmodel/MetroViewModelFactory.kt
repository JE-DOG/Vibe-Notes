package ru.khinkal.vibe_notes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import kotlin.reflect.KClass

class MetroViewModelFactory<T : ViewModel>(
    private val creator: () -> T,
) : ViewModelProvider.Factory {
    override fun <VM : ViewModel> create(
        modelClass: KClass<VM>,
        extras: CreationExtras,
    ): VM {
        @Suppress("UNCHECKED_CAST")
        return creator() as VM
    }
}
