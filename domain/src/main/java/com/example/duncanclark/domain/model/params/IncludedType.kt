package com.example.duncanclark.domain.model.params

enum class IncludedType(val value: String) {
    RESTAURANT("restaurant");

    // For extension function
    companion object
}

fun IncludedType.Companion.nearbyPlacesForLunch(): String {
    return listOf(
        IncludedType.RESTAURANT,
    ).joinToString(separator = ",") { it.value }
}