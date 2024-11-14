package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notesapp.Screen.NoteScreen
import com.example.notesapp.Screen.NoteViewModel
import com.example.notesapp.model.Note
import com.example.notesapp.ui.theme.NotesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val noteViewModel : NoteViewModel by viewModels()
                    MyApp(noteViewModel = noteViewModel)
                }
            }
        }
    }
}

@Composable
fun MyApp(noteViewModel: NoteViewModel = viewModel()){
    val noteList = noteViewModel.getAllNotes()
    NoteScreen(
        notes = noteList,
        onAddNote = {noteViewModel.addNote(it)},
        onRemoveNote = {noteViewModel.removeNote(it)}
    )



}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotesAppTheme {

    }
}