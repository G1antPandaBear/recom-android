package kr.hs.pandabear.recom.presentation.feature.result

import kr.hs.pandabear.recom.presentation.base.BaseViewModel

class ResultViewModel : BaseViewModel() {

    fun onClickShow() {
        viewEvent(EVENT_ON_CLICK_SHOW)
    }

    fun onClickShare() {
        viewEvent(EVENT_ON_CLICK_SHARE)
    }

    companion object {
        const val EVENT_ON_CLICK_SHOW = 0
        const val EVENT_ON_CLICK_SHARE = 1
    }

}