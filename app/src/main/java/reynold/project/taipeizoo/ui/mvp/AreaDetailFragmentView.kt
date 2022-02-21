package reynold.project.taipeizoo.ui.mvp

import reynold.project.taipeizoo.models.PlantList
import reynold.project.taipeizoo.ui.base.BaseMvpView

interface AreaDetailFragmentView: BaseMvpView {
    fun showLoading()
    fun hideLoading()
    fun showPlantList(plantList: List<PlantList.Result.Detail>)
    fun navigateToPlantDetailFragment(plantDetail: PlantList.Result.Detail)
    fun onApiFailure()
}