package ru.digipeople.db.room.repository

import android.arch.persistence.room.RoomDatabase
import ru.digipeople.db.model.ModelWithId
import ru.digipeople.db.repository.ModelRepository
import ru.digipeople.db.room.dao.BaseDao
import ru.digipeople.db.room.entity.EntityWithId
import ru.digipeople.db.room.mapper.BaseMapper

/**
 * Базовый репозиторий для моделей с Id.
 *
 * @author Aleksandr Brazhkin
 */
abstract class ModelRepositoryImpl<Model : ModelWithId<Id>, Entity : EntityWithId<Id>, Id> protected constructor(appDatabase: RoomDatabase) : RepositoryImpl(appDatabase), ModelRepository<Model, Id> {

    abstract val dao: BaseDao<Entity>

    abstract val mapper: BaseMapper<Model, Entity>

    abstract fun loadById(id: Id): Model

    override fun insert(model: Model) {
        val entity = mapper.modelToEntity(model)
        dao.insert(entity!!)
    }

    override fun insert(models: List<Model>) {
        val entities = mapper.modelListToEntityList(models)
        dao.insert(entities!!)
    }

    override fun update(model: Model) {
        dao.update(mapper.modelToEntity(model)!!)
    }

    override fun delete(model: Model) {
        dao.delete(mapper.modelToEntity(model)!!)
    }

    override fun delete(models: List<Model>) {
        val entities = mapper.modelListToEntityList(models)
        dao.delete(entities!!)
    }
}
