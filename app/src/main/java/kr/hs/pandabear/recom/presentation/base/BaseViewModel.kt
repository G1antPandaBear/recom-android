package kr.hs.pandabear.recom.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.hs.pandabear.recom.presentation.widget.Event

open class BaseViewModel : ViewModel() {

    protected val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    fun getIsLoading(): LiveData<Boolean> {
        return isLoading
    }

    private val _viewEvent = MutableLiveData<Event<Any>>()
    val viewEvent: LiveData<Event<Any>>
        get() = _viewEvent

    fun viewEvent(content: Any) {
        _viewEvent.value = Event(content)
    }

    val onErrorEvent = MutableLiveData<Throwable>()
}