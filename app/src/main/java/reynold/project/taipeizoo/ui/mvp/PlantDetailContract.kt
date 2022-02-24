package reynold.project.taipeizoo.ui.mvp

import reynold.project.taipeizoo.models.PlantDetailItem
import reynold.project.taipeizoo.models.PlantList
import reynold.project.taipeizoo.ui.base.BaseMvpPresenter
import reynold.project.taipeizoo.ui.base.BaseMvpView

interface PlantDetailContract {

    interface PlantDetailFragmentView : BaseMvpView {
        fun showPlantDetailList(plantDetailList: List<PlantDetailItem>)
    }

    interface PlantDetailFragmentPresenter : BaseMvpPresenter<PlantDetailFragmentView> {
        fun getPlantDetailList(plantTitleList: Array<String>, plantDetail: PlantList.Result.Detail)
    }
}