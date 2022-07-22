package kr.hs.pandabear.recom.viewmodel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kr.hs.pandabear.recom.network.model.speech.Document
import kr.hs.pandabear.recom.network.request.SaveDataRequest
import kr.hs.pandabear.recom.network.service.DocumentService
import kr.hs.pandabear.recom.viewmodel.base.BaseViewModel
import kr.hs.pandabear.recom.viewmodel.state.SaveRecordState
import kr.hs.pandabear.recom.widget.Resource
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SoloViewModel @Inject constructor(
    private val service: DocumentService
) : BaseViewModel() {

    private val _isEnd = MutableLiveData<Boolean>(true)
    val isEnd: LiveData<Boolean> get() = _isEnd

    private val _saveRecordState = MutableStateFlow<SaveRecordState>(SaveRecordState(isLoading = false))
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
        useSendData().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    isLoading.value = false
                    _saveRecordState.value = SaveRecordState(document = result.data)
                }
                is Resource.Loading -> {
                    isLoading.value = true
                    _saveRecordState.value = SaveRecordState(isLoading = true)
                }
                is Resource.Error -> {
                    isLoading.value = false
                    _saveRecordState.value = SaveRecordState(error = result.message ?: "문서를 받아오지 못하였습니다.")
                }
            }
        }.launchIn(viewModelScope)
    }
    
    private fun useSendData() : Flow<Resource<Document>> = flow {
        try {
            emit(Resource.Loading())
            val result = service.saveData(
                SaveDataRequest (
                    content.value ?: "",
                    address.value ?: "대구광역시 달성군 구지면 창리로11길 93",
                    title = title.value ?: "무제"
                )

            )
            emit(Resource.Success<Document>(result))
        } catch (e: HttpException) {
            emit(Resource.Error<Document>(convertErrorBody(e)))
        } catch (e: IOException) {
            Resource.Error<Document>("서버에 도달할 수 없습니다. 네트워크 상태를 확인해 주세요.")
        }
    }

    private fun convertErrorBody(throwable: HttpException): String {
        val errorBody = JSONObject(throwable.response()?.errorBody()!!.string())
        return errorBody.getString("message")
    }

    companion object {
        const val EVENT_ON_CLICK_PLAY = 1
        const val EVENT_ON_CLICK_CLEAR = 2
        const val EVENT_ON_CLICK_SAVE = 3
    }
}