package com.widsons.baseapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.widsons.article.data.api.ArticleAPI
import com.widsons.baseapplication.databinding.ActivityMainBinding
import com.widsons.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        val binding = DataBindingUtil.setContentView<ActivityMainbinding>()
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
    }

    override fun hideBottomNavigation() {
        binding.isShowBottomNavigation = false
    }

    override fun hideToolBar() {
       supportActionBar?.hide()
    }

    override fun showToolbar() {
        supportActionBar?.show()
    }

    override fun showBottomNavigation() {
        binding.isShowBottomNavigation = true
    }
}