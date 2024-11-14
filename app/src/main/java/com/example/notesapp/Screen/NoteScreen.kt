package com.example.notesapp.Screen

import android.health.connect.datatypes.units.Length
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpace
import androidx.compose.ui.graphics.colorspace.Rgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.R
import com.example.notesapp.components.NoteButton
import com.example.notesapp.components.NoteInputText
import com.example.notesapp.data.NoteDataSource
import com.example.notesapp.model.Note
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
     notes : List<Note>,
     onAddNote : (Note) -> Unit,
     onRemoveNote : (Note) -> Unit
){
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column (modifier = Modifier.padding(6.dp)){

        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.hsl(hue = 250f,
                        saturation = 1f,
                        lightness = 0.1f),
                    fontSize = 30.sp
                )
            )
        },
            actions = { Icon(imageVector = Icons.Rounded.Notifications,
                contentDescription = "Icon")},
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.LightGray))
        // Content
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = title,
                label = "Title" ,
                maxLine = 1,
                onTextChange = {
                    if(it.all {char->
                        char.isLetter() || char.isWhitespace()
                        }){
                        title = it
                    }
                },
                imeAction = ImeAction.Done)
            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = description,
                label = "Add a Note",
                imeAction = ImeAction.Done,
                onTextChange = {
                    if(it.all {char->
                        char.isLetter() || char.isWhitespace()
                        }){
                        description = it
                    }
                })
            NoteButton(text = "Save" ,
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()){
                        onAddNote(
                            Note(title = title,
                            description = description)
                        )
                    }
                    title = ""
                    description = ""
                    Toast.makeText(context,"Note Added", Toast.LENGTH_SHORT).show()
                })

        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn{
            items(notes){note->
               NoteRow(note = note, onNoteClicked = {
                   onRemoveNote(note)
               })

            }
        }
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked : (Note) -> Unit = {}
){
    Surface(
        modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(topEnd = 33.dp)),
        color = Color.LightGray) {
        Surface(
            modifier = Modifier.padding(start = 260.dp) .clickable {
                onNoteClicked(note)
            },
            color = Color.hsl(hue = 90f, saturation = 0.4f, lightness = 0.6f),
            shape = RoundedCornerShape(corner = CornerSize(4.dp))
        ) {
            Text(text = "Remove",
               modifier = Modifier,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.hsl(hue = 250f,
                        saturation = 1f,
                        lightness = 0.1f),
                    textAlign = TextAlign.Center
                )
            )
        }
        Column(
            modifier
                .padding(
                    horizontal = 14.dp,
                    vertical = 9.dp
                ),
            horizontalAlignment = Alignment.Start
        ) {

            Text(text = note.title,
                    style = MaterialTheme.typography.titleLarge)

            Text(text = note.description,
                style = MaterialTheme.typography.titleSmall)

            Text(text = note.time.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
                style = MaterialTheme.typography.titleSmall,
                color = Color.DarkGray)

        }

    }

}
@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen(
        notes = NoteDataSource().loadNotes(),
        onAddNote = {},
        onRemoveNote = {}
    )
    
}