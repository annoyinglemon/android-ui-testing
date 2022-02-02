package kurt.lemond.uitesting.ui.core.matcher

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import org.hamcrest.Description
import org.hamcrest.Matcher

class RecyclerViewItemChildMatcher(
    private val position: Int,
    @IdRes private val childId: Int,
    private val childMatcher: Matcher<View>
) : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

    override fun describeTo(description: Description?) {
        description?.appendText("RecyclerView's item has child that " +
                "matches [$childMatcher] at position $position")
    }

    override fun matchesSafely(recyclerView: RecyclerView?): Boolean {
        val viewHolder = recyclerView?.findViewHolderForAdapterPosition(position) ?: return false
        val matcher = if (viewHolder.itemView is ViewGroup)
            hasDescendant(ChildViewMatcher(childId, childMatcher)
        ) else childMatcher
        return matcher.matches(viewHolder.itemView)
    }

}