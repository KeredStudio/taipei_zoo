package reynold.project.taipeizoo.ui.mvp

import reynold.project.taipeizoo.models.PlantList
import reynold.project.taipeizoo.ui.base.BaseMvpPresenter
import reynold.project.taipeizoo.util.PlantDetailTitle

interface PlantDetailFragmentPresenter: BaseMvpPresenter<PlantDetailFragmentView> {
    fun getPlantDetailList(plantDetailTitleMap: Map<PlantDetailTitle, String>, plantDetail: PlantList.Result.Detail)
}