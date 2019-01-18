package ru.digipeople.database.room.repository

import android.arch.persistence.room.RoomDatabase

import ru.digipeople.database.model.ModelWithId
import ru.digipeople.database.room.entity.EntityWithId

/**
 * Базовый репозиторий для моделей с Id типа [Long].
 *
 * @author Aleksandr Brazhkin
 */
abstract class ModelRepositoryWithLongId<Model : ModelWithId<Long>, Entity : EntityWithId<Long>> protected constructor(appDatabase: RoomDatabase) : ModelRepositoryImpl<Model, Entity, Long>(appDatabase) {

    override fun insert(model: Model) {
        val entity = mapper.modelToEntity(model)
        val id = dao.insert(entity!!)
        model.id = id
    }

    override fun insert(models: List<Model>) {
        val entities = mapper.modelListToEntityList(models)
        val ids = dao.insert(entities!!)
        for (i in models.indices) {
            models[i].id = ids[i]
        }
    }
}