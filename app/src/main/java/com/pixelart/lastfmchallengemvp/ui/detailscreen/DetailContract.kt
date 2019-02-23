package com.pixelart.lastfmchallengemvp.ui.detailscreen

import com.pixelart.lastfmchallengemvp.base.BasePresenter
import com.pixelart.lastfmchallengemvp.base.BaseView

interface DetailContract{
    interface View:BaseView{
        fun showAlbumDetails(intentKey: String)
    }
    interface Presenter: BasePresenter{
        fun getAlbumDetails(intentKey: String)
    }
}