package com.dbank0208.recycleexpanded

import android.animation.ObjectAnimator
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dbank0208.recycleexpanded.databinding.ActivityMainBinding
import com.dbank0208.recycleexpanded.databinding.RecyclerItemBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val list = ObservableArrayList<RecyclerItemViewModel>()
        for (i in 0..49) {
            var c = i+1
            val viewModel = RecyclerItemViewModel("Test: $i", "Test2: $c")
            list.add(viewModel)
        }
        binding.recyclerView.adapter = ListAdapter(this, list)
    }

    private class ListAdapter(private val context: Context, list: ObservableList<RecyclerItemViewModel>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
        private val list: List<RecyclerItemViewModel>

        init {
            this.list = list
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(context, parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val viewModel = getItem(position)
            if (viewModel.isExpanded()) {
                holder.binding!!.expandButton.isSelected = true
                holder.binding!!.expandableLayout.expand(true)
            } else {
                holder.binding!!.expandButton.isSelected = false
                holder.binding!!.expandableLayout.collapse(false)
            }

            viewModel.setOnClickListener(View.OnClickListener()  {
                if (viewModel.isExpanded()) {
                    holder.binding!!.expandButton.isSelected = false
                    holder.binding!!.expandableLayout.collapse(false)
                } else {
                    holder.binding!!.expandButton.isSelected = true
                    holder.binding!!.expandableLayout.expand(true)
                }
                //TODO:　To clarify
                //val anim = ObjectAnimator.ofFloat(holder.binding!!.expandArrow, "rotation",0.toFloat(),180.toFloat())
                //anim.setDuration(150)
                //anim.start()
                viewModel.setExpanded(!viewModel.isExpanded())
            })

            viewModel.setExpandButtonText("Test"+ position.toString())

            holder.binding!!.setViewModel(viewModel);
            holder.binding!!.executePendingBindings();
        }

        override fun getItemCount(): Int {
            return list.size
        }

        fun getItem(position: Int): RecyclerItemViewModel {
            return list[position]
        }

        inner class ViewHolder(context: Context, parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)) {

            internal var binding: RecyclerItemBinding? = null

            init {
                binding = DataBindingUtil.bind(itemView)
            }
        }
    }
}
