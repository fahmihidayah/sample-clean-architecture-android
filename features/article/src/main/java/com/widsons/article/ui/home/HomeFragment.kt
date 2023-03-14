package com.widsons.article.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.widsons.article.R
import com.widsons.article.databinding.HomeFragmentBinding
import com.widsons.core.state.UIState
import com.widsons.ui.base.BaseViewModelFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseViewModelFragment<HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()

    var homeAdapter : HomeAdapter = HomeAdapter()
    var categoryAdapter : CategoryAdapter = CategoryAdapter()
    lateinit var binding : HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.home_fragment, container, false)
        getBaseActivity().showBottomNavigation()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.listArticleStateFlow.collect {
                when (it) {
                    is UIState.Loading -> {

                    }
                    is UIState.Error -> {

                    }
                    is UIState.Success -> {
                        homeAdapter = HomeAdapter(it.data?.articles?: listOf())
                        categoryAdapter = CategoryAdapter(it.data?.categories?: listOf())
                        binding.articleAdapter = homeAdapter
                        binding.categoryAdapter = categoryAdapter
                    }
                    else -> {

                    }
                }
            }
        }

        viewModel.initialLoadArticle()
    }


}