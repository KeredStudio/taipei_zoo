package reynold.project.taipeizoo.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import reynold.project.taipeizoo.models.AreaList
import reynold.project.taipeizoo.models.PlantList

object NetworkRepository {
    const val ERROR_CODE_RESPONSE_NULL = 10010
    const val ERROR_CODE_API_FAILURE = 10011

    fun getAreaList(apiCallback: ApiCallback<List<AreaList.Result.Detail>>) {
        val call = NetworkProvider.apiService.getAreaList()
        call.enqueue(object : Callback<AreaList> {
            override fun onResponse(call: Call<AreaList>, response: Response<AreaList>) {
                if (response.isSuccessful) {
                    val areaList = response.body()
                    if (areaList != null) {
                        apiCallback.onSuccess(areaList.result.details)
                    } else {
                        apiCallback.onFailure(ERROR_CODE_RESPONSE_NULL)
                    }
                } else {
                    apiCallback.onFailure(response.code())
                }
            }

            override fun onFailure(call: Call<AreaList>, t: Throwable) {
                apiCallback.onFailure(ERROR_CODE_API_FAILURE)
            }
        })
    }

    fun getPlantList(query: String? = null, apiCallback: ApiCallback<List<PlantList.Result.Detail>>) {
        val call = NetworkProvider.apiService.getPlantList(query = query)
        call.enqueue(object : Callback<PlantList> {
            override fun onResponse(call: Call<PlantList>, response: Response<PlantList>) {
                if (response.isSuccessful) {
                    val plantList = response.body()
                    if (plantList != null) {
                        apiCallback.onSuccess(plantList.result.details)
                    } else {
                        apiCallback.onFailure(ERROR_CODE_RESPONSE_NULL)
                    }
                } else {
                    apiCallback.onFailure(response.code())
                }
            }

            override fun onFailure(call: Call<PlantList>, t: Throwable) {
                apiCallback.onFailure(ERROR_CODE_API_FAILURE)
            }
        })
    }
}