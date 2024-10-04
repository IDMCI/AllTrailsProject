package com.example.duncanclark.domain.module

import com.example.duncanclark.domain.mapper.ParamsNearbyPlaceMapperImpl
import com.example.duncanclark.domain.model.params.ParamsForNearbyPlaces
import com.example.duncanclark.domain.model.ui.Places
import com.example.duncanclark.domain.repository.Repository
import com.example.duncanclark.domain.usecase.GetNearbyPlacesLunchUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Reusable
    @Provides
    @Named("ParamsNearbyPlaceMapperImpl")
    fun provideParamsNearbyPlaceMapperImpl() = ParamsNearbyPlaceMapperImpl()

    @Singleton
    @Provides
    @Named("GetNearbyPlacesLunchUseCaseImpl")
    fun provideGetNearbyPlacesUseCaseImpl(
        @Named("NearbyPlacesRepositoryImpl") repository: Repository<ParamsForNearbyPlaces, Flow<Result<Places>>>,
        @Named("ParamsNearbyPlaceMapperImpl") mapper: ParamsNearbyPlaceMapperImpl,
    ): GetNearbyPlacesLunchUseCaseImpl {
        return GetNearbyPlacesLunchUseCaseImpl(repository, mapper)
    }
}