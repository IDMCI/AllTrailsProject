package com.example.duncanclark.domain.mapper

import com.example.duncanclark.domain.common.mapper.Mapper
import com.example.duncanclark.domain.model.params.Center
import com.example.duncanclark.domain.model.params.Circle
import com.example.duncanclark.domain.model.params.FieldMask
import com.example.duncanclark.domain.model.params.IncludedType
import com.example.duncanclark.domain.model.params.LocationRestriction
import com.example.duncanclark.domain.model.params.NearbyPlaceRequestParams
import com.example.duncanclark.domain.model.params.ParamsNearbyPlace
import com.example.duncanclark.domain.model.params.nearbyPlacesLunch
import javax.inject.Inject

class ParamsNearbyPlaceMapperImpl @Inject constructor()
    : Mapper<Pair<Double, Double>, ParamsNearbyPlace> {

    override fun invoke(input: Pair<Double, Double>): ParamsNearbyPlace {
        return ParamsNearbyPlace(
            fieldMasks = FieldMask.nearbyPlacesLunch(),
            bodyParams = NearbyPlaceRequestParams(
                locationRestriction = createLocationRestriction(input),
                includedTypes = IncludedType.nearbyPlacesLunch(),
            )
        )
    }

    private fun createLocationRestriction(latLng: Pair<Double, Double>): LocationRestriction {
        return LocationRestriction(
                Circle(
                    center = Center(
                        latitude = latLng.first,
                        longitude = latLng.second,
                    )
                )
            )
    }
}