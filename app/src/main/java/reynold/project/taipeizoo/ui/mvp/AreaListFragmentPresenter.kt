package reynold.project.taipeizoo.ui.mvp

import reynold.project.taipeizoo.api.ApiCallback
import reynold.project.taipeizoo.api.ZooRepository
import reynold.project.taipeizoo.models.AreaList

class AreaListFragmentPresenter : AreaListContract.AreaListFragmentPresenter {
    private var areaListView: AreaListContract.AreaListFragmentView? = null

    override fun getAreaList() {
        ZooRepository.getAreaList(object : ApiCallback<List<AreaList.Result.Detail>> {
            override fun onSuccess(result: List<AreaList.Result.Detail>) {
                areaListView?.showAreaList(result)
            }

            override fun onFailure(errorCode: Int) {
                areaListView?.onApiFailure() // TODO("error code handling")
            }

            override fun onShowLoading() {
                areaListView?.showLoading()
            }

            override fun onHideLoading() {
                areaListView?.hideLoading()
            }
        })
    }

    override fun onAreaClick(areaDetail: AreaList.Result.Detail) {
        areaListView?.navigateToAreaDetailFragment(areaDetail)
    }

    override fun attachView(view: AreaListContract.AreaListFragmentView) {
        areaListView = view
    }

    override fun detachView() {
        areaListView = null
    }
}