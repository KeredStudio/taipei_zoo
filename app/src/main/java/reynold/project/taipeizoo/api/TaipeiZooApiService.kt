package reynold.project.taipeizoo.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import reynold.project.taipeizoo.models.AreaList
import reynold.project.taipeizoo.models.PlantList

interface TaipeiZooApiService {

    @GET("api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a")
    fun getAreaList(
        @Query("scope") scope: String = "resourceAquire",
        @Query("q") query: String? = null,
        @Query("limit") limit: String? = null,
        @Query("offset") offset: String? = null,
    ): Call<AreaList>

    @GET("/api/v1/dataset/f18de02f-b6c9-47c0-8cda-50efad621c14")
    fun getPlantList(
        @Query("scope") scope: String = "resourceAquire",
        @Query("q") query: String? = null,
        @Query("limit") limit: String? = null,
        @Query("offset") offset: String? = null,
    ): Call<PlantList>
}