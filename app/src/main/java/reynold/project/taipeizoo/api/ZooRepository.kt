package reynold.project.taipeizoo.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import reynold.project.taipeizoo.models.AreaList
import reynold.project.taipeizoo.models.PlantList

object ZooRepository {
    const val ERROR_CODE_RESPONSE_NULL = 10010
    const val ERROR_CODE_API_FAILURE = 10011
    private var localAreaList = listOf<AreaList.Result.Detail>()
    private var localPlantMap = mutableMapOf<String, List<PlantList.Result.Detail>>()

    fun getAreaList(apiCallback: ApiCallback<List<AreaList.Result.Detail>>) {
        if (localAreaList.isNotEmpty()) {
            apiCallback.onSuccess(localAreaList)
        } else {
            apiCallback.onShowLoading()
            val call = NetworkProvider.apiService.getAreaList()
            call.enqueue(object : Callback<AreaList> {
                override fun onResponse(call: Call<AreaList>, response: Response<AreaList>) {
                    if (response.isSuccessful) {
                        val areaList = response.body()
                        if (areaList != null) {
                            localAreaList = areaList.result.details
                            apiCallback.onSuccess(localAreaList)
                        } else {
                            apiCallback.onFailure(ERROR_CODE_RESPONSE_NULL)
                        }
                    } else {
                        apiCallback.onFailure(response.code())
                    }
                    apiCallback.onHideLoading()
                }

                override fun onFailure(call: Call<AreaList>, t: Throwable) {
                    apiCallback.onFailure(ERROR_CODE_API_FAILURE)
                    apiCallback.onHideLoading()
                }
            })
        }
    }

    fun getPlantList(query: String, apiCallback: ApiCallback<List<PlantList.Result.Detail>>) {
        val localPlantList = localPlantMap[query]
        if (localPlantList != null && localPlantList.isNotEmpty()) {
            apiCallback.onSuccess(localPlantList)
        } else {
            apiCallback.onShowLoading()
            val call = NetworkProvider.apiService.getPlantList(query = query)
            call.enqueue(object : Callback<PlantList> {
                override fun onResponse(call: Call<PlantList>, response: Response<PlantList>) {
                    if (response.isSuccessful) {
                        val plantList = response.body()
                        if (plantList != null) {
                            localPlantMap[query] = plantList.result.details
                            apiCallback.onSuccess(localPlantMap[query]!!)
                        } else {
                            apiCallback.onFailure(ERROR_CODE_RESPONSE_NULL)
                        }
                    } else {
                        apiCallback.onFailure(response.code())
                    }
                    apiCallback.onHideLoading()
                }

                override fun onFailure(call: Call<PlantList>, t: Throwable) {
                    apiCallback.onFailure(ERROR_CODE_API_FAILURE)
                    apiCallback.onHideLoading()
                }
            })

        }
    }
}