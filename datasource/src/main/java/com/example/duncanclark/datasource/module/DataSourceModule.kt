package com.example.duncanclark.datasource.module

import com.example.duncanclark.datasource.api.NearbyPlacesApiService
import com.example.duncanclark.datasource.mapper.LunchPlacesMapperImpl
import com.example.duncanclark.datasource.remote.NearbyPlacesApiDataSource
import com.example.duncanclark.datasource.remote.NearbyPlacesApiDataSourceImpl
import com.example.duncanclark.datasource.repository.NearbyPlacesRepositoryImpl
import com.example.duncanclark.domain.model.params.ParamsNearbyPlace
import com.example.duncanclark.domain.model.ui.Places
import com.example.duncanclark.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    @Named("NearbyPlacesRepositoryImpl")
    fun provideNearbyPlacesRepositoryImpl(
        dataSource: NearbyPlacesApiDataSource,
        mapper: LunchPlacesMapperImpl
    ): Repository<ParamsNearbyPlace, Flow<Result<Places>>> {
        return NearbyPlacesRepositoryImpl(dataSource, mapper)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://places.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideNearbyPlacesApiService(
        retrofit: Retrofit
    ): NearbyPlacesApiService = retrofit.create(NearbyPlacesApiService::class.java)

    @Reusable
    @Provides
    fun provideApiPlacesToPlacesMapperImpl() = LunchPlacesMapperImpl()

    @Singleton
    @Provides
    fun provideNearbyPlacesApiDataSourceImpl(
        apiService: NearbyPlacesApiService,
    ): NearbyPlacesApiDataSource = NearbyPlacesApiDataSourceImpl(apiService)
}