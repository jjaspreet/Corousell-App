package com.example.carousell.common

import android.content.Context
import android.widget.Toast

fun String.toToast(context: Context): Toast {
    return Toast.makeText(context, this, Toast.LENGTH_LONG)
}