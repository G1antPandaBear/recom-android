package kr.hs.pandabear.recom.domain.request

data class SaveDocumentRequest(
    val content: String,
    val address: String,
    val title: String
)
