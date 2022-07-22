package kr.hs.pandabear.recom.viewmodel.activity

import kr.hs.pandabear.recom.viewmodel.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    companion object {
        const val EVENT_ON_CLICK_BACK = 1
    }

    fun onClickBackEvent() {
        viewEvent(EVENT_ON_CLICK_BACK)
    }
}