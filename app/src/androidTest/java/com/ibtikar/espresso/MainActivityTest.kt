package com.ibtikar.espresso

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun greet() {
        val greetingView = onView(withId(R.id.greetingView))
        val button = onView(withId(R.id.greet_button))

        greetingView.check(matches(withText("")))
        button.check(matches(isEnabled()))

//        button.perform(click()).check(matches(not(isEnabled())))

//        greetingView.check(matches(withText("hello world")))
    }


    @Test
    fun greet2() {
        val greetingView = onView(withId(R.id.greetingView))
        val button = onView(withId(R.id.greet_button))

        button.perform(click())
                .check(matches(not(isEnabled())))

        greetingView.check(matches(withText("hello world")))
    }

    @Test
    fun testRecyclerVieWPosition() {
        onView(withId(R.id.rv))
                .check(matches(atPosition(0, hasDescendant(withText("one")))));
    }

    fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> {
        checkNotNull(itemMatcher)
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                        ?: // has no item on such position
                        return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }

}