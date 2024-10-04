package com.example.duncanclark.domain.mapper

import com.example.duncanclark.domain.common.mapper.Mapper
import com.example.duncanclark.domain.model.params.BodyParamsForSearchText
import com.example.duncanclark.domain.model.params.Center
import com.example.duncanclark.domain.model.params.Circle
import com.example.duncanclark.domain.model.params.FieldMask
import com.example.duncanclark.domain.model.params.LocationRestriction
import com.example.duncanclark.domain.model.params.ParamsForSearchTextPlaces
import com.example.duncanclark.domain.model.params.nearbyPlacesForLunch
import javax.inject.Inject

class ParamsSearchTextPlacesMapperImpl @Inject constructor()
    : Mapper<String, ParamsForSearchTextPlaces> {

    override fun invoke(input: String): ParamsForSearchTextPlaces {
        return ParamsForSearchTextPlaces(
            fieldMasks = FieldMask.nearbyPlacesForLunch(),
            bodyParams = BodyParamsForSearchText(
                textQuery = input,
                locationRestriction = createLocationRestriction(
                    Pair(40.479822043320816,-104.89954079298904)
                ),
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