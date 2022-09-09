package kr.hs.pandabear.recom.presentation.feature.main

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.pandabear.recom.R
import kr.hs.pandabear.recom.databinding.ActivityMainBinding
import kr.hs.pandabear.recom.presentation.base.BaseActivity
import java.lang.Exception
import java.util.Locale

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()

    private val requiredPermission = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    var subject: String = ""
    var address: String = ""

    override fun observerViewModel() {
        requestPermission()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        val intent = intent
        subject = intent.getStringExtra("subject") ?: "무제"
        mBinding.tvSubject.text = intent.getStringExtra("subject") ?: "무제"
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
            requestCode == REQUEST_PERMISSION_CODE &&
                grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED

        if (!audioRecordPermissionGranted) {
            finish()
        } else {
            address = getGPSLocation()
            Log.d("TestTest", "onRequestPermissionsResult: $address")
        }
    }

    private fun getGPSLocation(): String {
        val locationManager: LocationManager = this.getSystemService(LOCATION_SERVICE) as LocationManager
        val currentLocation: Location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)!!
        val lat = currentLocation.latitude // 위도
        val lon = currentLocation.longitude // 경도

        val geocoder = Geocoder(this, Locale.KOREA)
        var addr = "주소 오류"

        try {
            addr = geocoder.getFromLocation(lat, lon, 1).first().getAddressLine(0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return addr
    }

    private fun requestPermission() {
        requestPermissions(requiredPermission, REQUEST_PERMISSION_CODE)
    }

/*
    private fun beginTransaction() {
        val fragment: Fragment = SoloFragment()
        supportFragmentManager.beginTransaction()
            .add(mBinding.fragmentContainerView.id, fragment)
            .commit()
    }*/

    companion object {
        private const val REQUEST_PERMISSION_CODE = 200
    }
}
