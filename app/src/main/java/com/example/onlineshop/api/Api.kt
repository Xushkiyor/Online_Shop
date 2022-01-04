package com.example.onlineshop.api


import com.example.onlineshop.model.BaseResponse
import com.example.onlineshop.model.CategoriesModel
import com.example.onlineshop.model.OfferModel
import com.example.onlineshop.model.ProductModel
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("get_offers")
    fun getOffers(): Call<BaseResponse<List<OfferModel>>>

    @GET("get_categories")
    fun getCategories(): Call<BaseResponse<List<CategoriesModel>>>

    @GET("get_top_products")
    fun getTopProduct(): Call<BaseResponse<List<ProductModel>>>
}