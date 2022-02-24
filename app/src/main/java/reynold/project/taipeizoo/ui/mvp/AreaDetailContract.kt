package reynold.project.taipeizoo.ui.mvp

import reynold.project.taipeizoo.models.AreaList
import reynold.project.taipeizoo.models.PlantList
import reynold.project.taipeizoo.ui.base.BaseMvpPresenter
import reynold.project.taipeizoo.ui.base.BaseMvpView

interface AreaDetailContract {

    interface AreaDetailFragmentView : BaseMvpView {
        fun showLoading()
        fun hideLoading()
        fun showPlantList(plantList: List<PlantList.Result.Detail>)
        fun navigateToPlantDetailFragment(plantDetail: PlantList.Result.Detail)
        fun onApiFailure()
    }

    interface AreaDetailFragmentPresenter : BaseMvpPresenter<AreaDetailFragmentView> {
        fun getPlantList(areaDetail: AreaList.Result.Detail)
        fun onPlantClick(plantDetail: PlantList.Result.Detail)
    }
}