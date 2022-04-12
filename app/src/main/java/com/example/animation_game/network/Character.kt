package com.example.retrofitexample.network

import com.squareup.moshi.Json

data class Character (
    @Json(name="name")
    val name:String,
    @Json(name="image")
    val image:String)

data class CharacterResponce(@Json(name="results") val result: List<Character>)
