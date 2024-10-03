package com.example.duncanclark.datasource.mapper

import com.example.duncanclark.datasource.model.Place
import com.example.duncanclark.domain.common.mapper.Mapper
import com.example.duncanclark.domain.model.PointsOfInterest
import javax.inject.Inject

class ApiPlacesToPlacesMapperImpl @Inject constructor(): Mapper<List<Place>, PointsOfInterest> {
    override operator fun invoke(input: List<Place>): PointsOfInterest {
        return emptyList()
    }
}