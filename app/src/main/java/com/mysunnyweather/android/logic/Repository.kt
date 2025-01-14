package com.mysunnyweather.android.logic

import androidx.lifecycle.liveData
import com.mysunnyweather.android.logic.network.MySunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers

object Repository {
    fun searchPlaces(query:String) = liveData(Dispatchers.IO){
        val result = try {
            val response = MySunnyWeatherNetwork.searchPlaces(query)
            if(response.status == "ok"){
               val places = response.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${response.status}"))
            }
        }catch (e:Exception){
            Result.failure(e)
        }
        emit(result)
    }
}