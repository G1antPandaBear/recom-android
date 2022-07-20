package kr.hs.pandabear.recom.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.hs.pandabear.recom.R
import kr.hs.pandabear.recom.databinding.ItemSpeechBinding
import kr.hs.pandabear.recom.network.model.speech.SpeechInfo

class SpeechAdapter : ListAdapter<SpeechInfo, SpeechAdapter.SpeechViewHolder>(SpeechDiffUtilCallback) {

    class SpeechViewHolder(private val binding: ItemSpeechBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SpeechInfo) {
            binding.speech = data

            binding.cardSpeech.setOnClickListener {
                data.isImpact = data.isImpact.not()
                binding.speech = data
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeechViewHolder {
        return SpeechViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_speech,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SpeechViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    companion object {
        val SpeechDiffUtilCallback = object : DiffUtil.ItemCallback<SpeechInfo>() {
            override fun areItemsTheSame(oldItem: SpeechInfo, newItem: SpeechInfo): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: SpeechInfo, newItem: SpeechInfo): Boolean = oldItem == newItem
        }
    }
}