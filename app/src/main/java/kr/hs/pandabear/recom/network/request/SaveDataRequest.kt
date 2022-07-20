package kr.hs.pandabear.recom.network.request

data class SaveDataRequest(
    val content: String,
    val address: String,
    val title: String
)
