package com.example.animation_game.network

import com.example.retrofitexample.network.CharacterResponce
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient  //object keyword use this object directly into our programme no need create object
{
    /**
    https://rickandmortyapi.com/api/character/?page=1
    The retrofit builder will need a base url so we extract that
    from our link and create the base url variable of type String
     */


    private val BASE_URL="https://rickandmortyapi.com/api/"


    /***
     * next we create a variable for the moshi builder,
     * adding a converter to it
     */

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


    /***
     * Then we create an instance of Retrofit by lazy so it can initialized only when it is needed
     * pass the base url and the moshi variables created above
     *
     */
//this will create the url then used to get the data from the api
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }




    //it allows us to use the interface Apiservice and get the characters
    val apiService by lazy {
        retrofit.create(ApiService::class.java)
    }


}


/***
 * below the object class let us crate an interface to define how Rerofit talks to the service using the get method
 */

interface ApiService{
    /***
     * then we create a fetchCharacters mehod
     * annotate with @Get passing the character path just like in out api link
     * above to tell the server send us those characters
     */

    @GET("character")
    suspend  fun fetchCharacters(@Query("page")page:String): CharacterResponce









}