package kurt.lemond.uitesting.ui.core

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.common.truth.Truth.assertThat
import kurt.lemond.uitesting.ui.core.matcher.RecyclerViewItemChildMatcher
import kurt.lemond.uitesting.ui.core.matcher.RecyclerViewMatcher
import kurt.lemond.uitesting.ui.core.matcher.RecyclerViewSizeMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

open class BaseRobot {

    fun clickView(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(click())
    }

    fun clickRecyclerViewItem(
        @IdRes recyclerViewId: Int,
        itemMatcher: Matcher<View>
    ) {
        onView(
            withId(recyclerViewId)
        ).perform(
            RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                itemMatcher, click()
            )
        )
    }

    fun clickRecyclerViewItemChildAtPosition(
        @IdRes recyclerViewId: Int,
        position: Int,
        @IdRes itemChildId: Int
    ) {
        onView(
            RecyclerViewMatcher(recyclerViewId, position, itemChildId)
        ).perform(click())
    }

    fun scrollRecyclerViewToItem(
        @IdRes recyclerViewId: Int,
        itemMatcher: Matcher<View>
    ) {
        onView(
            withId(recyclerViewId)
        ).perform(
            RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                itemMatcher
            )
        )
    }

    fun scrollRecyclerViewToPosition(
        @IdRes recyclerViewId: Int,
        position: Int
    ) {
        onView(
            withId(recyclerViewId)
        ).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position)
        )
    }

    fun typeOnEditText(
        @IdRes editTextId: Int,
        text: String
    ) {
        onView(withId(editTextId)).perform(typeText(text))
    }

    fun viewIsVisible(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(isDisplayed()))
    }

    fun verifyDestination(
        navController: TestNavHostController,
        @IdRes destinationId: Int
    ) {
        assertThat(
            navController.currentDestination?.id
        ).isEqualTo(destinationId)
    }

    fun verifyRecyclerViewCount(
        @IdRes recyclerViewId: Int,
        count: Int
    ) {
        onView(
            withId(recyclerViewId)
        ).check(
            matches(RecyclerViewSizeMatcher(count))
        )
    }

    fun verifyRecyclerViewItemChildAtPosition(
        @IdRes recyclerViewId: Int,
        position: Int,
        @IdRes childId: Int,
        childMatcher: Matcher<View>
    ) {
        onView(
            withId(recyclerViewId)
        ).check(
            matches(RecyclerViewItemChildMatcher(position, childId, childMatcher))
        )
    }

    fun verifySnackBar(@StringRes message: Int) {
        onView(
            withId(com.google.android.material.R.id.snackbar_text)
        ).check(
            matches(allOf(withText(message), isDisplayed()))
        )
    }

    fun verifyText(
        @IdRes viewId: Int,
        text: String
    ) {
        onView(withId(viewId)).check(matches(withText(text)))
    }

}