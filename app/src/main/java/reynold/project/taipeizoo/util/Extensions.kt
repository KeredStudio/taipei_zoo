package reynold.project.taipeizoo.util

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

inline fun <T : ViewDataBinding> T.executeAfter(block: T.() -> Unit) {
    block()
    executePendingBindings()
}

internal fun View.setSafeOnClickListener(interval: Int = 1000, onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener(interval) {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

internal fun Fragment.navigateFragment(action: NavDirections) {
    findNavController().navigate(action)
}

internal fun Fragment.navigateUp() {
    findNavController().navigateUp()
}

internal fun <T> Fragment.saveDataToPreviousFragment(key: String, value: T) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, value)
}

internal fun <T> Fragment.getSavedStateLiveData(key: String): MutableLiveData<T>? {
    return findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData(key)
}