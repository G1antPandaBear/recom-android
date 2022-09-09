package kr.hs.pandabear.recom.domain.request

data class SaveDataRequest(
    val content: String,
    val address: String,
    val title: String
)
