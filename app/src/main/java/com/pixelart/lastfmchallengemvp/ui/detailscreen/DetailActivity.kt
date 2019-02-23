package com.pixelart.lastfmchallengemvp.ui.detailscreen

import android.content.Intent
import android.net.Uri
import android.view.View
import com.pixelart.lastfmchallengemvp.R
import com.pixelart.lastfmchallengemvp.base.BaseActivity
import com.pixelart.lastfmchallengemvp.common.ALBUM_INTENT_KEY
import com.pixelart.lastfmchallengemvp.common.GlideApp
import com.pixelart.lastfmchallengemvp.databinding.ActivityDetailBinding
import com.pixelart.lastfmchallengemvp.di.ApplicationModule
import com.pixelart.lastfmchallengemvp.di.DaggerApplicationComponent
import com.pixelart.lastfmchallengemvp.model.Album
import javax.inject.Inject

class DetailActivity : BaseActivity<DetailContract.Presenter>(), DetailContract.View{

    @Inject
    lateinit var presenter: DetailContract.Presenter
    @Inject
    lateinit var binder: ActivityDetailBinding

    private var url = ""

    override fun onStart() {
        super.onStart()
        presenter.getAlbumDetails(ALBUM_INTENT_KEY)
    }

    override fun getViewPresenter(): DetailContract.Presenter = presenter

    override fun init() {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build().injectDetailActivity(this)
    }

    override fun showLoadingIndicator() {

    }

    override fun hideLoadingIndicator() {

    }

    //Show the details of the album
    override fun showAlbumDetails(intentKey: String) {
        val album = intent.getParcelableExtra<Album>(intentKey)
        binder.apply {
            this.tvAlbumName.text = album.name
            this.tvArtist.text = album.artist

            //Display album art
            GlideApp.with(this@DetailActivity)
                .load(album.image[2].text)
                .placeholder(R.drawable.placeholder_albumart)
                .error(R.drawable.placeholder_albumart)
                .override(380, 240)
                .into(this.ivAlbumArt)
        }
        url = album.url
    }

    //Open album url to last fm in a browser
    fun onClick(v: View?) {
        when(v?.id){
            R.id.btnListen ->{
                val uri = Uri.parse(url)
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
    }
}
