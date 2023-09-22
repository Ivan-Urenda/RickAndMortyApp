package com.ivo.rickandmortyapp.utils

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.ivo.rickandmortyapp.R
import java.time.Duration

fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_placeholder_image)
        .error(R.drawable.ic_error_image)
        .into(this)
}

fun Context.loadImageFromUrl(url: String, imageView: ImageView) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_placeholder_image)
        .error(R.drawable.ic_error_image)
        .into(imageView)
}

fun Fragment.loadImageFromUrl(url: String, imageView: ImageView) =
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_placeholder_image)
        .error(R.drawable.ic_error_image)
        .into(imageView)

fun Activity.loadImageFromUrl(url: String, imageView: ImageView) =
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_placeholder_image)
        .error(R.drawable.ic_error_image)
        .into(imageView)

fun FragmentActivity.loadImageFromUrl(url: String, imageView: ImageView) =
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_placeholder_image)
        .error(R.drawable.ic_error_image)
        .into(imageView)

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Context.showToast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.requireContext(), message, duration).show()
}