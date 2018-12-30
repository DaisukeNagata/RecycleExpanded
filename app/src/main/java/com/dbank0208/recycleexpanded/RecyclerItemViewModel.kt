package com.dbank0208.recycleexpanded

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View


class RecyclerItemViewModel(text1: String, text2: String) : BaseObservable() {


    var text11: String = ""
    var text22: String = ""
    lateinit var onClickListenerr: View.OnClickListener
    private var expanded = false
    var expandButtonTextt: String = ""

    init {
       this.text11 = text1
      this.text22 = text2
    }
    public fun getText1(): String {
        return text11
    }

    public fun getText2(): String {
        return text22
    }

    @Bindable
    fun getExpandButtonText(): String {
        return expandButtonTextt
    }

    fun setExpandButtonText(expandButtonText: String) {
        this.expandButtonTextt = expandButtonText
    }

    fun onClickExpandButton(view: View) {
        onClickListenerr.onClick(view)
    }

    fun setOnClickListener(onClickListener: View.OnClickListener) {
        this.onClickListenerr = onClickListener
    }

    fun setExpanded(expanded: Boolean) {
        this.expanded = expanded
    }

    fun isExpanded(): Boolean {
        return this.expanded
    }
}
