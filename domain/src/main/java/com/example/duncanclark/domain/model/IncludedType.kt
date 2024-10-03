package com.example.duncanclark.domain.model

enum class IncludedType(val value: String) {
    RESTAURANT("restaurant");

    // For extension function
    companion object
}

fun IncludedType.Companion.nearbyPlacesLunch(): String {
    return listOf(
        IncludedType.RESTAURANT,
    ).joinToString(separator = ",") { it.value }
}