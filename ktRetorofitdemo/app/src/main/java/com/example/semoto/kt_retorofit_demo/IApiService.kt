package com.example.semoto.kt_retorofit_demo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IApiService {

    @GET("users/{user}/repos")

    fun getUser(@Path("user") user: String): Call<List<Repo>>
}