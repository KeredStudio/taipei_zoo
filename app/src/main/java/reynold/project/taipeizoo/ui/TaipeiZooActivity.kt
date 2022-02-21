package reynold.project.taipeizoo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.findNavController
import reynold.project.taipeizoo.R
import reynold.project.taipeizoo.databinding.ActivityTaipeiZooBinding
import reynold.project.taipeizoo.util.KeepStateNavigator

class TaipeiZooActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView<ActivityTaipeiZooBinding>(this, R.layout.activity_taipei_zoo).apply {
            // setup custom navigator
            val navController = findNavController(R.id.fragment_container_view)
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)!!
            val keepStateNavigator = KeepStateNavigator(this@TaipeiZooActivity, navHostFragment.childFragmentManager, R.id.fragment_container_view)
            navController.navigatorProvider.addNavigator(keepStateNavigator)
            navController.setGraph(R.navigation.nav_taipei_zoo)
        }
    }
}