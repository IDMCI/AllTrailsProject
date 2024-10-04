package com.example.duncanclark.domain.module

import com.example.duncanclark.domain.mapper.ParamsNearbyPlacesMapperImpl
import com.example.duncanclark.domain.mapper.ParamsSearchTextPlacesMapperImpl
import com.example.duncanclark.domain.model.params.ParamsForNearbyPlaces
import com.example.duncanclark.domain.model.params.ParamsForSearchTextPlaces
import com.example.duncanclark.domain.model.ui.Places
import com.example.duncanclark.domain.repository.Repository
import com.example.duncanclark.domain.usecase.GetNearbyPlacesLunchUseCaseImpl
import com.example.duncanclark.domain.usecase.SearchTextLunchUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Reusable
    @Provides
    @Named("ParamsNearbyPlacesMapperImpl")
    fun provideParamsNearbyPlacesMapperImpl() = ParamsNearbyPlacesMapperImpl()

    @Singleton
    @Provides
    @Named("GetNearbyPlacesLunchUseCaseImpl")
    fun provideGetNearbyPlacesUseCaseImpl(
        @Named("NearbyPlacesRepositoryImpl") repository:
            Repository<ParamsForNearbyPlaces, Flow<Result<Places>>>,
        @Named("ParamsNearbyPlacesMapperImpl") mapper: ParamsNearbyPlacesMapperImpl,
    ): GetNearbyPlacesLunchUseCaseImpl {
        return GetNearbyPlacesLunchUseCaseImpl(repository, mapper)
    }

    @Reusable
    @Provides
    @Named("ParamsSearchTextPlacesMapperImpl")
    fun provideParamsSearchTextPlacesMapperImpl() = ParamsSearchTextPlacesMapperImpl()

    @Singleton
    @Provides
    @Named("SearchTextLunchUseCaseImpl")
    fun provideSearchTextLunchUseCaseImpl(
        @Named("SearchTextPlacesRepositoryImpl") repository:
            Repository<ParamsForSearchTextPlaces, Flow<Result<Places>>>,
        @Named("ParamsSearchTextPlacesMapperImpl") mapper: ParamsSearchTextPlacesMapperImpl,
    ): SearchTextLunchUseCaseImpl {
        return SearchTextLunchUseCaseImpl(repository, mapper)
    }
}