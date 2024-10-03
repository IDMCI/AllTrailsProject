package com.example.duncanclark.datasource.model

data class Place(
    val name: String,
    val rating: Float,
    val displayName: DisplayName,
    val servesLunch: Boolean,
)

data class DisplayName(
    val text: String,
    val languageCode: String,
)