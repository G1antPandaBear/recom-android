package kr.hs.pandabear.recom.presentation.feature.solo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.pandabear.recom.domain.usecase.document.SaveDocumentUseCase
import kr.hs.pandabear.recom.presentation.base.BaseViewModel
import kr.hs.pandabear.recom.presentation.feature.solo.state.SaveRecordState
import javax.inject.Inject

@HiltViewModel
class SoloViewModel @Inject constructor(
    private val saveDocumentUseCase: SaveDocumentUseCase
) : BaseViewModel() {

    private val _isEnd = MutableLiveData<Boolean>(true)
    val isEnd: LiveData<Boolean> get() = _isEnd

    private val _saveRecordState = MutableStateFlow<SaveRecordState>(SaveRecordState())
    val saveRecordState: StateFlow<SaveRecordState> = _saveRecordState

    val content = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val address = MutableLiveData<String>()

    fun onClickPlay() {
        _isEnd.value = _isEnd.value?.not()
        viewEvent(EVENT_ON_CLICK_PLAY)
    }

    fun onClickClear() {
        viewEvent(EVENT_ON_CLICK_CLEAR)
    }

    fun onClickSave() {
        viewEvent(EVENT_ON_CLICK_SAVE)
    }

    fun setEndToTrue() {
        _isEnd.value = true
    }

    fun saveMeetingRecord() {
        saveDocumentUseCase(
            SaveDocumentUseCase.Params(
                content = content.value ?: return,
                address = address.value ?: return,
                title = title.value ?: "무제"
            )
        ).divideResult(
            { _saveRecordState.value = SaveRecordState(document = it, isLoading = false) },
            { _saveRecordState.value = SaveRecordState(isLoading = true) },
            { _saveRecordState.value = SaveRecordState(error = it ?: "문서를 받아오지 못하였습니다.", isLoading = false) }
        ).launchIn(viewModelScope)
    }

    companion object {
        const val EVENT_ON_CLICK_PLAY = 1
        const val EVENT_ON_CLICK_CLEAR = 2
        const val EVENT_ON_CLICK_SAVE = 3
    }
}
