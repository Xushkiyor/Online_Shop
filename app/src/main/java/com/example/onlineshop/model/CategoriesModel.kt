package com.example.onlineshop.model

data class CategoriesModel(
    val id: Int,
    val title: String,
    val icon: String,
    val parent_id: String,
    var checked: Boolean = false
)
