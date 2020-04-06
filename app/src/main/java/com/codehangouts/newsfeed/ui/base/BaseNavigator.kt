package com.codehangouts.newsfeed.ui.base

interface BaseNavigator<T> {
    fun handleError(message: String?)
    fun setData(data: T)
}