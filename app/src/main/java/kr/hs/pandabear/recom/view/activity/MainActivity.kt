package kr.hs.pandabear.recom.view.activity

import androidx.activity.viewModels
import kr.hs.pandabear.recom.databinding.ActivityMainBinding
import kr.hs.pandabear.recom.view.base.BaseActivity
import kr.hs.pandabear.recom.viewmodel.activity.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()

    override fun observerViewModel() {

    }

    override fun bindingViewEvent() {}
}