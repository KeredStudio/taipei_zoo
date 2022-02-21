package reynold.project.taipeizoo.ui.mvp

import reynold.project.taipeizoo.api.ApiCallback
import reynold.project.taipeizoo.api.NetworkRepository
import reynold.project.taipeizoo.models.AreaList

class AreaListFragmentPresenterImpl: AreaListFragmentPresenter {
    private var areaListView: AreaListFragmentView? = null

    override fun getAreaList() {
        areaListView?.showLoading()
        NetworkRepository.getAreaList(object : ApiCallback<List<AreaList.Result.Detail>> {
            override fun onSuccess(result: List<AreaList.Result.Detail>) {
                areaListView?.hideLoading()
                areaListView?.showAreaList(result)
            }

            override fun onFailure(errorCode: Int) {
                areaListView?.hideLoading()
                areaListView?.onApiFailure() // TODO("error code handling")
            }
        })
    }

    override fun onAreaClick(areaDetail: AreaList.Result.Detail) {
        areaListView?.navigateToAreaDetailFragment(areaDetail)
    }

    override fun attachView(view: AreaListFragmentView) {
        areaListView = view
    }

    override fun detachView() {
        areaListView = null
    }
}