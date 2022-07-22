package kr.hs.pandabear.recom.view.fragment

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import kr.hs.pandabear.recom.databinding.FragmentResultBinding
import kr.hs.pandabear.recom.view.base.BaseFragment
import kr.hs.pandabear.recom.viewmodel.fragment.ResultViewModel
import kr.hs.pandabear.recom.widget.Constants

class ResultFragment : BaseFragment<FragmentResultBinding, ResultViewModel>() {
    override val viewModel: ResultViewModel by viewModels()
    private val args: ResultFragmentArgs by navArgs()
    lateinit var uriString: String

    override fun observerViewModel() {
        uriString = Constants.BASE_URL + "document/" + args.code
    }

    override fun bindingViewEvent() {
        with(viewModel) {
            viewEvent.observe(this@ResultFragment) {
                it.getContentIfNotHandled()?.let { event ->
                    when (event) {
                        ResultViewModel.EVENT_ON_CLICK_SHOW -> {
                            turnToWeb()
                        }
                        ResultViewModel.EVENT_ON_CLICK_SHARE -> {
                            openShare()
                        }
                    }
                }
            }
        }
    }

    private fun turnToWeb() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uriString))
        startActivity(intent)
    }

    private fun openShare() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, uriString)

        val shareIntent = Intent.createChooser(intent, "회의 공유하기")
        startActivity(shareIntent)
    }
}