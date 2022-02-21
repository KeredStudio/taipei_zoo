package reynold.project.taipeizoo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class BaseFragment<DB : ViewDataBinding, V : BaseMvpView, P : BaseMvpPresenter<V>> : Fragment(), BaseMvpView {
    val presenter: P by lazy {
        createPresenter()
    }
    lateinit var binding: DB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this as V)
        setupViews()
        startApiCall()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    abstract fun getLayoutResId(): Int
    abstract fun createPresenter(): P
    abstract fun setupViews()
    abstract fun startApiCall()

    fun navigateFragment(action: NavDirections) {
        GlobalScope.launch(Dispatchers.Main) {
            delay(300) // waiting for clicking ripple animation for better user experience
            findNavController().navigate(action)
        }
    }

    fun navigateUp() {
        GlobalScope.launch(Dispatchers.Main) {
            delay(100)
            findNavController().navigateUp()
        }
    }
}