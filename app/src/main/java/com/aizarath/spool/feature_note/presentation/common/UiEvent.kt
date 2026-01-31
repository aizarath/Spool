package com.aizarath.spool.feature_note.presentation.common

sealed class UiEvent{
    data class ShowSnackBar(val message: String): UiEvent()
    object NavigateBack: UiEvent()
}