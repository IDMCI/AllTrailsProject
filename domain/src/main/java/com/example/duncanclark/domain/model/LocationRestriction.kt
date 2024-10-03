package com.example.duncanclark.domain.model

data class LocationRestriction(
    val circle: Circle = Circle(),
)

data class Circle (
    val center: Center = Center(),
    val radius: Double = 500.0,
)

data class Center(
    // TODO DC: Remove stubbed data
    val latitude: Double = 40.479822043320816,
    val longitude: Double = -104.89954079298904
)