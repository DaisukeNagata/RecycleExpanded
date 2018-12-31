package com.dbank0208.recycleexpanded

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View

class RecyclerItemViewModel(text1: String, text2: String) : BaseObservable() {

    private var expanded = false
    private var text1: String = ""
    private var text2: String = ""
    private var expandButtonText: String = ""
    private lateinit var onClickListener: View.OnClickListener

    init {
       this.text1 = text1
       this.text2 = text2
    }


    public fun getText1(): String { return text1 }

    public fun getText2(): String { return text2 }

    public fun getExpandButtonText(): String { return expandButtonText }

    fun isExpanded(): Boolean { return this.expanded }

    fun setExpanded(expanded: Boolean) {  this.expanded = expanded }

    fun onClickExpandButton(view: View) { onClickListener.onClick(view) }

    fun setExpandButtonText(expandButtonText: String) { this.expandButtonText = expandButtonText }

    fun setOnClickListener(onClickListener: View.OnClickListener) { this.onClickListener = onClickListener }

}