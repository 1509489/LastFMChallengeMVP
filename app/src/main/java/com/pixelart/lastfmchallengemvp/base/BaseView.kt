package com.pixelart.lastfmchallengemvp.base

interface BaseView {

    fun showMessage(message: String)
    fun showError(error: String)
    fun showLoadingIndicator()
    fun hideLoadingIndicator()
}