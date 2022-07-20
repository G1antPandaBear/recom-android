package kr.hs.pandabear.recom.view.fragment

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.pandabear.recom.databinding.FragmentSoloBinding
import kr.hs.pandabear.recom.network.model.speech.SpeechInfo
import kr.hs.pandabear.recom.view.activity.MainActivity
import kr.hs.pandabear.recom.view.adapter.SpeechAdapter
import kr.hs.pandabear.recom.view.base.BaseFragment
import kr.hs.pandabear.recom.viewmodel.fragment.SoloViewModel
import kotlin.collections.ArrayList

@AndroidEntryPoint
class SoloFragment : BaseFragment<FragmentSoloBinding, SoloViewModel>() {
    override val viewModel: SoloViewModel by viewModels()

    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var recognizerIntent: Intent
    private lateinit var adapter: SpeechAdapter
    private var isFirstUsed: Boolean = true
    var code: String = ""

    override fun observerViewModel() {
        setSpeechRecognizer()
        setSpeechRecycler()
        collectDocument()

        with(viewModel) {
            isEnd.observe(this@SoloFragment) {
                if (isEnd.value == true) {
                    speechRecognizer.stopListening()
                    speechRecognizer.cancel()

                    mBinding.soundVisualizerView.stopVisualizing()
                    mBinding.soundVisualizerView.clearVisualization()

                    isFirstUsed = true
                }
            }
        }
    }

    override fun bindingViewEvent() {
        with(viewModel) {
            viewEvent.observe(this@SoloFragment) {
                it.getContentIfNotHandled()?.let { event ->
                    when (event) {
                        SoloViewModel.EVENT_ON_CLICK_PLAY -> {
                            onClickPlayButton()
                        }
                        SoloViewModel.EVENT_ON_CLICK_CLEAR -> {
                            adapter.submitList(listOf())
                            adapter.notifyDataSetChanged()
                        }
                        SoloViewModel.EVENT_ON_CLICK_SAVE -> {
                            setEndToTrue()
                            getValueInMainActivity()
                            if (adapter.currentList.isEmpty()) {
                                Toast.makeText(requireContext(), "값이 없어요..ㅠ", Toast.LENGTH_SHORT)
                                    .show()
                                return@observe
                            }
                            viewModel.content.value = convertToString(adapter.currentList)
                            viewModel.saveMeetingRecord()
                        }
                    }
                }
            }
        }
    }

