package reynold.project.taipeizoo.ui.mvp

import reynold.project.taipeizoo.models.PlantDetailItem
import reynold.project.taipeizoo.ui.base.BaseMvpView

interface PlantDetailFragmentView: BaseMvpView {
    fun showPlantDetailList(plantDetailList: List<PlantDetailItem>)
}