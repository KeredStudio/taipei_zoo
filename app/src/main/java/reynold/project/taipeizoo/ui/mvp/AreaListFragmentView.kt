package reynold.project.taipeizoo.ui.mvp

import reynold.project.taipeizoo.models.AreaList
import reynold.project.taipeizoo.ui.base.BaseMvpView

interface AreaListFragmentView: BaseMvpView {
    fun showLoading()
    fun hideLoading()
    fun showAreaList(areaList: List<AreaList.Result.Detail>)
    fun navigateToAreaDetailFragment(areaDetail: AreaList.Result.Detail)
    fun onApiFailure()
}