package com.bibaswan.wipro_telstra.ui


import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.bibaswan.wipro_telstra.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class DataUnavailableTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun DataUnavailableTest() {
        SystemClock.sleep(4000)

        val frameLayout = onView(
            allOf(
                withId(R.id.nav_host_fragment),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        frameLayout.check(ViewAssertions.matches(isDisplayed()))

        val frameLayout2 = onView(
            allOf(
                withId(R.id.nav_host_fragment),
                childAtPosition(
                    allOf(
                        withId(R.id.nav_host_fragment),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        frameLayout2.check(ViewAssertions.matches(isDisplayed()))

        val viewGroup = onView(
            allOf(
                withId(R.id.refreshInfo),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        viewGroup.check(ViewAssertions.matches(isDisplayed()))
        val imageView = onView(
            allOf(
                withId(R.id.no_connection_img_vw),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.refreshInfo),
                        1
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
