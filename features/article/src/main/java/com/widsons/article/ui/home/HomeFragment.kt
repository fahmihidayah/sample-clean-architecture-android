package com.widsons.article.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
    var categoryAdapter : CategoryAdapter = CategoryAdapter().apply {
        onClickItemListener = {
            viewModel.loadArticleByCategory(it)
        }
    }
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
            viewModel.listCategoryStateFlow.collect {
                when (it) {
                    is UIState.Loading -> {

                    }
                    is UIState.Success -> {
                        categoryAdapter.updateWhenChange(it.data?: listOf()) {
                            binding.categoryAdapter = categoryAdapter
                        }
                    }
                    is UIState.Error -> {

                    }
                    else -> {

                    }
                }

            }

        }

        lifecycleScope.launch {

            viewModel.listArticleStateFlow.collect {
                when (it) {
                    is UIState.Loading -> {

                    }
                    is UIState.Error -> {
                        showError(message = it.error)
                    }
                    is UIState.Success -> {
                        hideError()
                        homeAdapter.updateWhenChange(it.data?: listOf()) {
                            binding.articleAdapter = homeAdapter
                        }
                    }
                    else -> {

                    }
                }
            }
        }

        viewModel.initialLoadArticle()
    }


    override fun getErrorView(): View = binding.emptyLayout.root

    override fun getContentView(): View = binding.recyclerViewNews

    override fun getErrorText(): TextView = binding.emptyLayout.textViewEmpty
}