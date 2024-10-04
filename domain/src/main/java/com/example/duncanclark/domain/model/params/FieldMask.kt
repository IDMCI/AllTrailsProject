package com.example.duncanclark.domain.model.params

/**
 * Header FieldMask parameters
 */
enum class FieldMask(val value: String) {
    DISPLAY_NAME("places.displayName"),
    PLACES_NAME("places.name"),
    PLACES_RATING("places.rating"),
    PLACES_SERVES_LUNCH("places.servesLunch"),
    PRIMARY_TYPE("places.primaryType"),
    PRIMARY_TYPE_DISPLAY_NAME("places.primaryTypeDisplayName"),
    TYPE("places.types");

    // Used for extension function
    companion object
}

fun FieldMask.Companion.nearbyPlacesForLunch(): String {
    return listOf(
        FieldMask.DISPLAY_NAME,
        FieldMask.PLACES_NAME,
        FieldMask.PLACES_RATING,
        FieldMask.PLACES_SERVES_LUNCH,
        FieldMask.PRIMARY_TYPE,
        FieldMask.PRIMARY_TYPE_DISPLAY_NAME,
        FieldMask.TYPE,
    ).joinToString(separator = ",") { it.value }
}

fun FieldMask.Companion.searchTextForLunch(): String {
    return listOf(
        FieldMask.DISPLAY_NAME,
        FieldMask.PLACES_NAME,
        FieldMask.PLACES_RATING,
        FieldMask.PRIMARY_TYPE,
        FieldMask.PRIMARY_TYPE_DISPLAY_NAME,
        FieldMask.TYPE,
    ).joinToString(separator = ",") { it.value }
}