package com.pixelart.lastfmchallengemvp.ui.homescreen

import com.pixelart.lastfmchallengemvp.base.BasePresenter
import com.pixelart.lastfmchallengemvp.base.BaseView
import com.pixelart.lastfmchallengemvp.model.Album

interface HomeContract {
    interface View:BaseView{
        fun showAlbums(albums: List<Album>)
    }

    interface Presenter: BasePresenter{
        fun getAlbums(album: String)
    }
}