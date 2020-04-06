package com.codehangouts.newsfeed.di.component

import android.app.Application
import com.codehangouts.newsfeed.CustomApplication
import com.codehangouts.newsfeed.di.builder.ActivityBuilder
import com.codehangouts.newsfeed.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent {
    fun inject(app: CustomApplication)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}