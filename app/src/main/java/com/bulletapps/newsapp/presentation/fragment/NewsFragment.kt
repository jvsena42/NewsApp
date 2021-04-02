package com.bulletapps.newsapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bulletapps.newsapp.R
import com.bulletapps.newsapp.data.model.NewsResponse
import com.bulletapps.newsapp.data.util.Resource
import com.bulletapps.newsapp.data.util.viewGone
import com.bulletapps.newsapp.data.util.viewVisible
import com.bulletapps.newsapp.databinding.FragmentNewsBinding
import com.bulletapps.newsapp.presentation.activity.NewsActivity
import com.bulletapps.newsapp.presentation.adapter.NewsAdapter
import com.bulletapps.newsapp.presentation.viewmodel.NewsViewModel


class NewsFragment : Fragment() {
    private lateinit var mViewModel: NewsViewModel
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter

    private var isScrolling = false
    private var page = 1
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        mViewModel = (activity as NewsActivity).mainViewModel
        newsAdapter = (activity as NewsActivity).newsAdapter
        initRecyclerView()
        viewNewsList()
    }

    private fun viewNewsList() {
        mViewModel.getNewsHeadLines("br",page)
        mViewModel.newsHeadLines.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let {
                        newsAdapter.differ.submitList(it.articles?.toList())

                        checkLastPage(it)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    it.message?.let {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                    }
                }

            }
        })
    }

    private fun checkLastPage(it: NewsResponse) {
        if (it.totalResults!! % 20 == 0) {
            pages = it.totalResults?.div(20)!!
        } else {
            pages = it.totalResults?.div(20)?.plus(1)!!
        }
        isLastPage = page == pages
    }

    private fun initRecyclerView() {
        binding.rvNews.adapter = newsAdapter
        binding.rvNews.setHasFixedSize(true)
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNews.addOnScrollListener(this@NewsFragment.onScrollListener)

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply{
                putSerializable("selected_article",it)
            }
            findNavController().navigate(R.id.action_newsFragment_to_infoFragment,bundle)
        }
    }

    private fun showProgressBar(){
        isLoading = true
        binding.progressBar.viewVisible()
    }

    private fun hideProgressBar(){
        isLoading = false
        binding.progressBar.viewGone()
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.rvNews.layoutManager as LinearLayoutManager
            val sizeCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount

            //Position of the star item of the current visible items
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition+visibleItems >= sizeCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            if (shouldPaginate){
                page++
                mViewModel.getNewsHeadLines("br",page)
                isScrolling = false
            }
        }
    }
}