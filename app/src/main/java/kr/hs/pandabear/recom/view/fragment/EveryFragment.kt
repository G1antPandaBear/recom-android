package kr.hs.pandabear.recom.view.fragment

import androidx.fragment.app.viewModels
import kr.hs.pandabear.recom.databinding.FragmentEveryBinding
import kr.hs.pandabear.recom.databinding.FragmentSoloBinding
import kr.hs.pandabear.recom.view.base.BaseFragment
import kr.hs.pandabear.recom.viewmodel.fragment.EveryViewModel
import kr.hs.pandabear.recom.viewmodel.fragment.SoloViewModel

class EveryFragment : BaseFragment<FragmentEveryBinding, EveryViewModel>() {
    override val viewModel: EveryViewModel by viewModels()

    override fun observerViewModel() {

    }

    override fun bindingViewEvent() {}
}