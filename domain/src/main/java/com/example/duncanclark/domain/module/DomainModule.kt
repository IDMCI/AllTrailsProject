package com.example.duncanclark.domain.module

import com.example.duncanclark.domain.usecase.GetNearbyPlacesLunchUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Reusable
    @Provides
    @Named("GetNearbyPlacesUseCaseImpl")
    fun provideGetNearbyPlacesUseCase(
//        @Named("NearbyPlacesRepositoryImpl")
//        repository: Repository<Flow<Result<Places>>>
    ): GetNearbyPlacesLunchUseCaseImpl {
        return GetNearbyPlacesLunchUseCaseImpl()
    }
}