package kr.hs.pandabear.recom.view.activity

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import kr.hs.pandabear.recom.databinding.ActivityMainBinding
import kr.hs.pandabear.recom.view.base.BaseActivity
import kr.hs.pandabear.recom.view.fragment.EveryFragment
import kr.hs.pandabear.recom.view.fragment.SoloFragment
import kr.hs.pandabear.recom.viewmodel.activity.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()

    override fun observerViewModel() {
        beginTransaction()
    }

    override fun bindingViewEvent() {
        with(viewModel) {
            viewEvent.observe(this@MainActivity) {
                it.getContentIfNotHandled()?.let { event ->
                    when (event) {
                        MainViewModel.EVENT_ON_CLICK_BACK -> {
                            finish()
                        }
                    }
                }
            }
        }
    }

    private fun beginTransaction() {
        val fragment: Fragment = selectFragment()
        supportFragmentManager.beginTransaction()
            .add(mBinding.fragmentContainerView.id, fragment)
            .commit()
    }

    private fun selectFragment(): Fragment {
        val intent = intent
        return when (intent.getIntExtra("mode", -1)) {
            0 -> SoloFragment()
            1 -> EveryFragment()
            else -> SoloFragment()
        }
    }
}