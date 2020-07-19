package com.example.urbandictionaryapp.view

import android.view.View
import com.example.urbandictionaryapp.model.DefinitionModel

interface IMainActivity {
    fun onBind()
    fun initView()
    fun initNetwork()
    fun inputData(view: View)
    fun showToast(message: String)
    fun showProgress()
    fun dismissProgress()
    fun displayData(dataSet: MutableList<DefinitionModel>)
    fun onCheckedChanged(view: View)
    fun sortByThumbs(checked: Boolean)
}