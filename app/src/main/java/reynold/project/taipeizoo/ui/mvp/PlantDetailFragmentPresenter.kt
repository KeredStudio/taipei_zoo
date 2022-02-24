package reynold.project.taipeizoo.ui.mvp

import reynold.project.taipeizoo.models.PlantDetailItem
import reynold.project.taipeizoo.models.PlantList
import reynold.project.taipeizoo.util.PlantTitleIndex.ALSO_KNOWN
import reynold.project.taipeizoo.util.PlantTitleIndex.BRIEF
import reynold.project.taipeizoo.util.PlantTitleIndex.ENG_NAME
import reynold.project.taipeizoo.util.PlantTitleIndex.FAMILY_AND_GENUS
import reynold.project.taipeizoo.util.PlantTitleIndex.FEATURE
import reynold.project.taipeizoo.util.PlantTitleIndex.LOCATION
import reynold.project.taipeizoo.util.PlantTitleIndex.SCIENTIFIC_NAME
import reynold.project.taipeizoo.util.PlantTitleIndex.UPDATE_TIME


class PlantDetailFragmentPresenter : PlantDetailContract.PlantDetailFragmentPresenter {
    private var plantDetailView: PlantDetailContract.PlantDetailFragmentView? = null

    override fun getPlantDetailList(plantTitleList: Array<String>, plantDetail: PlantList.Result.Detail) {
        val plantDetailList = mutableListOf<PlantDetailItem>()
        plantTitleList.forEachIndexed { index, title ->
            plantDetailList.add(
                PlantDetailItem(
                    title,
                    when (index) {
                        LOCATION -> plantDetail.fLocation
                        ALSO_KNOWN -> plantDetail.fAlsoKnown
                        ENG_NAME -> plantDetail.fNameEn
                        SCIENTIFIC_NAME -> plantDetail.fNameLatin
                        FAMILY_AND_GENUS -> "${plantDetail.fFamily};${plantDetail.fGenus}"
                        BRIEF -> plantDetail.fBrief
                        FEATURE -> plantDetail.fFeature
                        UPDATE_TIME -> plantDetail.fUpdate
                        else -> return@forEachIndexed
                    }
                )
            )
        }
        plantDetailView?.showPlantDetailList(plantDetailList)
    }

    override fun attachView(view: PlantDetailContract.PlantDetailFragmentView) {
        plantDetailView = view
    }

    override fun detachView() {
        plantDetailView = null
    }
}