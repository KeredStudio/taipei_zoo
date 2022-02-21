package reynold.project.taipeizoo.ui.fragment

import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import reynold.project.taipeizoo.R
import reynold.project.taipeizoo.databinding.FragmentPlantDetailBinding
import reynold.project.taipeizoo.models.PlantDetailItem
import reynold.project.taipeizoo.ui.adapter.PlantDetailListAdapter
import reynold.project.taipeizoo.ui.base.BaseFragment
import reynold.project.taipeizoo.ui.mvp.PlantDetailFragmentPresenter
import reynold.project.taipeizoo.ui.mvp.PlantDetailFragmentPresenterImpl
import reynold.project.taipeizoo.ui.mvp.PlantDetailFragmentView
import reynold.project.taipeizoo.util.PlantDetailTitle

class PlantDetailFragment : BaseFragment<FragmentPlantDetailBinding, PlantDetailFragmentView, PlantDetailFragmentPresenter>(), PlantDetailFragmentView {
    private val args: PlantDetailFragmentArgs by navArgs()
    private lateinit var plantDetailListAdapter: PlantDetailListAdapter

    override fun getLayoutResId(): Int {
        return R.layout.fragment_plant_detail
    }

    override fun createPresenter(): PlantDetailFragmentPresenter {
        return PlantDetailFragmentPresenterImpl()
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
        val plantDetailTitleMap = mapOf(
            PlantDetailTitle.LOCATION to getString(R.string.plant_location_label),
            PlantDetailTitle.ALSO_KNOWN to getString(R.string.plant_also_known_label),
            PlantDetailTitle.ENG_NAME to getString(R.string.plant_eng_name_label),
            PlantDetailTitle.SCIENTIFIC_NAME to getString(R.string.plant_scientific_name_label),
            PlantDetailTitle.FAMILY_AND_GENUS to getString(R.string.plant_family_genus_label),
            PlantDetailTitle.BRIEF to getString(R.string.plant_brief_label),
            PlantDetailTitle.FEATURE to getString(R.string.plant_feature_label),
            PlantDetailTitle.UPDATE_TIME to getString(R.string.plant_update_time_label),
        )
        presenter.getPlantDetailList(plantDetailTitleMap, args.plantDetail)
    }

    override fun showPlantDetailList(plantDetailList: List<PlantDetailItem>) {
        plantDetailListAdapter.submitList(plantDetailList)
    }
}