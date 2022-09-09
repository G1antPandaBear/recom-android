package kr.hs.pandabear.recom.presentation.feature.solo.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.pandabear.recom.domain.model.speech.SpeechInfo

object SpeechDiffUtilCallback : DiffUtil.ItemCallback<SpeechInfo>() {

    override fun areItemsTheSame(oldItem: SpeechInfo, newItem: SpeechInfo): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: SpeechInfo, newItem: SpeechInfo): Boolean =
        oldItem == newItem
}
