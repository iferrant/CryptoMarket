package dev.ferrant.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.ferrant.database.CmDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCmDatabase(
        @ApplicationContext context: Context,
    ): CmDatabase = Room.databaseBuilder(
        context,
        CmDatabase::class.java,
        "cm-database"
    ).build()
}
