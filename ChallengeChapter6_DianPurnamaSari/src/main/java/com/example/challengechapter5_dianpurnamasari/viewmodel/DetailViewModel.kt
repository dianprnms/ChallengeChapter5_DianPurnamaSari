package com.example.challengechapter5_dianpurnamasari.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengechapter5_dianpurnamasari.model.ResponseDataDetail
import com.example.challengechapter5_dianpurnamasari.network.FilmApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private var api : FilmApiService) : ViewModel(){
    var detailFilm: MutableLiveData<ResponseDataDetail> = MutableLiveData()

    fun callDetailFilm(movieId: Int) {
        api.getMovieDetail(movieId)
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