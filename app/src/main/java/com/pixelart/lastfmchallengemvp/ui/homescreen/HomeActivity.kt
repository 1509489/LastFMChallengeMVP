package com.pixelart.lastfmchallengemvp.ui.homescreen

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pixelart.lastfmchallengemvp.R
import com.pixelart.lastfmchallengemvp.adapter.HomeAdapter
import com.pixelart.lastfmchallengemvp.base.BaseActivity
import com.pixelart.lastfmchallengemvp.databinding.ActivityHomeBinding
import com.pixelart.lastfmchallengemvp.di.ApplicationModule
import com.pixelart.lastfmchallengemvp.di.DaggerApplicationComponent
import com.pixelart.lastfmchallengemvp.di.NetworkModule
import com.pixelart.lastfmchallengemvp.model.Album
import javax.inject.Inject

class HomeActivity : BaseActivity<HomeContract.Presenter>(),HomeContract.View,
    View.OnClickListener, HomeAdapter.OnItemClickedListener {


    @Inject
    lateinit var presenter: HomeContract.Presenter
    @Inject
    lateinit var binding: ActivityHomeBinding

    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.progressBar.visibility = View.INVISIBLE

        adapter = HomeAdapter(this)
        binding.rvHome.layoutManager = LinearLayoutManager(this)
        binding.rvHome.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))
        binding.rvHome.adapter = adapter

        binding.btnSearch.setOnClickListener(this)

    }

    override fun getViewPresenter(): HomeContract.Presenter = presenter

    override fun init() {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .build().injectHomeActivity(this)
    }

    override fun showLoadingIndicator() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoadingIndicator() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun showAlbums(albums: List<Album>) {
        adapter.submitList(albums)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnSearch ->{
                val searchKeyWord = binding.etSearch.text.toString()
                presenter.getAlbums(searchKeyWord)
                closeKeyboard()
            }
        }
    }

    private fun closeKeyboard(){
        val view: View = this.currentFocus
        if (view != null){
            val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onItemClicked(position: Int) {
        Toast.makeText(this, "$position", Toast.LENGTH_SHORT).show()
    }
}
