package com.example.duncanclark.domain.model.params

/**
 * Header FieldMask parameters
 */
enum class FieldMask(val value: String) {
    ALLOWS_DOGS("places.allowsDogs"),
    DISPLAY_NAME("places.displayName"),
    FORMATTED_ADDRESS("places.formattedAddress"),
    OUT_DOOR_SEATING("places.outdoorSeating"),
    PLACES_NAME("places.name"),
    PLACES_RATING("places.rating"),
    PLACES_SERVES_LUNCH("places.servesLunch"),
    PRIMARY_TYPE("places.primaryType"),
    PRIMARY_TYPE_DISPLAY_NAME("places.primaryTypeDisplayName"),
    SERVES_VEGETARIAN_FOOD("places.servesVegetarianFood"),
    TAKE_OUT("places.takeout"),
    TYPE("places.types");

    // Used for extension function
    companion object
}

fun FieldMask.Companion.nearbyPlacesForLunch(): String {
    return listOf(
        FieldMask.ALLOWS_DOGS,
        FieldMask.DISPLAY_NAME,
        FieldMask.FORMATTED_ADDRESS,
        FieldMask.OUT_DOOR_SEATING,
        FieldMask.PLACES_NAME,
        FieldMask.PLACES_RATING,
        FieldMask.PLACES_SERVES_LUNCH,
        FieldMask.PRIMARY_TYPE,
        FieldMask.PRIMARY_TYPE_DISPLAY_NAME,
        FieldMask.SERVES_VEGETARIAN_FOOD,
        FieldMask.TAKE_OUT,
        FieldMask.TYPE,
    ).joinToString(separator = ",") { it.value }
}

fun FieldMask.Companion.smallerListExample(): String {
    return listOf(
        FieldMask.DISPLAY_NAME,
        FieldMask.PLACES_NAME,
        FieldMask.PLACES_RATING,
        FieldMask.PRIMARY_TYPE,
        FieldMask.PRIMARY_TYPE_DISPLAY_NAME,
        FieldMask.TYPE,
    ).joinToString(separator = ",") { it.value }
}