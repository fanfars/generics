package ru.netology.`object`

class Comment(
    commentId: UInt = 0u,
    isDeleted: Boolean,
    userId: UInt,
    val noteId: UInt,
    val replyTo: UInt = 100u,
    val message: String = "message",
) : Crud(commentId, isDeleted, userId)

object CommentService : CrudService<Comment>() {

    fun restoreComment(commentId: UInt): Boolean {
        val comment = this.getById(commentId)
        if (comment != null) {
            comment.isDeleted = false
            return true
        }
        return false
    }

    fun getComments(noteId: UInt): MutableList<Comment>? {
        val results = mutableListOf<Comment>()
        for ((index, crud) in items.withIndex()) if (noteId == crud.noteId) results.add(crud)
        if (results.isEmpty()) throw NotFoundException("Item with ID $noteId not found")
        return results
    }

    fun deleteComments(noteId: UInt): Boolean {
        var value = false
        for ((index, crud) in items.withIndex()) if (noteId == crud.noteId) {
            crud.isDeleted = true
            value = true
        }
        return value
    }

}
