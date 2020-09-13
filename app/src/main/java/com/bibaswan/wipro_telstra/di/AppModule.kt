package com.bibaswan.wipro_telstra.di

import android.content.Context
import com.bibaswan.wipro_telstra.data.local.AppDatabase
import com.bibaswan.wipro_telstra.data.local.CanadaInfoDao
import com.bibaswan.wipro_telstra.data.remote.CanadaInfoRemoteDataSource
import com.bibaswan.wipro_telstra.data.remote.CanadaInfoService
import com.bibaswan.wipro_telstra.data.repository.CanadaInfoRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): CanadaInfoService = retrofit.create(
        CanadaInfoService::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(canadaInfoService: CanadaInfoService) = CanadaInfoRemoteDataSource(canadaInfoService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.characterDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: CanadaInfoRemoteDataSource,
                          localDataSource: CanadaInfoDao) =
        CanadaInfoRepository(remoteDataSource, localDataSource)
}