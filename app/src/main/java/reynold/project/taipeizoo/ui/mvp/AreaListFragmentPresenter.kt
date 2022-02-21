package reynold.project.taipeizoo.ui.mvp

import reynold.project.taipeizoo.models.AreaList
import reynold.project.taipeizoo.ui.base.BaseMvpPresenter

interface AreaListFragmentPresenter: BaseMvpPresenter<AreaListFragmentView> {
    fun getAreaList()
    fun onAreaClick(areaDetail: AreaList.Result.Detail)
}