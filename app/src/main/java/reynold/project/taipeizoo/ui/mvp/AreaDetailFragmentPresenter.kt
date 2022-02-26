package reynold.project.taipeizoo.ui.mvp

import reynold.project.taipeizoo.api.ApiCallback
import reynold.project.taipeizoo.api.ZooRepository
import reynold.project.taipeizoo.models.AreaList
import reynold.project.taipeizoo.models.PlantList

class AreaDetailFragmentPresenter: AreaDetailContract.AreaDetailFragmentPresenter {
    private var areaDetailView: AreaDetailContract.AreaDetailFragmentView? = null

    override fun getPlantList(areaDetail: AreaList.Result.Detail) {
        ZooRepository.getPlantList(areaDetail.eName, object : ApiCallback<List<PlantList.Result.Detail>> {
            override fun onSuccess(result: List<PlantList.Result.Detail>) {
                areaDetailView?.showPlantList(result)
            }

            override fun onFailure(errorCode: Int) {
                areaDetailView?.onApiFailure() // TODO("error code handling")
            }

            override fun onShowLoading() {
                areaDetailView?.showLoading()
            }

            override fun onHideLoading() {
                areaDetailView?.hideLoading()
            }
        })
    }

    override fun onPlantClick(plantDetail: PlantList.Result.Detail) {
        areaDetailView?.navigateToPlantDetailFragment(plantDetail)
    }

    override fun attachView(view: AreaDetailContract.AreaDetailFragmentView) {
        areaDetailView = view
    }

    override fun detachView() {
        areaDetailView = null
    }
}