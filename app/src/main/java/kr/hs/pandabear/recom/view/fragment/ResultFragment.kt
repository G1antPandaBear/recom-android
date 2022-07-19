package kr.hs.pandabear.recom.view.fragment

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import kr.hs.pandabear.recom.databinding.FragmentResultBinding
import kr.hs.pandabear.recom.view.base.BaseFragment
import kr.hs.pandabear.recom.viewmodel.fragment.ResultViewModel
import kr.hs.pandabear.recom.widget.Constants


class ResultFragment : BaseFragment<FragmentResultBinding, ResultViewModel>() {
    override val viewModel: ResultViewModel by viewModels()
    private val args: ResultFragmentArgs by navArgs()

    override fun observerViewModel() {

    }

    override fun bindingViewEvent() {
        with(viewModel) {
            viewEvent.observe(this@ResultFragment) {
                it.getContentIfNotHandled()?.let { event ->
                    when (event) {
                        ResultViewModel.EVENT_ON_CLICK_SHOW -> {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(
                                    Constants.BASE_URL +
                                            "/document/" +
                                            args.code
                                )
                            )
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }

}