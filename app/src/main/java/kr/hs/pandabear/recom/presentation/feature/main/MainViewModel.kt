package kr.hs.pandabear.recom.presentation.feature.main

import kr.hs.pandabear.recom.presentation.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    companion object {
        const val EVENT_ON_CLICK_BACK = 1
    }

    fun onClickBackEvent() {
        viewEvent(EVENT_ON_CLICK_BACK)
    }
}