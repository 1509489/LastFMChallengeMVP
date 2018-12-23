package com.pixelart.lastfmchallengemvp.di

import com.pixelart.lastfmchallengemvp.ui.homescreen.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun injectHomeActivity(homeActivity: HomeActivity)
}