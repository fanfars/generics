package ru.netology.`object`

open class Crud(
    var id: UInt,
    var isDeleted: Boolean = false,
    var userId: UInt
)

open class CrudService<T : Crud> {

    private var nextId = 1u
    protected val items = mutableListOf<T>()

    fun add(item: T): UInt {
        item.id = nextId++
        items.add(item)
        return items.last().id
    }

    fun update(item: T): Boolean {
        for ((index, crud) in items.withIndex()) {
            if (crud.id == item.id) {
                items[index] = item
                return true
            }
        }
        return false
    }

    fun getById(id: UInt): T? {
        for ((index, crud) in items.withIndex()) if (id == crud.id){return crud}
        throw NotFoundException("Item with ID $id not found")
    }

    fun delete(id: UInt): Boolean {
        for ((index, crud) in items.withIndex()) if (id == crud.id) {
            items[index].isDeleted = true
            return true
        }
        return false
    }

    fun get (userId: UInt) : MutableList<T>?{
        val results = mutableListOf<T>()
        for ((index, crud) in items.withIndex()) if (crud.userId == userId) {
            results.add(crud)
        }
        if(results.isEmpty()) throw NotFoundException("Item with ID $userId not found")
        return results
}

}