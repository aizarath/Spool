package com.aizarath.spool.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aizarath.spool.feature_note.data.data_source.DatabaseSeeder
import com.aizarath.spool.feature_note.data.data_source.NoteDatabase
import com.aizarath.spool.feature_note.data.repository.FolderRepositoryImp
import com.aizarath.spool.feature_note.data.repository.NoteRepositoryImp
import com.aizarath.spool.feature_note.domain.repository.FolderRepository
import com.aizarath.spool.feature_note.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(
        @ApplicationContext context: Context,
        seeder: DatabaseSeeder
    ): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    seeder.seedDefaultFolder(db)
                }
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideFolderRepository(db: NoteDatabase): FolderRepository {
        return FolderRepositoryImp(db.folderDao)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository{
        return NoteRepositoryImp(db.noteDao)
    }
}
