package com.example.mvvmwithretrofitapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmwithretrofitapp.api.QuoteService
import com.example.mvvmwithretrofitapp.models.QuoteList
import java.lang.reflect.Array.get

class QuoteRepository(private val quoteService: QuoteService) {

    private val quotesLiveData = MutableLiveData<QuoteList>()

    val quotes : LiveData<QuoteList>
    get() = quotesLiveData

    suspend fun getQuotes(page:Int){
        val result = quoteService.getQuotes(page)
        if(result!=null && result.body()!=null){
            quotesLiveData.postValue(result.body())
        }
    }
}