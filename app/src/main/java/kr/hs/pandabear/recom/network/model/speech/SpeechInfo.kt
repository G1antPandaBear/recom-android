package kr.hs.pandabear.recom.network.model.speech

data class SpeechInfo (
    val speech: String,
    var isImpact: Boolean = false
)