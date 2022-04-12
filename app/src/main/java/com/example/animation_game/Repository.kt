package com.example.animation_game

//we are only fetching the data from the api with no implementation of local database but it is important viewmodel has no direct access to the data source to maintain seperation of concerns

    import com.example.animation_game.network.ApiService

    class Repository(private val apiService: ApiService)
    {

        suspend fun getCharacters(page:String)=apiService.fetchCharacters(page)

    }
