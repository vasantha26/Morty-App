package com.example.rickymortyapp.service

import com.example.rickymortyapp.model.RickAndMortyResponse
import retrofit2.Call
import retrofit2.http.GET

interface RickAndMortyApi {
    @GET("character")
    fun getCharacters(): Call<RickAndMortyResponse>
}