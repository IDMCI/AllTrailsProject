package com.example.duncanclark.domain.model.params

enum class FieldMask(val value: String) {
    DISPLAY_NAME("places.displayName"),
    PLACES_NAME("places.name"),
    PLACES_RATING("places.rating"),
    PLACES_SERVES_LUNCH("places.servesLunch");

    // Used for extension function
    companion object
}

fun FieldMask.Companion.nearbyPlacesLunch(): String {
    return listOf(
        FieldMask.DISPLAY_NAME,
        FieldMask.PLACES_NAME,
        FieldMask.PLACES_RATING,
        FieldMask.PLACES_SERVES_LUNCH,
    ).joinToString(separator = ",") { it.value }
}