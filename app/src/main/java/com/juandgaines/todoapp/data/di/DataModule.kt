package com.juandgaines.todoapp.data.di

import android.content.Context
import androidx.room.Room
import com.juandgaines.todoapp.data.RoomTaskLocalDataSource
import com.juandgaines.todoapp.data.TaskDao
import com.juandgaines.todoapp.data.TodoDatabase
import com.juandgaines.todoapp.domain.Task
import com.juandgaines.todoapp.domain.TaskLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDataBase (
        @ApplicationContext
        context: Context,

        ): TodoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TodoDatabase::class.java,
            "todo_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(
        database: TodoDatabase
    ): TaskDao = database.taskDao()

    @Provides
    @Singleton
    fun provideTaskLocalDataSource(
        taskDao: TaskDao,
        dispatcherIO: CoroutineDispatcher
    ): TaskLocalDataSource = RoomTaskLocalDataSource(taskDao)
}