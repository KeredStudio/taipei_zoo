package reynold.project.taipeizoo.ui.fragment

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.google.android.material.snackbar.Snackbar
import reynold.project.taipeizoo.R
import reynold.project.taipeizoo.databinding.FragmentAreaListBinding
import reynold.project.taipeizoo.models.AreaList
import reynold.project.taipeizoo.ui.adapter.AreaListAdapter
import reynold.project.taipeizoo.ui.base.BaseFragment
import reynold.project.taipeizoo.ui.mvp.AreaListFragmentPresenter
import reynold.project.taipeizoo.ui.mvp.AreaListFragmentPresenterImpl
import reynold.project.taipeizoo.ui.mvp.AreaListFragmentView

class AreaListFragment : BaseFragment<FragmentAreaListBinding, AreaListFragmentView, AreaListFragmentPresenter>(), AreaListFragmentView {
    private lateinit var areaListAdapter: AreaListAdapter

    override fun getLayoutResId(): Int {
        return R.layout.fragment_area_list
    }

    override fun createPresenter(): AreaListFragmentPresenter {
        return AreaListFragmentPresenterImpl()
    }

    override fun setupViews() {
        with(binding.toolbar) {
            (requireActivity() as AppCompatActivity).setSupportActionBar(this)
        }
        with(binding.areaListRecyclerView) {
            areaListAdapter = AreaListAdapter(presenter)
            this.adapter = areaListAdapter
            val dividerItemDecoration = DividerItemDecoration(requireContext(), VERTICAL)
            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.list_item_divider)!!)
            this.addItemDecoration(dividerItemDecoration)
        }
    }

    override fun startApiCall() {
        presenter.getAreaList()
    }

    override fun showLoading() {
        binding.swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        binding.swipeRefreshLayout.isRefreshing = false
        binding.swipeRefreshLayout.isEnabled = false
    }

    override fun showAreaList(areaList: List<AreaList.Result.Detail>) {
        areaListAdapter.submitList(areaList)
    }

    override fun navigateToAreaDetailFragment(areaDetail: AreaList.Result.Detail) {
        val action = AreaListFragmentDirections.actionAreaListFragmentToAreaDetailFragment(areaDetail)
        navigateFragment(action)
    }

    override fun onApiFailure() {
        Snackbar.make(binding.coordinatorLayout, R.string.api_failure, Snackbar.LENGTH_LONG).show()
    }
}