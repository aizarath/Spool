package com.aizarath.spool.feature_note.domain.util

sealed class FolderOrder(val orderType: OrderType) {
    class Name(orderType: OrderType): FolderOrder(orderType)
    class Created(orderType: OrderType): FolderOrder(orderType)
    class Modified(orderType: OrderType): FolderOrder(orderType)

    fun copy(orderType: OrderType): FolderOrder{
        return when(this){
            is Name -> Name(orderType)
            is Created -> Created(orderType)
            is Modified -> Modified(orderType)
        }
    }
}
