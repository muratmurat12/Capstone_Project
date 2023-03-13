package com.example.cryptoapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.cryptoapp.database.AppDatabase
import io.reactivex.disposables.CompositeDisposable

class CoinViewModel(application: Application):AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriceInfoDao().getPriceList()

    fun loadData(){

    }

}