package reynold.project.taipeizoo.ui.mvp

import reynold.project.taipeizoo.models.AreaList
import reynold.project.taipeizoo.models.PlantList
import reynold.project.taipeizoo.ui.base.BaseMvpPresenter

interface AreaDetailFragmentPresenter: BaseMvpPresenter<AreaDetailFragmentView> {
    fun getPlantList(areaDetail: AreaList.Result.Detail)
    fun onPlantClick(plantDetail: PlantList.Result.Detail)
}