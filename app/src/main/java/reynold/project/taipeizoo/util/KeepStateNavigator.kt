package reynold.project.taipeizoo.util

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import java.util.*

/**
 * ref: https://juejin.im/post/5d3807375188251b2569fb62
 * Save fragment state with using Navigation component, because Navigation use replace transaction by default.
 */
@Navigator.Name("keep_state_fragment")
class KeepStateNavigator(private val context: Context, private val manager: FragmentManager, private val containerId: Int) : FragmentNavigator(
    context, manager, containerId
) {
    private val mBackStack = ArrayDeque<String>()

    override fun navigate(destination: Destination, args: Bundle?, navOptions: NavOptions?, navigatorExtras: Navigator.Extras?): NavDestination? {
        val tag = destination.id.toString()
        val label = destination.label.toString()
        val transaction = manager.beginTransaction()
        if (navOptions != null) {
            transaction.setCustomAnimations(navOptions.enterAnim, navOptions.exitAnim, navOptions.popEnterAnim, navOptions.popExitAnim)
        }
        var initialNavigate = false
        val currentFragment = manager.primaryNavigationFragment
        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }
        var fragment = manager.findFragmentByTag(tag)
        if (fragment == null) {
            val className = destination.className
            fragment = manager.fragmentFactory.instantiate(context.classLoader, className)
            fragment.arguments = args
            transaction.add(containerId, fragment, tag)
            initialNavigate = true
            Log.d(TAG, String.format("Add tag %s (%s) to mBackStack %s", tag, label, mBackStack))
            mBackStack.add(tag)
        } else {
            transaction.show(fragment)
        }
        transaction.setPrimaryNavigationFragment(fragment)
        transaction.setReorderingAllowed(true)
        transaction.commitNow()
        return if (initialNavigate) destination else null
    }

    override fun popBackStack(): Boolean {
        if (mBackStack.isEmpty()) {
            return false
        }
        Log.d(TAG, String.format("Remove tag %s from mBackStack %s", mBackStack.last, mBackStack))
        val removeTag = mBackStack.removeLast()
        return doNavigate(removeTag)
    }

    private fun doNavigate(removeTag: String): Boolean {
        val transaction = manager.beginTransaction()
        val removeFrag = manager.findFragmentByTag(removeTag)
        if (removeFrag != null) {
            transaction.remove(removeFrag)
        } else {
            return false
        }
        if (mBackStack.isEmpty()) {
            // We're already at the startDestination of the graph so there's no 'Up' to go to.
            // No previous fragment can be shown. There should be subsequent 'navigate' to show the new startDestination.
            transaction.commitNow()
            return true
        }
        val showTag = mBackStack.last
        val showFrag = manager.findFragmentByTag(showTag)
        if (showFrag != null) {
            transaction.show(showFrag)
            transaction.setPrimaryNavigationFragment(showFrag)
            transaction.setReorderingAllowed(true)
            val stateSaved = manager.isStateSaved
            if (stateSaved) {
                transaction.commitNowAllowingStateLoss()
            } else {
                transaction.commitNow()
            }
        } else {
            return false
        }
        return true
    }

    companion object {
        private const val TAG = "KeepStateNavigator"
    }
}