package reynold.project.taipeizoo.ui.mvp

import reynold.project.taipeizoo.models.PlantDetailItem
import reynold.project.taipeizoo.models.PlantList
import reynold.project.taipeizoo.util.PlantDetailTitle

class PlantDetailFragmentPresenterImpl : PlantDetailFragmentPresenter {
    private var plantDetailView: PlantDetailFragmentView? = null

    override fun getPlantDetailList(plantDetailTitleMap: Map<PlantDetailTitle, String>, plantDetail: PlantList.Result.Detail) {
        plantDetailView?.showPlantDetailList(
            plantDetailTitleMap.map {
                when (it.key) {
                    PlantDetailTitle.LOCATION -> PlantDetailItem(it.value, plantDetail.fLocation)
                    PlantDetailTitle.ALSO_KNOWN -> PlantDetailItem(it.value, plantDetail.fAlsoKnown)
                    PlantDetailTitle.ENG_NAME -> PlantDetailItem(it.value, plantDetail.fNameEn)
                    PlantDetailTitle.SCIENTIFIC_NAME -> PlantDetailItem(it.value, plantDetail.fNameLatin)
                    PlantDetailTitle.FAMILY_AND_GENUS -> PlantDetailItem(it.value, "${plantDetail.fFamily};${plantDetail.fGenus}")
                    PlantDetailTitle.BRIEF -> PlantDetailItem(it.value, plantDetail.fBrief)
                    PlantDetailTitle.FEATURE -> PlantDetailItem(it.value, plantDetail.fFeature)
                    PlantDetailTitle.UPDATE_TIME -> PlantDetailItem(it.value, plantDetail.fUpdate)
                }
            })
    }

    override fun attachView(view: PlantDetailFragmentView) {
        plantDetailView = view
    }

    override fun detachView() {
        plantDetailView = null
    }
}