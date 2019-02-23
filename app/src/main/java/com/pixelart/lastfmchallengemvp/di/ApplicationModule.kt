package com.pixelart.lastfmchallengemvp.di

import android.app.Activity
import androidx.databinding.DataBindingUtil
import com.pixelart.lastfmchallengemvp.R
import com.pixelart.lastfmchallengemvp.base.BaseActivity
import com.pixelart.lastfmchallengemvp.databinding.ActivityDetailBinding
import com.pixelart.lastfmchallengemvp.databinding.ActivityHomeBinding
import com.pixelart.lastfmchallengemvp.remote.ApiService
import com.pixelart.lastfmchallengemvp.ui.detailscreen.DetailActivity
import com.pixelart.lastfmchallengemvp.ui.detailscreen.DetailContract
import com.pixelart.lastfmchallengemvp.ui.detailscreen.DetailPresenter
import com.pixelart.lastfmchallengemvp.ui.homescreen.HomeActivity
import com.pixelart.lastfmchallengemvp.ui.homescreen.HomeContract
import com.pixelart.lastfmchallengemvp.ui.homescreen.HomePresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class ApplicationModule(private val baseActivity: BaseActivity<*>){

    @Provides
    @Singleton
    fun providesHomeContractPresenter(apiService: ApiService): HomeContract.Presenter = HomePresenter(baseActivity as HomeActivity, apiService)

    @Provides
    @Singleton
    fun providesHomeActivityBinding():ActivityHomeBinding = DataBindingUtil.setContentView(baseActivity as Activity, R.layout.activity_home)

    @Provides
    @Singleton
    fun providesDetailContractPresenter(): DetailContract.Presenter = DetailPresenter(baseActivity as DetailActivity)

    @Provides
    @Singleton
    fun providesDetailActivityBinding():ActivityDetailBinding = DataBindingUtil.setContentView(baseActivity as Activity, R.layout.activity_detail)
}