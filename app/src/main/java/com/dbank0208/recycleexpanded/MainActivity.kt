package com.dbank0208.recycleexpanded


import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.RelativeLayout

import com.dbank0208.recycleexpanded.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.recycler_item.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val list = ObservableArrayList<RecyclerItemViewModel>()
        for (i in 0..49) {
            var c = i + 1
            val viewModel = RecyclerItemViewModel("Test: $i", "Test2: $c")
            list.add(viewModel)
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = ListAdapter(this, list)
    }
}