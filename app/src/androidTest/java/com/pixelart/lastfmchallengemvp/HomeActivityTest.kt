package com.pixelart.lastfmchallengemvp

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.pixelart.lastfmchallengemvp.adapter.HomeAdapter
import com.pixelart.lastfmchallengemvp.common.BASE_URL
import com.pixelart.lastfmchallengemvp.ui.homescreen.HomeActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class HomeActivityTest{
    private val FILE_NAME = "api_success.json"
    private val searchWord = "cather 3"

    private val activityTestRule  = ActivityTestRule<HomeActivity>(HomeActivity::class.java)
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup(){
        mockWebServer = MockWebServer()
        try {

            mockWebServer.start()
            mockWebServer.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(TestUtil.getStringFromFile(InstrumentationRegistry.getInstrumentation().context, FILE_NAME)))
        }catch (e: IOException) {
            e.printStackTrace()
        }

        BASE_URL = mockWebServer.url("/").toString()
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun testDataFetchingSuccess(){
        val countingIdlingResource: CountingIdlingResource = activityTestRule.activity.countingIdlingResource
        IdlingRegistry.getInstance().register(countingIdlingResource)

        Espresso.onView(ViewMatchers.withId(R.id.etSearch)).perform(ViewActions.typeText(searchWord), ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.btnSearch)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.rvHome)).check(TestUtil.RecyclerViewItemCountAssertion(3))

        Espresso.onView(ViewMatchers.withId(R.id.rvHome)).check(ViewAssertions.matches(
            TestUtil.atPosition(1, ViewMatchers.hasDescendant(
                        ViewMatchers.withText("Tha Cather 3")))))

        Espresso.onView(ViewMatchers.withId(R.id.rvHome)).perform(
            RecyclerViewActions.actionOnItemAtPosition<HomeAdapter.ViewHolder>(1, ViewActions.click()))
    }
}