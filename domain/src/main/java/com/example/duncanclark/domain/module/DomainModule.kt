package com.example.duncanclark.domain.module

import com.example.duncanclark.domain.mapper.ParamsNearbyPlaceMapperImpl
import com.example.duncanclark.domain.model.ui.LunchPlaces
import com.example.duncanclark.domain.model.params.ParamsNearbyPlace
import com.example.duncanclark.domain.repository.Repository
import com.example.duncanclark.domain.usecase.GetNearbyPlacesLunchUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Reusable
    @Provides
    fun provideParamsNearbyPlaceMapperImpl() = ParamsNearbyPlaceMapperImpl()

    @Reusable
    @Provides
    @Named("GetNearbyPlacesUseCaseImpl")
    fun provideGetNearbyPlacesUseCase(
        @Named("NearbyPlacesRepositoryImpl")
        repository: Repository<ParamsNearbyPlace, Flow<Result<LunchPlaces>>>,
        mapper: ParamsNearbyPlaceMapperImpl,
    ): GetNearbyPlacesLunchUseCaseImpl {
        return GetNearbyPlacesLunchUseCaseImpl(repository, mapper)
    }
}