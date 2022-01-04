package com.example.onlineshop.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.onlineshop.R
import com.example.onlineshop.api.Api
import com.example.onlineshop.model.BaseResponse
import com.example.onlineshop.model.CategoriesModel
import com.example.onlineshop.model.OfferModel
import com.example.onlineshop.model.ProductModel
import com.example.onlineshop.view.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    var offers: List<OfferModel> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerCategory.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://osonsavdo.sd-group.uz/api/").build()
        val api = retrofit.create(Api::class.java)
        api.getOffers().enqueue(object : Callback<BaseResponse<List<OfferModel>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<OfferModel>>>,
                response: Response<BaseResponse<List<OfferModel>>>
            ) {
                if (response.isSuccessful && response.body()!!.success) {
                    offers = response.body()!!.data

                    carouselView.setImageListener { position, imageView ->
                        Glide.with(imageView)
                            .load("http://osonsavdo.sd-group.uz/images/${offers[position].image}")
                            .into(imageView)
                    }
                    carouselView.pageCount = offers.count()
                } else {
                    Toast.makeText(requireActivity(), response.body()?.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<BaseResponse<List<OfferModel>>>, t: Throwable) {
                Toast.makeText(requireActivity(), t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

        api.getCategories().enqueue(object : Callback<BaseResponse<List<CategoriesModel>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<CategoriesModel>>>,
                response: Response<BaseResponse<List<CategoriesModel>>>
            ) {
                if (response.isSuccessful && response.body()!!.success) {

                } else {
                    Toast.makeText(requireActivity(), response.body()?.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<BaseResponse<List<CategoriesModel>>>, t: Throwable) {
                Toast.makeText(requireActivity(), t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

        api.getTopProduct().enqueue(object : Callback<BaseResponse<List<ProductModel>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<ProductModel>>>,
                response: Response<BaseResponse<List<ProductModel>>>
            ) {
                if (response.isSuccessful && response.body()!!.success) {

                } else {
                    Toast.makeText(requireActivity(), response.body()?.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<BaseResponse<List<ProductModel>>>, t: Throwable) {
                Toast.makeText(requireActivity(), t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

        api.getCategories().enqueue(object : Callback<BaseResponse<List<CategoriesModel>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<CategoriesModel>>>,
                response: Response<BaseResponse<List<CategoriesModel>>>
            ) {
                if (response.isSuccessful && response.body()!!.success) {
                    recyclerCategory.adapter = CategoryAdapter(response.body()?.data ?: emptyList())
                } else {
                    Toast.makeText(requireActivity(), response.body()?.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<BaseResponse<List<CategoriesModel>>>, t: Throwable) {
                Toast.makeText(requireActivity(), t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}