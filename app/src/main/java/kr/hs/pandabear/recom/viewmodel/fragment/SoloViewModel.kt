package kr.hs.pandabear.recom.viewmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kr.hs.pandabear.recom.viewmodel.base.BaseViewModel

class SoloViewModel : BaseViewModel() {

    private val _isEnd = MutableLiveData<Boolean>(true)
    val isEnd: LiveData<Boolean> get() = _isEnd

    fun onClickPlay() {
        _isEnd.value = _isEnd.value?.not()
        viewEvent(EVENT_ON_CLICK_PLAY)
    }

    companion object {
        const val EVENT_ON_CLICK_PLAY = 1
    }
}