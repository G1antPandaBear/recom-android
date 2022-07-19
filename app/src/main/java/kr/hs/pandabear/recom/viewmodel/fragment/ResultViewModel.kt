package kr.hs.pandabear.recom.viewmodel.fragment

import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.pandabear.recom.viewmodel.base.BaseViewModel

class ResultViewModel : BaseViewModel() {

    fun onClickShow() {
        viewEvent(EVENT_ON_CLICK_SHOW)
    }

    companion object {
        const val EVENT_ON_CLICK_SHOW = 0
    }

}