package kr.hs.pandabear.recom.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kr.hs.pandabear.recom.domain.utils.Resource
import kr.hs.pandabear.recom.presentation.widget.Event

open class BaseViewModel : ViewModel() {

    private val _viewEvent = MutableLiveData<Event<Any>>()
    val viewEvent: LiveData<Event<Any>>
        get() = _viewEvent

    fun viewEvent(content: Any) {
        _viewEvent.value = Event(content)
    }

    fun <T> Flow<Resource<T>>.divideResult(
        successAction: (T?) -> Unit,
        loadingAction: () -> Unit,
        errorAction: (String?) -> Unit
    ) = onEach { resource ->
        when (resource) {
            is Resource.Success -> {
                successAction.invoke(resource.data)
            }
            is Resource.Loading -> {
                loadingAction.invoke()
            }
            is Resource.Error -> {
                errorAction.invoke(resource.message)
            }
        }
    }

    val onErrorEvent = MutableLiveData<Throwable>()
}