    private fun collectDocument() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                saveRecordState.collect { state ->
                    if (state.document != null) {
                        Log.d("TestTest", "collectDocument: document is not null ${state.document.code}")
                        val navAction = SoloFragmentDirections.actionSoloFragmentToResultFragment(state.document.code)
                        findNavController().navigate(navAction)
                    }

                    if (state.error.isNotBlank()) {
                        Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setSpeechRecognizer() {
        recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        recognizerIntent.putExtra(
            RecognizerIntent.EXTRA_CALLING_PACKAGE,
            requireContext().packageName
        )
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR")

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())
    }

    private fun setSpeechRecycler() {
        adapter = SpeechAdapter()
        mBinding.rcSpeech.adapter = adapter
        val manager = LinearLayoutManager(requireContext())
        manager.reverseLayout = true
        manager.stackFromEnd = true
        mBinding.rcSpeech.layoutManager = manager

        // Test 더미 데이터
        /*val dataList = mutableListOf<SpeechInfo>()
        dataList.add(SpeechInfo("안녕"))
        dataList.add(SpeechInfo("안녕"))
        dataList.add(SpeechInfo("안녕"))
        dataList.add(SpeechInfo("안녕"))
        dataList.add(SpeechInfo("안녕"))
        dataList.add(SpeechInfo("안녕"))
        dataList.add(SpeechInfo("안녕"))
        dataList.add(SpeechInfo("안녕"))
        dataList.add(SpeechInfo("두두두등장"))
        adapter.submitList(dataList)*/
    }

    private val recognitionListener: RecognitionListener = object : RecognitionListener {
        override fun onReadyForSpeech(p0: Bundle?) {
            Log.e("asdf", "onReady")
            mBinding.tvState.text = "이제 말씀하세요!"
        }

        override fun onBeginningOfSpeech() {
            Log.e("asdf", "begin")
            mBinding.tvState.text = "잘 듣고 있어요."
        }

        override fun onRmsChanged(sound: Float) {
            mBinding.soundVisualizerView.onRequestCurrentAmplitude =
                { sound.toInt()*1678 }
        }

        override fun onBufferReceived(p0: ByteArray?) {}

        override fun onEndOfSpeech() {
            Log.e("asdf", "onEndOfSpeech", )
            if (viewModel.isEnd.value == true) {
                mBinding.tvState.text = "녹음을 완료하였습니다."
            } else {
                replay()
            }
        }

        override fun onError(error: Int) {
            val message = when (error) {
                SpeechRecognizer.ERROR_AUDIO -> "오디오 에러"
                SpeechRecognizer.ERROR_CLIENT -> "녹음이 종료되었습니다."
                SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "퍼미션 없음"
                SpeechRecognizer.ERROR_NETWORK -> "네트워크 에러"
                SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "네트웍 타임아웃"
                SpeechRecognizer.ERROR_NO_MATCH -> {
                    Log.e("asdf-nomatch", "no match")
                    replay()
                    "말씀을 해주세요"
                }
                SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> {
                    Log.e("asdf", "busy")
                    replay()
                    "말씀이 너무 복잡해요.."
                }
                SpeechRecognizer.ERROR_SERVER -> "서버가 이상함"
                SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "말하는 시간초과"
                else -> "알 수 없는 오류"
            }
            mBinding.tvState.text = message
        }

        override fun onResults(results: Bundle?) {
            Log.e("asdf", "onResult")
            val matches: String = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                .toString()
                .replace("[", "")
                .replace("]", "")
            Log.e("asdf", "onResults: $matches")

            val dataList: ArrayList<SpeechInfo> = ArrayList()
            adapter.currentList.forEach {
                dataList.add(it)
            }
            dataList.add(SpeechInfo(matches))
            mBinding.rcSpeech.smoothScrollToPosition(adapter.itemCount)
            adapter.submitList(dataList)
        }

        override fun onPartialResults(p0: Bundle?) {}

        override fun onEvent(p0: Int, p1: Bundle?) {}

        private fun replay() {
            Log.e("asdf", "replay in")
            if (isFirstUsed) {
                isFirstUsed = false
            } else {
                speechRecognizer.cancel()
            }
            onClickPlayButton()
        }
    }

    private fun onClickPlayButton() {
        Log.e("asdf", "onClick Playbtn")
        if(viewModel.isEnd.value == false) {
            if (isFirstUsed) {
                Toast.makeText(requireContext(), "테스트를 해주세요!", Toast.LENGTH_SHORT).show()
                speechRecognizer.setRecognitionListener(recognitionListener)
                mBinding.soundVisualizerView.startVisualizing(false)

                isFirstUsed = false
            }
            speechRecognizer.startListening(recognizerIntent)
        }
    }

    private fun convertToString(recordList: List<SpeechInfo>): String {
        val recordStr = StringBuilder()
        recordList.forEach {
            if (it.isImpact)
                recordStr.append("#")
            recordStr.append(it.speech)
            recordStr.append("\\")
        }
        return recordStr.toString()
    }

    private fun getValueInMainActivity() {
        viewModel.title.value = (activity as MainActivity).subject
        viewModel.address.value = (activity as MainActivity).address
        Log.d("TestTest", "getValueInMainActivity: ${viewModel.address.value}")
    }

    override fun onStop() {
        super.onStop()

        speechRecognizer.destroy()
        mBinding.soundVisualizerView.stopVisualizing()
        mBinding.soundVisualizerView.clearVisualization()
    }
}