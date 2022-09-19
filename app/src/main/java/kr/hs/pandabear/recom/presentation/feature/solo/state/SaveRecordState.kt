package kr.hs.pandabear.recom.presentation.feature.solo.state

import kr.hs.pandabear.recom.domain.model.document.Document

data class SaveRecordState(
    val document: Document? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
