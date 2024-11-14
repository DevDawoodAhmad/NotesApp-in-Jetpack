package com.example.notesapp.data

import com.example.notesapp.model.Note

class NoteDataSource{
    fun loadNotes():List<Note> {
        return listOf(
            Note(
                title = "Have A good Day",
                description = "Work all the days best and remain satisfy"
            ),
            Note(
                title = "Have A good Health",
                description = "Do exercise all the days and remain Fit"
            ),
            Note(
                title = "Have A good Day",
                description = "Work all the days best and remain satisfy"
            ),
            Note(
                title = "Have A good Health",
                description = "Do exercise all the days and remain Fit"
            ),
            Note(
                title = "Have A good Day",
                description = "Work all the days best and remain satisfy"
            ),
            Note(
                title = "Have A good Health",
                description = "Do exercise all the days and remain Fit"
            )
        )
    }
}