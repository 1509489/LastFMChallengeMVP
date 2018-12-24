package com.pixelart.lastfmchallengemvp

import com.pixelart.lastfmchallengemvp.common.*
import com.pixelart.lastfmchallengemvp.model.*
import com.pixelart.lastfmchallengemvp.remote.ApiService
import com.pixelart.lastfmchallengemvp.ui.homescreen.HomeContract
import com.pixelart.lastfmchallengemvp.ui.homescreen.HomePresenter
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import java.util.concurrent.Executor

@RunWith(MockitoJUnitRunner::class)
class TestHomePresenter{
    private val ALBUM = "cather 3"

    private lateinit var presenter: HomePresenter
    private lateinit var apiResponse: ApiResponse

    @Mock private lateinit var view: HomeContract.View
    @Mock private lateinit var apiService: ApiService

    companion object {
        @BeforeClass
        @JvmStatic
        fun setupSchedulers(){
            val scheduler = object : Scheduler() {
                override fun createWorker(): Scheduler.Worker {
                    return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
                }
            }

            RxJavaPlugins.setInitIoSchedulerHandler { scheduler }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }
        }
    }

    @Before
    fun setup(){
        presenter = HomePresenter(view, apiService)

        apiResponse = ApiResponse(Results(
            opensearchQuery = OpensearchQuery("", "", "", ""),
            opensearchTotalResults = "", opensearchItemsPerPage = "", opensearchStartIndex = "",
            albummatches = Albummatches(Collections.emptyList()), attr = Attr("")
        ))
    }

    @Test
    fun testApiSuccess(){
        Mockito.`when`(apiService.getAlbums(METHOD, ALBUM, 1, 30, API_KEY, FORMAT))
            .thenReturn(Single.just(apiResponse))

        presenter.getAlbums(ALBUM)
        Mockito.verify(view).showAlbums(Mockito.anyList())
    }

    @Test
    fun testApiFail(){
        Mockito.`when`(apiService.getAlbums(METHOD, ALBUM, 1, 30, API_KEY, FORMAT))
            .thenReturn(Single.error(Throwable()))
        presenter.getAlbums(ALBUM)
        Mockito.verify(view).showError("Fetching data failed")
    }
}