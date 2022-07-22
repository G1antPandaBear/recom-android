package kr.hs.pandabear.recom.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.pandabear.recom.R
import kr.hs.pandabear.recom.databinding.ActivityStartBinding
import java.lang.reflect.ParameterizedType
import java.util.*

class StartActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityStartBinding
    val subject = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    fun onClickStartButton() {
        // 모드 0은 솔로, 모드 1은 단체
        val intent = Intent(this, MainActivity::class.java)
        if (subject.value.isNullOrEmpty())
            subject.value = "무제"
        Log.d("TestTest", "onClickStartButtons: ${subject.value}")
        intent.putExtra("subject", subject.value)
        startActivity(intent)
    }

    private fun performDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_start)
        mBinding.lifecycleOwner = this
        mBinding.activity = this
        mBinding.executePendingBindings()
    }
}