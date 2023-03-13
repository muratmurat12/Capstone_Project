package com.example.cryptoapp

import CoinViewModel
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

//import kotlinx.android.synthetic.main.activity_coin_detail.*

class MainActivity : AppCompatActivity() {

     private lateinit var viewModel: CoinViewModel

//    private val compositeDisposable = CompositeDisposable()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val disposable = ApiFactory.apiService.getTopCoinsInfo()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                   Log.d("TEST_OF_LOADING_DATA", "Success: $it")
//            },{
//                   Log.d("TEST_OF_LOADING_DATA", "Failure: ${it.message}")
//            })
//        compositeDisposable.add(disposable)
//
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        compositeDisposable.dispose()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this)[CoinViewModel::class.java]
        viewModel.loadData()
        viewModel.priceList.observe(this,Observer{
            Log.d("TEST_OF_LOADING_DATA", "Success in activity: $it")
        })



    }
}