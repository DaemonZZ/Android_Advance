package com.example.mvvmprojecttemplate.extension

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

object UIHelper {

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        if(imm.isAcceptingText){
            imm.hideSoftInputFromWindow(
                activity.currentFocus?.windowToken,
                0
            );
        }
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}