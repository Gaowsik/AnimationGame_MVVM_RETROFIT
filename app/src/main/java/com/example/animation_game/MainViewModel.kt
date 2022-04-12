package com.example.animation_game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animation_game.network.ApiClient
import com.example.retrofitexample.network.Character
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val respository:Repository = Repository(ApiClient.apiService)) :  ViewModel()
{
    private var charactesLiveData= MutableLiveData<List<Character>?>() ////we are creting private mutable live data of type list of character //we can modify the values we need but only within this class therefore private
//Mutable live data responsible for posting updated to the activity whenever there is a change

    ////create the getter for MutableLiveData, MutableLiveData variable cannot be modified outside the viewmodel class because live data not mutable - only can view through getter
    val characterLiveData : MutableLiveData<List<Character>?>
    get()=charactesLiveData



    init {

        fetchCharacter()
    }
    private fun fetchCharacter()
    {



        //below coroutine going to run in the main thread but if you want to change it edit like this viewModelScope.launch(Dispatchers.IO) - it will run in background theread
        //coroutine dispatchers  //there are 3 types of dispatchers(1.IO - used for network call and local databases, 2.Main - Updating the UI, 3.Defalut - heavy task)
        // coroutinescope- // viewModelScope.launch - we have used to launch coroutine in a  viewmodel class if the viewmodel cancel coroutine will be canceled
        // lifecycle.launch - used to launch the coroutine in the actvity or fragment if the activity canceled coroutine will be canceled so we can prevent memory leak




/*

//to use the below method without coroutines - go to relavant classes and delete the suspend functions and in ApiService Interface change the function as below "fun fetchCharacters(@Query("page")page:String): Call<CharacterResponce>"
        val client=respository.getCharacters("1")

        client.enqueue(object :retrofit2.Callback<CharacterResponce> {
            override fun onResponse(
                call: Call<CharacterResponce>,
                response: Response<CharacterResponce>
            ) {
                if(response.isSuccessful)
                {

                    characterLiveData.postValue(response.body()?.result)


                }
            }

            override fun onFailure(call: Call<CharacterResponce>, t: Throwable) {
                Log.d("failed",""+t.message)
            }


        })

        */


        viewModelScope.launch {
            Log.d("MainViewModel",Thread.currentThread().name)
            try {

                val client=respository.getCharacters("1")
                characterLiveData.postValue(client.result)

            }

            catch (e:Exception)
            {
                Log.d("failed",""+e.message)


            }



        }

    }



}