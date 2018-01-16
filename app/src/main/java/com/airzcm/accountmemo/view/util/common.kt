package com.airzcm.accountmemo.view.util

import android.content.Context
import android.widget.Toast

/**
 * @author airzcm on 2018/1/16.
 */
fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()