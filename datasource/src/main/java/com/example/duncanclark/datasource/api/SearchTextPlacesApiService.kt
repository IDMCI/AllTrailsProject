package com.example.duncanclark.datasource.api

import com.example.duncanclark.datasource.model.PlacesResponse
import com.example.duncanclark.domain.model.params.BodyParamsForSearchText
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface SearchTextPlacesApiService {
    @POST("v1/places:searchText")
    @Headers("Content-Type: application/json")
    fun searchText(
        @Header("X-Goog-Api-key") apiKey: String,
        @Header("X-Goog-FieldMask") fieldMask: String,
        @Body bodyParams: BodyParamsForSearchText,
    ): Call<PlacesResponse>
}