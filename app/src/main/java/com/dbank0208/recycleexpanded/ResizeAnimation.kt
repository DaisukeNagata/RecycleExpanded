package com.dbank0208.recycleexpanded

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

class ResizeAnimation(view: View,addHeight: Int,startHeight: Int) : Animation() {

    private  var addHeight  = 0
    private  var startHeight = 0
    private  var view: View? = null
    
    init {
        this.addHeight = addHeight
        this.startHeight = startHeight
        this.view = view
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        val newHeight = (startHeight + addHeight * interpolatedTime).toInt()
        view?.getLayoutParams()?.height = newHeight
        view?.requestLayout()
    }

    override fun willChangeBounds(): Boolean { return true }
}