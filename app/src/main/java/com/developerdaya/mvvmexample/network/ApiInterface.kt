package com.developerdaya.mvvmexample.network
import com.developerdaya.mvvmexample.model.CountryListModel
import io.reactivex.Observable
import retrofit2.http.GET
interface ApiInterface {
    @GET("66300b57-b489-4cda-99e5-ab35663323b6")
    fun getCountry(): Observable<CountryListModel>
}