package com.example.mvvmwithretrofitapp

//https://www.youtube.com/watch?v=MCqmeNBKV9k&list=PLRKyZvuMYSIO0jLgj8g6sADnD0IBaWaw2&index=4

// NOTE: ** implementation 'androidx.appcompat:appcompat:1.4.2' **
// Make sure that the version is 1.4.2. Else, MainViewModelFactory will not work.

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmwithretrofitapp.api.QuoteService
import com.example.mvvmwithretrofitapp.api.RetrofitHelper
import com.example.mvvmwithretrofitapp.repository.QuoteRepository
import com.example.mvvmwithretrofitapp.viewmodels.MainViewModel
import com.example.mvvmwithretrofitapp.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val repository = QuoteRepository(quoteService)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {
            Log.d("Test",it.results.toString())
        })
    }
}