package com.example.challengechapter5_dianpurnamasari.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengechapter5_dianpurnamasari.network.DataMovie
import com.example.challengechapter5_dianpurnamasari.network.FilmApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel

class FilmViewModel @Inject constructor(private var api : FilmApiService) : ViewModel(){
    var dataFilm: MutableLiveData<DataMovie> = MutableLiveData()

    fun callApiFilm() {
        api.getMovie()
            .enqueue(object : Callback<DataMovie> {
                override fun onResponse(
                    call: Call<DataMovie>,
                    response: Response<DataMovie>
                ) {
                    if (response.isSuccessful) {
                        dataFilm.postValue(response.body())
                    }
                    else{
                        dataFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<DataMovie>, t: Throwable) {
                    dataFilm.postValue((null))
                }
            })
    }

}