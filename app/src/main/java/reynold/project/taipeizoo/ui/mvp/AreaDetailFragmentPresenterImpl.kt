package reynold.project.taipeizoo.ui.mvp

import reynold.project.taipeizoo.api.ApiCallback
import reynold.project.taipeizoo.api.NetworkRepository
import reynold.project.taipeizoo.models.AreaList
import reynold.project.taipeizoo.models.PlantList

class AreaDetailFragmentPresenterImpl: AreaDetailFragmentPresenter {
    private var areaDetailView: AreaDetailFragmentView? = null

    override fun getPlantList(areaDetail: AreaList.Result.Detail) {
        areaDetailView?.showLoading()
        NetworkRepository.getPlantList(areaDetail.eName, object : ApiCallback<List<PlantList.Result.Detail>> {
            override fun onSuccess(result: List<PlantList.Result.Detail>) {
                areaDetailView?.hideLoading()
                areaDetailView?.showPlantList(result)
            }

            override fun onFailure(errorCode: Int) {
                areaDetailView?.hideLoading()
                areaDetailView?.onApiFailure() // TODO("error code handling")
            }
        })
    }

    override fun onPlantClick(plantDetail: PlantList.Result.Detail) {
        areaDetailView?.navigateToPlantDetailFragment(plantDetail)
    }

    override fun attachView(view: AreaDetailFragmentView) {
        areaDetailView = view
    }

    override fun detachView() {
        areaDetailView = null
    }
}