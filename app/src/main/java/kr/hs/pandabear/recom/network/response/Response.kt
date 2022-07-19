package kr.hs.pandabear.recom.network.response

data class Response<T>(
    val `data`: T,
    val status: Int,
    val message: String
)