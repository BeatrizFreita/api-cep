package com.example.retrofitviacep
import br.senai.sp.jandira.retrofitviacep.RetrofitService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    //RECEBE A URL DO VIACEP
    val URL = "http://10.0.2.2:8080/api/"
    val retrofitFactory = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

    fun retrofitService() : RetrofitService {
        return retrofitFactory.create(RetrofitService::class.java)
    }
}