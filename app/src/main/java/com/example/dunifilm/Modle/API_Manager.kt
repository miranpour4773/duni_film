package com.example.dunifilm.Modle

import android.provider.ContactsContract.RawContacts.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_Manager {
     var apiService: ApiServices

    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiServices::class.java)
    }

    fun getGenres(apiCallBack: apiCallBack<List<Genres>>) {
        apiService.getGenresList().enqueue(object : Callback<List<Genres>> {
            override fun onResponse(
                call: Call<List<Genres>>,
                response: Response<List<Genres>>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    apiCallBack.onSuccess(body)
                } else {
                    apiCallBack.onError("Response body is null or not successful")
                }
            }

            override fun onFailure(call: Call<List<Genres>>, t: Throwable) {
                apiCallBack.onError(t.message.toString())
            }
        })
    }

    interface apiCallBack<T> {
        fun onSuccess(data: T)
        fun onError(errorMessage: String)
    }
}