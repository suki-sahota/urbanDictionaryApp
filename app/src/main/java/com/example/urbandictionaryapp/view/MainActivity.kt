package com.example.urbandictionaryapp.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urbandictionaryapp.R
import com.example.urbandictionaryapp.model.DefinitionModel
import com.example.urbandictionaryapp.model.DictionaryAdapter
import com.example.urbandictionaryapp.presenter.Presenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), IMainActivity {

//    private val TAG = MainActivity::class.java.simpleName // "MainActivity"

    private val presenter: Presenter by lazy { Presenter() }
    private val adapter: DictionaryAdapter by lazy { DictionaryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onBind()
        initView()
        initNetwork()
        dismissProgress()
    }

    override fun onBind() {
        presenter.onBind(this)
    }

    override fun initView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter // Calls constructor in lazy block [1st use of adapter]
    }

    override fun initNetwork() {
        presenter.initNetwork()
    }

    override fun inputData(view: View) {
        showProgress()
        if (presenter.isEmptyInput(til_search.editText!!.text.toString())) {
            dismissProgress()
            showToast("Please enter a term to search!")
        }
        til_search.editText!!.setText("")
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun dismissProgress() {
        progress_bar.visibility = View.GONE
    }

    override fun displayData(dataSet: MutableList<DefinitionModel>) {
        adapter.dataSet = dataSet
    }

    override fun onCheckedChanged(view: View) {
        showProgress()
        sortByThumbs(view.tbtn_sort.isChecked)
    }

    override fun sortByThumbs(checked: Boolean) {
        if (checked) adapter.sortThumbsUp() else adapter.sortThumbsDown()
        dismissProgress()
    }
}