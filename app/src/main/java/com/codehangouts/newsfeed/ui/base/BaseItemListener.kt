package com.codehangouts.newsfeed.ui.base

interface BaseItemListener<T> {
    fun onItemClick(item: T)
}