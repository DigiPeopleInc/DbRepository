package ru.digipeople.db.room.repository

import android.arch.persistence.room.RoomDatabase
import ru.digipeople.db.repository.Repository

/**
 * Репозиторий.
 *
 * @author Aleksandr Brazhkin
 */
abstract class RepositoryImpl(protected val roomDb: RoomDatabase) : Repository {

    protected fun beginTransaction() {
        roomDb.beginTransaction()
    }

    protected fun endTransaction() {
        roomDb.endTransaction()
    }

    protected fun setTransactionSuccessful() {
        roomDb.setTransactionSuccessful()
    }
}