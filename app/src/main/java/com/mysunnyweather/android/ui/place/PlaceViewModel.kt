package com.mysunnyweather.android.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.mysunnyweather.android.logic.Repository
import com.mysunnyweather.android.logic.model.Place

class PlaceViewModel : ViewModel(){
    val searchLiveData = MutableLiveData<String>()
    val placeList = ArrayList<Place>()
    val placeLiveData = searchLiveData.switchMap { query->
        Repository.searchPlaces(query)
    }

    fun searchPlace(query:String){
        searchLiveData.value = query
    }
}