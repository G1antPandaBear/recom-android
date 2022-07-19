package kr.hs.pandabear.recom.view.fragment

import androidx.fragment.app.viewModels
import kr.hs.pandabear.recom.databinding.FragmentSoloBinding
import kr.hs.pandabear.recom.view.base.BaseFragment
import kr.hs.pandabear.recom.viewmodel.fragment.SoloViewModel

class SoloFragment : BaseFragment<FragmentSoloBinding, SoloViewModel>() {
    override val viewModel: SoloViewModel by viewModels()

    override fun observerViewModel() {

    }

    override fun bindingViewEvent() {}
}