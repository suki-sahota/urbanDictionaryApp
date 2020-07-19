package com.example.urbandictionaryapp.presenter

import com.example.urbandictionaryapp.model.SearchModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DictionaryApi {
    // Base Url https://mashape-community-urban-dictionary.p.rapidapi.com
    // End Point /define?
    @GET("/define?")
    fun getMeDefinitions(@Query("term") term: String): Call<SearchModel> // Exposes Asynchronous execution
}