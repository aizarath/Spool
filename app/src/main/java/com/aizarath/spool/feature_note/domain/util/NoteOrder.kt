package com.aizarath.spool.feature_note.domain.util

sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): NoteOrder(orderType)
    class Created(orderType: OrderType): NoteOrder(orderType)
    class Modified(orderType: OrderType): NoteOrder(orderType)
    class Color(orderType: OrderType): NoteOrder(orderType)

    fun copy(orderType: OrderType): NoteOrder{
        return when(this){
            is Title -> Title(orderType)
            is Created -> Created(orderType)
            is Modified -> Modified(orderType)
            is Color -> Color(orderType)
        }
    }
}