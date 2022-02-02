package kurt.lemond.uitesting.ui.core.matcher

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

class RecyclerViewSizeMatcher(private val itemCount: Int):
    BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
    override fun describeTo(description: Description?) {
        val itemStr = if (itemCount == 1) "item" else "items"
        description?.appendText("RecyclerView contains $itemCount $itemStr")
    }

    override fun matchesSafely(recyclerView: RecyclerView?): Boolean {
        return recyclerView?.adapter?.itemCount == itemCount
    }
}