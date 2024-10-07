package com.example.duncanclark.domain.model.route

data class FabState(
    var displayName: String,
    var route: String,
    var navCallBack: () -> Unit,
    var initialLoad: Boolean = true,
)