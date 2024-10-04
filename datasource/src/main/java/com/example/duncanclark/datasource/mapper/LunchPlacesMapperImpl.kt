package com.example.duncanclark.datasource.mapper

import com.example.duncanclark.datasource.model.PlacesResponse
import com.example.duncanclark.domain.common.mapper.Mapper
import com.example.duncanclark.domain.model.ui.LunchPlaces
import com.example.duncanclark.domain.model.ui.Place
import javax.inject.Inject

class LunchPlacesMapperImpl @Inject constructor(): Mapper<PlacesResponse, LunchPlaces> {
    override operator fun invoke(input: PlacesResponse): LunchPlaces {
        return input.places.map { place ->
            Place.LunchPlace(
                placeId = place.name,
                displayName = place.displayName.text,
                rating = place.rating,
                servesLunch = place.servesLunch,
                languageCode = place.displayName.languageCode,
            )
        }
    }
}