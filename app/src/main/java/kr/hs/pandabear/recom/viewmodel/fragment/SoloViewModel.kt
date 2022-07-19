package kr.hs.pandabear.recom.viewmodel.fragment

import kr.hs.pandabear.recom.viewmodel.base.BaseViewModel

class SoloViewModel : BaseViewModel() {

    fun onClickPlay() {
        viewEvent(EVENT_ON_CLICK_PLAY)
    }

    companion object {
        const val EVENT_ON_CLICK_PLAY = 1
    }
}