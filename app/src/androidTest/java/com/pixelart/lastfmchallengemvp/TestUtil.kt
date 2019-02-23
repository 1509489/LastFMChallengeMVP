package com.pixelart.lastfmchallengemvp

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Assert
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object TestUtil {

    @Throws(IOException::class)
    internal fun getStringFromFile(context: Context, filepath: String): String {
        val inputStream = context.resources.assets
            .open(filepath)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()

        bufferedReader.readLines().forEach {
            stringBuilder.append(it)
        }

        bufferedReader.close()
        inputStream.close()

        return stringBuilder.toString()
    }

    fun atPosition(position: Int, matcher: Matcher<View>): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

            override fun describeTo(description: Description) {
                description.appendText("Has Item at Position: $position ")
                matcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView
                    .findViewHolderForAdapterPosition(position)
                return viewHolder != null && matcher.matches(viewHolder.itemView)
            }
        }
    }


    class RecyclerViewItemCountAssertion(private val itemCount: Int) : ViewAssertion {


        override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val adapter = (view as RecyclerView)
                .adapter

            Assert.assertThat(
                adapter!!.itemCount,
                org.hamcrest.Matchers.`is`(itemCount)
            )
        }
    }
}