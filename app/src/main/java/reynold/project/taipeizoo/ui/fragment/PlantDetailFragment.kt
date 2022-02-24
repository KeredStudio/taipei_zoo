package reynold.project.taipeizoo.ui.fragment

import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import reynold.project.taipeizoo.R
import reynold.project.taipeizoo.databinding.FragmentPlantDetailBinding
import reynold.project.taipeizoo.models.PlantDetailItem
import reynold.project.taipeizoo.ui.adapter.PlantDetailListAdapter
import reynold.project.taipeizoo.ui.base.BaseFragment
import reynold.project.taipeizoo.ui.mvp.PlantDetailContract
import reynold.project.taipeizoo.ui.mvp.PlantDetailFragmentPresenter

class PlantDetailFragment : BaseFragment<FragmentPlantDetailBinding, PlantDetailContract.PlantDetailFragmentView, PlantDetailContract.PlantDetailFragmentPresenter>(),
    PlantDetailContract.PlantDetailFragmentView {
    private val args: PlantDetailFragmentArgs by navArgs()
    private lateinit var plantDetailListAdapter: PlantDetailListAdapter

    override fun getLayoutResId(): Int {
        return R.layout.fragment_plant_detail
    }

    override fun createPresenter(): PlantDetailContract.PlantDetailFragmentPresenter {
        return PlantDetailFragmentPresenter()
    }

    override fun setupViews() {
        binding.plantDetail = args.plantDetail

        with(binding.toolbar) {
            setNavigationOnClickListener {
                navigateUp()
            }
        }

        with(binding.plantDetailList) {
            plantDetailListAdapter = PlantDetailListAdapter()
            this.adapter = plantDetailListAdapter
            val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.list_item_divider)!!)
            this.addItemDecoration(dividerItemDecoration)
        }
    }

    override fun startApiCall() {
        val plantTitleList = resources.getStringArray(R.array.plant_title_list)
        presenter.getPlantDetailList(plantTitleList, args.plantDetail)
    }

    override fun showPlantDetailList(plantDetailList: List<PlantDetailItem>) {
        plantDetailListAdapter.submitList(plantDetailList)
    }
}