package com.example.duncanclark.datasource.api

import com.example.duncanclark.datasource.model.Place
import retrofit2.Call
import retrofit2.http.GET

interface NearbyPlacesApiService {
    @GET("v1/places:searchNearby")
    fun searchNearbyPlaces(): Call<List<Place>>
}