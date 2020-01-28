package co.shekhar.androidcodingexercise.ui

//import android.view.View
//import android.view.ViewGroup
//import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.assertion.ViewAssertions.matches
//import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
//import androidx.test.espresso.matcher.ViewMatchers.withId
//import androidx.test.filters.LargeTest
//import androidx.test.rule.ActivityTestRule
//import androidx.test.runner.AndroidJUnit4
//import co.shekhar.androidcodingexercise.R
//import org.hamcrest.Description
//import org.hamcrest.Matcher
//import org.hamcrest.Matchers.allOf
//import org.hamcrest.TypeSafeMatcher
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@LargeTest
//@RunWith(AndroidJUnit4::class)
//class MainActivityTest {
//
//    @Rule
//    @JvmField
//    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)
//
//    @Test
//    fun mainActivityTest() {
//        val progressBar = onView(
//            allOf(
//                childAtPosition(
//                    childAtPosition(
//                        withId(android.R.id.content),
//                        0
//                    ),
//                    2
//                ),
//                isDisplayed()
//            )
//        )
//        progressBar.check(matches(isDisplayed()))
//
//        val viewGroup = onView(
//            allOf(
//                withId(R.id.tool_bar),
//                childAtPosition(
//                    childAtPosition(
//                        withId(android.R.id.content),
//                        0
//                    ),
//                    0
//                ),
//                isDisplayed()
//            )
//        )
//        viewGroup.check(matches(isDisplayed()))
//    }
//
//    private fun childAtPosition(
//        parentMatcher: Matcher<View>, position: Int
//    ): Matcher<View> {
//
//        return object : TypeSafeMatcher<View>() {
//            override fun describeTo(description: Description) {
//                description.appendText("Child at position $position in parent ")
//                parentMatcher.describeTo(description)
//            }
//
//            public override fun matchesSafely(view: View): Boolean {
//                val parent = view.parent
//                return parent is ViewGroup && parentMatcher.matches(parent)
//                        && view == parent.getChildAt(position)
//            }
//        }
//    }
//}
