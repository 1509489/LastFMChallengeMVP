package com.pixelart.lastfmchallengemvp.ui.detailscreen

import javax.inject.Inject

class DetailPresenter @Inject constructor(private val view : DetailContract.View):
        DetailContract.Presenter{

    override fun getAlbumDetails(intentKey: String) {
        view.showAlbumDetails(intentKey)
    }

    override fun onStart() {
    }

    override fun onDestroy() {

    }

}