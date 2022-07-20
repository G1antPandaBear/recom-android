package kr.hs.pandabear.recom.viewmodel.fragment

import kr.hs.pandabear.recom.viewmodel.base.BaseViewModel

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