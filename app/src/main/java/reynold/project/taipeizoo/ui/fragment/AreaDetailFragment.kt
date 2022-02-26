package reynold.project.taipeizoo.ui.fragment

import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import reynold.project.taipeizoo.R
import reynold.project.taipeizoo.databinding.FragmentAreaDetailBinding
import reynold.project.taipeizoo.models.PlantList
import reynold.project.taipeizoo.ui.adapter.PlantListAdapter
import reynold.project.taipeizoo.ui.base.BaseFragment
import reynold.project.taipeizoo.ui.mvp.AreaDetailContract
import reynold.project.taipeizoo.ui.mvp.AreaDetailFragmentPresenter
import reynold.project.taipeizoo.util.getSavedStateLiveData
import reynold.project.taipeizoo.util.navigateFragment
import reynold.project.taipeizoo.util.navigateUp

class AreaDetailFragment : BaseFragment<FragmentAreaDetailBinding, AreaDetailContract.AreaDetailFragmentView, AreaDetailContract.AreaDetailFragmentPresenter>(),
    AreaDetailContract.AreaDetailFragmentView {
    private val args: AreaDetailFragmentArgs by navArgs()
    private lateinit var plantListAdapter: PlantListAdapter

    override fun getLayoutResId(): Int {
        return R.layout.fragment_area_detail
    }

    override fun createPresenter(): AreaDetailContract.AreaDetailFragmentPresenter {
        return AreaDetailFragmentPresenter()
    }

    override fun setupViews() {
        binding.areaDetail = args.areaDetail
        getSavedStateLiveData<Boolean>("expandAppLayout")?.observe(viewLifecycleOwner, {
            binding.appBarLayout.setExpanded(it)
        })

        with(binding.plantListRecyclerView) {
            plantListAdapter = PlantListAdapter(presenter)
            this.adapter = plantListAdapter
            val dividerItemDecoration = DividerItemDecoration(requireContext(), androidx.recyclerview.widget.DividerItemDecoration.VERTICAL)
            dividerItemDecoration.setDrawable(androidx.core.content.ContextCompat.getDrawable(requireContext(), reynold.project.taipeizoo.R.drawable.list_item_divider)!!)
            this.addItemDecoration(dividerItemDecoration)
        }

        with(binding.toolbar) {
            setNavigationOnClickListener {
                navigateUp()
            }
        }
    }

    override fun startApiCall() {
        presenter.getPlantList(args.areaDetail)
    }

    override fun showLoading() {
        binding.shimmerLayout.apply {
            startShimmer()
            isVisible = true
        }
    }

    override fun hideLoading() {
        binding.shimmerLayout.apply {
            stopShimmer()
            isVisible = false
        }
    }

    override fun showPlantList(plantList: List<PlantList.Result.Detail>) {
        binding.plantDetailTitle.apply {
            isVisible = true
            text = getString(
                if (plantList.isEmpty()) {
                    R.string.plant_empty_message
                } else {
                    R.string.plant_list_title
                }
            )
        }
        plantListAdapter.submitList(plantList)
    }

    override fun navigateToPlantDetailFragment(plantDetail: PlantList.Result.Detail) {
        val action = AreaDetailFragmentDirections.actionAreaDetailFragmentToPlantDetailFragment(plantDetail)
        navigateFragment(action)
    }

    override fun onApiFailure() {

    }
}