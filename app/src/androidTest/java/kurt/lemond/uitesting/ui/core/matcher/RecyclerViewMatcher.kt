package kurt.lemond.uitesting.ui.core.matcher

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class RecyclerViewMatcher(
    @IdRes private val recyclerViewId: Int,
    private val position: Int,
    @IdRes private val targetViewId: Int
) : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) {
        description?.appendText("RecyclerView$recyclerViewId " +
                "has item at position $position with a child $targetViewId")
    }

    override fun matchesSafely(view: View): Boolean {
        val recyclerView = view.rootView?.findViewById<RecyclerView?>(recyclerViewId)
        requireNotNull(recyclerView) {
            "Can not find view with id $recyclerViewId"
        }

        val childView = recyclerView.findViewHolderForAdapterPosition(position)?.itemView
        requireNotNull(childView) {
            "Can not find child view at position: $position"
        }

        return if (targetViewId == View.NO_ID) {
            view == childView
        } else {
            view == childView.findViewById(targetViewId)
        }
    }
}