package kurt.lemond.uitesting.ui.core.matcher

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

class ChildViewMatcher(
    @IdRes private val childId: Int,
    private val childMatcher: Matcher<View>
) : BoundedMatcher<View, View>(View::class.java) {
    override fun describeTo(description: Description?) {
        childMatcher.describeTo(description)
    }

    override fun matchesSafely(view: View?): Boolean {
        val childView = view?.findViewById<View>(childId)
        return childView != null && childMatcher.matches(childView)
    }
}