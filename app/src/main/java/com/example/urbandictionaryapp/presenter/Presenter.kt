package com.example.urbandictionaryapp.presenter

import com.example.urbandictionaryapp.model.DefinitionModel
import com.example.urbandictionaryapp.model.SearchModel
import com.example.urbandictionaryapp.view.IMainActivity
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Presenter {

//    private val TAG = Presenter::class.java.simpleName // "Presenter"

    private lateinit var view: IMainActivity
    private lateinit var dictionaryApi: DictionaryApi

    fun onBind(view: IMainActivity) {
        this.view = view
    }

    fun initNetwork() {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                .header("x-rapidapi-host", "mashape-community-urban-dictionary.p.rapidapi.com")
                .header("x-rapidapi-key", "fb88c54fb8msh0ba1d92b8c43faep126fe1jsn0202dae556cc")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        val client = httpClient.build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://mashape-community-urban-dictionary.p.rapidapi.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
        
        dictionaryApi = retrofit.create(DictionaryApi::class.java)
    }

    fun isEmptyInput(textInput: String): Boolean {
        return if (textInput.isEmpty()) true
        else {
            searchDictionary(textInput)
            false
        }
    }

    private fun searchDictionary(textInput: String) {
        dictionaryApi.getMeDefinitions(textInput).enqueue( // .enqueue for Asynchronous
            object : Callback<SearchModel> { // Object expression to create an object of an anonymous class
                override fun onFailure( // No communication with server; failed response
                    call: Call<SearchModel>,
                    t: Throwable) {
                    view.dismissProgress()
                    view.showToast("Failed to communicate with server.")
                }

                override fun onResponse( // Will receive response code from server
                    call: Call<SearchModel>,
                    response: Response<SearchModel>
                ) {
                    view.dismissProgress()
                    if (response.isSuccessful) { // response code is 200-299
                        if (response.body()?.list?.size == 0) {
                            view.showToast("Sorry, that term is not in Urban Dictionary.")
                        } else {
                            response.body().let {
                                view.displayData((it?.list) as MutableList<DefinitionModel>)
                            }
                        }
                    }
                }

            }
        )
    }
}