package com.example.challengechapter5_dianpurnamasari

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengechapter5_dianpurnamasari.model.ResponseDataDetail
import com.example.challengechapter5_dianpurnamasari.network.DataMovie
import com.example.challengechapter5_dianpurnamasari.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel(){
    lateinit var detailFilm: MutableLiveData<ResponseDataDetail>

    init {
        detailFilm = MutableLiveData()
    }

    fun callDetailFilm(movieId: Int) {
        RetrofitClient.instance.getMovieDetail(movieId)
            .enqueue(object : Callback<ResponseDataDetail> {

                override fun onResponse(
                    call: Call<ResponseDataDetail>,
                    response: Response<ResponseDataDetail>
                ) {
                    if (response.isSuccessful) {
                        detailFilm.postValue(response.body())
                    }
                    else{
                        detailFilm.postValue(null)
                    }                }

                override fun onFailure(call: Call<ResponseDataDetail>, t: Throwable) {
                    detailFilm.postValue((null))
                }
            })
    }

}