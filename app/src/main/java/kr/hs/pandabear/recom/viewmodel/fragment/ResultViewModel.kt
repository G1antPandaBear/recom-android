package kr.hs.pandabear.recom.viewmodel.fragment

import kr.hs.pandabear.recom.viewmodel.base.BaseViewModel

class ResultViewModel : BaseViewModel() {

    fun onClickShow() {
        viewEvent(EVENT_ON_CLICK_SHOW)
    }

    companion object {
        const val EVENT_ON_CLICK_SHOW = 0
    }

}