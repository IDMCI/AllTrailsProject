package com.example.duncanclark.alltrailsproject.ui.model

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.json.Json

//object CustomNavType {
//    val SearchResultType = object: NavType<SearchResult>(
//        isNullableAllowed = false
//    ) {
//        override fun get(bundle: Bundle, key: String): SearchResult? {
//            return Json.decodeFromString(bundle.getString(key) ?: return null)
//        }
//
//        override fun parseValue(value: String): SearchResult {
//            return Json.decodeFromString(SearchResult.ser)
//        }
//
//        override fun put(bundle: Bundle, key: String, value: SearchResult){
//            return bundle.putParcelable(key, value)
//        }
//    }
//}