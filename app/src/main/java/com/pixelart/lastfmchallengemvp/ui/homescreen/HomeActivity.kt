package com.pixelart.lastfmchallengemvp.ui.homescreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.espresso.idling.CountingIdlingResource
import com.pixelart.lastfmchallengemvp.R
import com.pixelart.lastfmchallengemvp.adapter.HomeAdapter
import com.pixelart.lastfmchallengemvp.base.BaseActivity
import com.pixelart.lastfmchallengemvp.common.ALBUM_INTENT_KEY
import com.pixelart.lastfmchallengemvp.databinding.ActivityHomeBinding
import com.pixelart.lastfmchallengemvp.di.ApplicationModule
import com.pixelart.lastfmchallengemvp.di.DaggerApplicationComponent
import com.pixelart.lastfmchallengemvp.model.Album
import com.pixelart.lastfmchallengemvp.ui.detailscreen.DetailActivity
import javax.inject.Inject

class HomeActivity : BaseActivity<HomeContract.Presenter>(),HomeContract.View,
    View.OnClickListener, HomeAdapter.OnItemClickedListener {


    @Inject
    lateinit var presenter: HomeContract.Presenter
    @Inject
    lateinit var binding: ActivityHomeBinding

    private lateinit var adapter: HomeAdapter
    private lateinit var albums: ArrayList<Album>

    var countingIdlingResource = CountingIdlingResource("Network_Call")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.progressBar.visibility = View.INVISIBLE

        adapter = HomeAdapter(this)
        binding.rvHome.layoutManager = LinearLayoutManager(this)
        binding.rvHome.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))
        binding.rvHome.adapter = adapter

        binding.btnSearch.setOnClickListener(this)
        albums = ArrayList()
    }

    override fun getViewPresenter(): HomeContract.Presenter = presenter

    override fun init() {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build().injectHomeActivity(this)
    }

    override fun showLoadingIndicator() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoadingIndicator() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    //Enter album name in the edit text and click search button to get list of albums
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnSearch ->{
                countingIdlingResource.increment()
                val searchKeyWord = binding.etSearch.text.toString()
                presenter.getAlbums(searchKeyWord)
                closeKeyboard()//close soft keyboard
            }
        }
    }

    override fun showAlbums(albums: List<Album>) {
        adapter.submitList(albums)
        this.albums = albums as ArrayList<Album>
        countingIdlingResource.decrement()
    }

    private fun closeKeyboard(){
        val view: View? = this.currentFocus
        if (view != null){
            val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    //Interface method to allow click interaction for the recycler view adapter
    //open detail activity and pass the selected album through intent
    override fun onItemClicked(position: Int) {
        val album  = albums[position]
        startActivity(Intent(this, DetailActivity::class.java).also {
            it.putExtra(ALBUM_INTENT_KEY, album)
        })
    }
}
