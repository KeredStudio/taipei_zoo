package reynold.project.taipeizoo.ui.mvp

import reynold.project.taipeizoo.models.AreaList
import reynold.project.taipeizoo.ui.base.BaseMvpPresenter
import reynold.project.taipeizoo.ui.base.BaseMvpView

interface AreaListContract {

    interface AreaListFragmentView : BaseMvpView {
        fun showLoading()
        fun hideLoading()
        fun showAreaList(areaList: List<AreaList.Result.Detail>)
        fun navigateToAreaDetailFragment(areaDetail: AreaList.Result.Detail)
        fun onApiFailure()
    }

    interface AreaListFragmentPresenter : BaseMvpPresenter<AreaListFragmentView> {
        fun getAreaList()
        fun onAreaClick(areaDetail: AreaList.Result.Detail)
    }
}