package com.example.challengechapter5_dianpurnamasari

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengechapter5_dianpurnamasari.model.Movie
import com.example.challengechapter5_dianpurnamasari.model.ResponseData
import com.example.challengechapter5_dianpurnamasari.model.response
import com.example.challengechapter5_dianpurnamasari.network.DataMovie
import com.example.challengechapter5_dianpurnamasari.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FilmViewModel : ViewModel() {
    lateinit var dataFilm: MutableLiveData<DataMovie>

    init {
        dataFilm = MutableLiveData()
    }

    fun callApiFilm() {
        RetrofitClient.instance.getMovie()
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