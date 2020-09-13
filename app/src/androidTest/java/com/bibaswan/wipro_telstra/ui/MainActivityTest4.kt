package com.bibaswan.wipro_telstra.ui


import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
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
class MainActivityTest4 {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest4() {
        SystemClock.sleep(6000)

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
        frameLayout.check(matches(isDisplayed()))

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
        frameLayout2.check(matches(isDisplayed()))

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
        viewGroup.check(matches(isDisplayed()))
        SystemClock.sleep(3000)

        val recyclerView = onView(
            allOf(
                withId(R.id.infos_rv),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.refreshInfo),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        recyclerView.check(matches(isDisplayed()))

        val frameLayout3 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.infos_rv),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        frameLayout3.check(matches(isDisplayed()))

        val viewGroup2 = onView(
            allOf(
                childAtPosition(
                    childAtPosition(
                        withId(R.id.infos_rv),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        viewGroup2.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withId(R.id.title), withText("Beavers"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Beavers")))

        val textView2 = onView(
            allOf(
                withId(R.id.description),
                withText("Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony")))
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
