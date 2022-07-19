package kr.hs.pandabear.recom.view.activity

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.pandabear.recom.R
import kr.hs.pandabear.recom.databinding.ActivityMainBinding
import kr.hs.pandabear.recom.view.base.BaseActivity
import kr.hs.pandabear.recom.view.fragment.SoloFragment
import kr.hs.pandabear.recom.viewmodel.activity.MainViewModel

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()

    private val requiredPermission = arrayOf(
        Manifest.permission.RECORD_AUDIO
    )

    override fun observerViewModel() {
        requestAudioPermission()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val audioRecordPermissionGranted =
            requestCode == REQUEST_RECORD_AUDIO_PERMISSION &&
                    grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED

        if (!audioRecordPermissionGranted) {
            finish()
        }
    }

    private fun requestAudioPermission() {
        requestPermissions(requiredPermission, REQUEST_RECORD_AUDIO_PERMISSION)
    }

/*
    private fun beginTransaction() {
        val fragment: Fragment = SoloFragment()
        supportFragmentManager.beginTransaction()
            .add(mBinding.fragmentContainerView.id, fragment)
            .commit()
    }*/

    companion object {
        private const val REQUEST_RECORD_AUDIO_PERMISSION = 200
    }
}