package com.dbank0208.recycleexpanded

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dbank0208.recycleexpanded.databinding.RecyclerItemBinding

class ListAdapter( private val context: Context, list: ObservableList<RecyclerItemViewModel>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private val list: List<RecyclerItemViewModel>

    init { this.list = list }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { return ViewHolder(context, parent) }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewModel = getItem(position)

        var originalHeight = 200
        var collapseAnimation = ResizeAnimation(holder.binding!!.expandableLayout, -originalHeight, originalHeight)
        var expandAnimation = ResizeAnimation(holder.binding!!.expandableLayout, originalHeight, 0)

        if (viewModel.isExpanded()) {
            holder.binding!!.expandButton.isSelected = true
            holder.binding!!.expandableLayout.startAnimation(expandAnimation)
        } else {
            holder.binding!!.expandButton.isSelected = false
            holder.binding!!.expandableLayout.startAnimation(collapseAnimation)
        }

        viewModel.setOnClickListener(View.OnClickListener  {
            if (viewModel.isExpanded()) {
                holder.binding!!.expandButton.isSelected = false
                holder.binding!!.expandableLayout.startAnimation(collapseAnimation)
            } else {
                holder.binding!!.expandButton.isSelected = true
                holder.binding!!.expandableLayout.startAnimation(expandAnimation)

            }
            viewModel.setExpanded(!viewModel.isExpanded())
        })

        viewModel.setExpandButtonText("Test"+ position.toString())

        holder.binding!!.setViewModel(viewModel)
        holder.binding!!.executePendingBindings()
    }

    override fun getItemCount(): Int { return list.size }

    fun getItem(position: Int): RecyclerItemViewModel {  return list[position] }

    inner class ViewHolder(context: Context, parent: ViewGroup) :
            RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)) {

        var binding: RecyclerItemBinding? = null

        init { binding = DataBindingUtil.bind(itemView) }
    }
}
