package com.example.musicapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
//Classic Tab should load:
//https://itunes.apple.com/search?term=classick&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50
//Pop Tab should load:
//https://itunes.apple.com/search?term=pop&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50
//
//Rock Tab should load:
//https://itunes.apple.com/search?term=rock&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50


interface MusicAPI {
    @GET("search?term=rock&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    fun getRock():retrofit2.Call<MusicList>
    @GET("search?term=classick&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    fun getClassic():retrofit2.Call<MusicList>
    @GET("search?term=pop&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    fun getPop():retrofit2.Call<MusicList>
    companion object{
        fun initRetrofit():MusicAPI{
            return Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MusicAPI::class.java)
        }
    }
}