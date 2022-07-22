package kr.hs.pandabear.recom.viewmodel.state

import kr.hs.pandabear.recom.network.model.speech.Document

data class SaveRecordState(
    val isLoading: Boolean = false,
    val document: Document? = null,
    val error: String = ""
)
