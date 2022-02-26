package reynold.project.taipeizoo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import reynold.project.taipeizoo.R
import reynold.project.taipeizoo.databinding.ActivityTaipeiZooBinding

class TaipeiZooActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityTaipeiZooBinding>(this, R.layout.activity_taipei_zoo)
    }
}