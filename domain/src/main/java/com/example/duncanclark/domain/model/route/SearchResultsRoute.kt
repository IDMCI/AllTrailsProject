package com.example.duncanclark.domain.model.route

import android.os.Parcelable
import com.example.duncanclark.domain.model.ui.PlaceId
import com.example.duncanclark.domain.model.ui.Places
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResultsRoute(
    val searchResults: Places,
    val selected: PlaceId,
) : Parcelable