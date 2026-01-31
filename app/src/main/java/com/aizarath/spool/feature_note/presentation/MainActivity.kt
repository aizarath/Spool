package com.aizarath.spool.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aizarath.spool.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.aizarath.spool.feature_note.presentation.folder_notes.FolderNotesScreen
import com.aizarath.spool.feature_note.presentation.folders.FoldersScreen
import com.aizarath.spool.feature_note.presentation.notes.NotesScreen
import com.aizarath.spool.feature_note.presentation.util.Screen
import com.aizarath.spool.ui.theme.SpoolTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpoolTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.FoldersScreen.route
                    ){
                        composable(route = Screen.FoldersScreen.route){
                            FoldersScreen(navController=navController)
                        }
                        composable(route = Screen.FolderNotesScreen.route +
                                "?folderId={folderId}&folderColor={folderColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "folderId"
                                ){
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "folderColor"
                                ){
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ){
                            FolderNotesScreen(navController=navController)
                        }
                        composable(route = Screen.NotesScreen.route){
                            NotesScreen(navController=navController)
                        }
                        composable(route = Screen.AddEditNoteScreen.route +
                                "?noteId={noteId}&noteColor={noteColor}",
                                arguments = listOf(
                                    navArgument(
                                        name = "noteId"
                                    ){
                                        type = NavType.IntType
                                        defaultValue = -1
                                    },
                                    navArgument(
                                        name = "noteColor"
                                    ){
                                        type = NavType.IntType
                                        defaultValue = -1
                                    },
                                )
                            ){
                            AddEditNoteScreen(
                                navController=navController,
                            )
                        }
                    }
                }
            }
        }
    }
}