package kurt.lemond.uitesting.ui.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch

fun <T> ReceiveChannel<T>.receiveWithLifecycle(
    lifecycle: Lifecycle,
    minActiveState: Lifecycle.State,
    receiver: (T) -> Unit
) {
    lifecycle.coroutineScope.launch {
        lifecycle.repeatOnLifecycle(minActiveState) {
            for (t in this@receiveWithLifecycle) {
                receiver(t)
            }
        }
    }
}
