package com.example.googlebooks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.googlebooks.model.presentation.BookResponse
import com.example.googlebooks.model.remote.Api
import com.example.googlebooks.model.remote.isDeviceConnected
import com.example.googlebooks.view.BookListFragment
import com.example.googlebooks.view.SearchBookFragment
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isDeviceConnected())
            supportFragmentManager.beginTransaction().replace(
                R.id.container_search,
                SearchBookFragment()
            ).commit()
        else
            showError()

    }

    fun executeRetrofit(bookTitle: String, bookType: String, maxResult: Int) {

        Api.initRetrofit().getBookByTitle(bookTitle, bookType, maxResult)
            .enqueue(object : Callback<BookResponse> {
                override fun onResponse(
                    call: Call<BookResponse>,
                    response: Response<BookResponse>
                ) {
                    if (response.isSuccessful) {
                        inflateDisplayFragment(response.body())
                    } else
                        showError()
                }

                override fun onFailure(call: Call<BookResponse>, t: Throwable) {

                }
            })
    }

    private fun showError() {
        Snackbar.make(
            findViewById(R.id.container_display),
            "No network, retry?", Snackbar.LENGTH_INDEFINITE
        ).setAction("Retry") {
            Log.d(TAG, "showError: Retry")
        }.show()
    }

    fun inflateDisplayFragment(dataSet: BookResponse?) {
        dataSet?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_display, BookListFragment.newInstance(it))
                .commit()
        }
    }
}
