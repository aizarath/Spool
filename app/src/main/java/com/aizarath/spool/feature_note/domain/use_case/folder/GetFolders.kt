package com.aizarath.spool.feature_note.domain.use_case.folder

import com.aizarath.spool.feature_note.domain.model.Folder
import com.aizarath.spool.feature_note.domain.repository.FolderRepository
import com.aizarath.spool.feature_note.domain.util.FolderOrder
import com.aizarath.spool.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFolders @Inject constructor(
    private val repository: FolderRepository
) {
    operator fun invoke(
        folderOrder: FolderOrder = FolderOrder.Modified(OrderType.Descending)
    ): Flow<List<Folder>> {
        return repository.getFolders().map { folders ->
            when(folderOrder.orderType){
                is OrderType.Ascending -> {
                    when(folderOrder){
                        is FolderOrder.Name -> folders.sortedBy { it.name.lowercase() }
                        is FolderOrder.Created -> folders.sortedBy { it.timestamp }
                        is FolderOrder.Modified -> folders.sortedBy { it.lastModified }
                    }
                }
                is OrderType.Descending -> {
                    when(folderOrder){
                        is FolderOrder.Name -> folders.sortedByDescending { it.name.lowercase() }
                        is FolderOrder.Created -> folders.sortedByDescending { it.timestamp }
                        is FolderOrder.Modified -> folders.sortedByDescending { it.lastModified }
                    }
                }
            }
        }
    }
}