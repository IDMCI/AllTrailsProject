package com.example.duncanclark.domain.usecase

import com.example.duncanclark.domain.model.params.Center
import com.example.duncanclark.domain.model.params.Circle
import com.example.duncanclark.domain.model.params.FieldMask
import com.example.duncanclark.domain.model.params.IncludedType
import com.example.duncanclark.domain.model.params.LocationRestriction
import com.example.duncanclark.domain.model.params.NearbyPlaceRequestParams
import com.example.duncanclark.domain.model.ui.LunchPlaces
import com.example.duncanclark.domain.model.params.ParamsNearbyPlace
import com.example.duncanclark.domain.model.params.nearbyPlacesLunch
import com.example.duncanclark.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

//interface SearchNearbyPlacesLunchUseCase {
//    suspend fun execute(lat: Double, lng: Double): Flow<Result<LunchPlaces>>
//}

class SearchNearbyPlacesLunchUseCase @Inject constructor(
    @Named("NearbyPlacesRepositoryImpl")
    private val repository: Repository<ParamsNearbyPlace, Flow<Result<LunchPlaces>>>,
//    @Named("ParamsNearbyPlaceMapperImpl")
//    private val mapper: Mapper<Pair<Double, Double>, ParamsNearbyPlace>
): UseCaseWithParam<ParamsNearbyPlace, LunchPlaces> {
    private val fieldMasks = FieldMask.nearbyPlacesLunch()
    private val lat = 40.479822043320816
    private val lng = -104.89954079298904
    private val request = NearbyPlaceRequestParams(
        locationRestriction = LocationRestriction(
            circle = Circle(Center(lat, lng))
        ),
        includedTypes = IncludedType.nearbyPlacesLunch(),
        maxResultCount = 1
    )

    override suspend fun execute(input: ParamsNearbyPlace): Flow<Result<LunchPlaces>> {
        return repository.getData(ParamsNearbyPlace(
            fieldMasks = fieldMasks,
            bodyParams = request
        ))
    }
}