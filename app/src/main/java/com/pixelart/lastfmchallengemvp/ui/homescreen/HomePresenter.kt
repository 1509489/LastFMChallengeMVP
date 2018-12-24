package com.pixelart.lastfmchallengemvp.ui.homescreen

import com.pixelart.lastfmchallengemvp.common.*
import com.pixelart.lastfmchallengemvp.model.ApiResponse
import com.pixelart.lastfmchallengemvp.remote.ApiService
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomePresenter @Inject constructor(private val view: HomeContract.View, private val apiService: ApiService):
        HomeContract.Presenter{

    override fun getAlbums(album: String) {
        apiService.getAlbums(METHOD, album, PAGE, LIMIT, API_KEY, FORMAT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ApiResponse>{
                override fun onSuccess(t: ApiResponse) {
                    view.showAlbums(t.results.albummatches.album)
                    view.hideLoadingIndicator()
                }

                override fun onSubscribe(d: Disposable) {
                    view.showLoadingIndicator()
                }

                override fun onError(e: Throwable) {
                    view.showError("Fetching data failed")
                }

            })
    }

    override fun onStart() {

    }

    override fun onDestroy() {

    }

}