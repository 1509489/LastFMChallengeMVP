package com.pixelart.lastfmchallengemvp.ui.detailscreen

import android.content.Intent
import com.pixelart.lastfmchallengemvp.model.Album
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val view : DetailContract.View):
        DetailContract.Presenter{

    override fun getAlbumDetails(intent: Intent) {
        val album: Album = intent.getParcelableExtra("album")
        view.showAlbumDetails(album)
    }

    override fun onStart() {
    }

    override fun onDestroy() {

    }

}