package com.example.googlebooks.model.remote

import com.example.googlebooks.model.presentation.BookResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("books/v1/volumes")
    fun getBookByTitle(
        @Query("q") bookTitle: String,
        @Query("printType") bookType: String = "books",
        @Query("maxResults") max: Int = 5
    ): Call<BookResponse>

    //kind of static declaration
    companion object {
        fun initRetrofit(): Api {
            return Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}

/**
 * 1 - Adding Dependency (retrofit converter-gson or moshi)
 * 2 - Create the Api interface
 * 3 - Define the http verbs (GET<POST<PUT<DELETE)
 * 4 - Create the retrofit object (using the builder)
 * 5 - Invoke the network call (enqueue, execute )
 */
