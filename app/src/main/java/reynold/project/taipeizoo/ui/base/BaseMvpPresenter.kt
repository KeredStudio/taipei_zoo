package reynold.project.taipeizoo.ui.base

interface BaseMvpPresenter<V : BaseMvpView> {
    fun attachView(view: V)
    fun detachView()
}