package com.aizarath.spool.feature_note.domain.model

class InvalidFolderException(message: String): Exception(message)
class FolderNotFoundException(message: String): Exception(message)