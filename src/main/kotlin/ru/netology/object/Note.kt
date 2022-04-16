package ru.netology.`object`

class Note(
    noteId: UInt = 0u,
    isDeleted: Boolean,
    userId: UInt,
    val title: String = "title",
    val text: String = "text",
    val privacy: Boolean = false,
) : Crud(noteId, isDeleted, userId)

object NoteService : CrudService<Note>()
