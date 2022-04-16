package ru.netology

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import ru.netology.`object`.*


class MainTest {


    @Test
    fun addNoteTest() {
        val expectedId = 3u
        val actualId = NoteService.add(Note(noteId = 4u, isDeleted = false, userId = 4u))
        assertEquals(expectedId, actualId)
    }

    @Test
    fun addCommentTest() {
        val expectedId = 1u
        val actualId = CommentService.add(Comment(commentId = 10u, isDeleted = false, userId = 123u, noteId = 1u))
        assertEquals(expectedId, actualId)
    }

    @Test
    fun updateNoteTest() {
        NoteService.add(Note(noteId = 1u, isDeleted = false, userId = 1u))
        val actual = NoteService.update(Note(noteId = 1u, isDeleted = false, userId = 4u))
        assertTrue(actual)
    }

    @Test
    fun updateCommentTest() {
        val actual = CommentService.update(Comment(commentId = 1u, isDeleted = false, userId = 123u, noteId = 1u))
        assertTrue(actual)
    }

    @Test
    fun getNoteById() {
        val expectedNote = Note(noteId = 2u, isDeleted = false, userId = 4u)
        NoteService.add(Note(noteId = 4u, isDeleted = false, userId = 4u))
        val actualNote = NoteService.getById(2u)
        if (actualNote != null) {
            assertEquals(expectedNote.id, actualNote.id)
        }
    }

    @Test(expected = NotFoundException::class)
    fun getNoteByIdException() {
        val expectedNote = Note(noteId = 2u, isDeleted = false, userId = 4u)
        val actualNote = NoteService.getById(10u)
        assertEquals(expectedNote, actualNote)
    }

    @Test
    fun deleteNoteById() {
        val expectedNote = true
        val actualNote = NoteService.delete(2u)
        assertEquals(expectedNote, actualNote)
    }

    @Test(expected = NotFoundException::class)
    fun getNoteByUserIdException() {
        val expectedNotes = mutableListOf<Note>(
            Note(noteId = 2u, isDeleted = false, userId = 4u),
            Note(noteId = 2u, isDeleted = false, userId = 4u)
        )
        val actualNotes = NoteService.get(22u)
        assertEquals(expectedNotes, actualNotes)
    }

    @Test
    fun restoreCommentTest() {
        val actual = CommentService.restoreComment(1u)
        assertTrue(actual)
    }

    @Test(expected = NotFoundException::class)
    fun getCommentsByNoteIdException() {
        val expectedNotes = mutableListOf<Comment>(

        )
        val actualNotes = CommentService.getComments(22u)
        assertEquals(expectedNotes, actualNotes)
    }

    @Test
    fun deleteCommentsByNoteId() {
        val actual = CommentService.deleteComments(1u)
        assertTrue(actual)
    }
}