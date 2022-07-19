package kr.hs.pandabear.recom.view.fragment

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.fragment.app.viewModels
import kr.hs.pandabear.recom.databinding.FragmentSoloBinding
import kr.hs.pandabear.recom.view.base.BaseFragment
import kr.hs.pandabear.recom.viewmodel.activity.MainViewModel
import kr.hs.pandabear.recom.viewmodel.fragment.SoloViewModel

class SoloFragment : BaseFragment<FragmentSoloBinding, SoloViewModel>() {
    override val viewModel: SoloViewModel by viewModels()

    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var recognizerIntent: Intent

    override fun observerViewModel() {
        setSpeechRecognizer()
    }

    override fun bindingViewEvent() {
        with(viewModel) {
            viewEvent.observe(this@SoloFragment) {
                it.getContentIfNotHandled()?.let { event ->
                    when (event) {
                        SoloViewModel.EVENT_ON_CLICK_PLAY -> {
                            onClickPlayButton()
                        }
                    }
                }
            }
        }
    }

    private fun setSpeechRecognizer() {
        recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, requireContext().packageName)
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR")
    }

    private fun onClickPlayButton() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())
        speechRecognizer.setRecognitionListener(recognitionListener)
        speechRecognizer.startListening(recognizerIntent)
    }

    private val recognitionListener: RecognitionListener = object : RecognitionListener {
        override fun onReadyForSpeech(p0: Bundle?) {
            mBinding.tvState.text = "이제 말씁하세요!"
        }

        override fun onBeginningOfSpeech() {
            mBinding.tvState.text = "잘 듣고 있어요."
        }

        override fun onRmsChanged(sound: Float) {

        }

        override fun onBufferReceived(p0: ByteArray?) {}

        override fun onEndOfSpeech() {
            mBinding.tvState.text = "끝"
        }

        override fun onError(error: Int) {
           val message = when (error) {
                SpeechRecognizer.ERROR_AUDIO -> "오디오 에러"
                SpeechRecognizer.ERROR_CLIENT -> "클라이언트 에러"
                SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "퍼미션 없음"
                SpeechRecognizer.ERROR_NETWORK -> "네트워크 에러"
                SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "네트웍 타임아웃"
                SpeechRecognizer.ERROR_NO_MATCH -> "찾을 수 없음"
                SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "RECOGNIZER 가 바쁨"
                SpeechRecognizer.ERROR_SERVER -> "서버가 이상함"
                SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "말하는 시간초과"
                else -> "알 수 없는 오류"
            }
            mBinding.tvState.text = message
        }

        override fun onResults(results: Bundle?) {
            val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        }

        override fun onPartialResults(p0: Bundle?) {}

        override fun onEvent(p0: Int, p1: Bundle?) {}
    }

}