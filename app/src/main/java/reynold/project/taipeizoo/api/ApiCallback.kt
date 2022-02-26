package reynold.project.taipeizoo.api

interface ApiCallback<T> {
    fun onSuccess(result: T)
    fun onFailure(errorCode: Int)
    fun onShowLoading()
    fun onHideLoading()
}