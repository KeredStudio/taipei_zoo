package reynold.project.taipeizoo.ui.fragment

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.google.android.material.snackbar.Snackbar
import reynold.project.taipeizoo.R
import reynold.project.taipeizoo.databinding.FragmentAreaListBinding
import reynold.project.taipeizoo.models.AreaList
import reynold.project.taipeizoo.ui.adapter.AreaListAdapter
import reynold.project.taipeizoo.ui.base.BaseFragment
import reynold.project.taipeizoo.ui.mvp.AreaListContract
import reynold.project.taipeizoo.ui.mvp.AreaListFragmentPresenter

class AreaListFragment : BaseFragment<FragmentAreaListBinding, AreaListContract.AreaListFragmentView, AreaListContract.AreaListFragmentPresenter>(),
    AreaListContract.AreaListFragmentView {
    private lateinit var areaListAdapter: AreaListAdapter

    override fun getLayoutResId(): Int {
        return R.layout.fragment_area_list
    }

    override fun createPresenter(): AreaListContract.AreaListFragmentPresenter {
        return AreaListFragmentPresenter()
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
        binding.shimmerLayout.startShimmer()
    }

    override fun hideLoading() {
        binding.shimmerLayout.apply {
            stopShimmer()
            isVisible = false
        }
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