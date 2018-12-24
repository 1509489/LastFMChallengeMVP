package com.pixelart.lastfmchallengemvp.ui.detailscreen

import android.content.Intent
import com.pixelart.lastfmchallengemvp.base.BasePresenter
import com.pixelart.lastfmchallengemvp.base.BaseView
import com.pixelart.lastfmchallengemvp.model.Album

interface DetailContract{
    interface View:BaseView{
        fun showAlbumDetails(album: Album)
    }
    interface Presenter: BasePresenter{
        fun getAlbumDetails(intent: Intent)
    }
}