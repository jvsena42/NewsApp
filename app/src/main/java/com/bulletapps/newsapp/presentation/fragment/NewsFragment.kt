package com.bulletapps.newsapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bulletapps.newsapp.R
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
        mViewModel.getNewsHeadLines("br",1)
        mViewModel.newsHeadLines.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let {
                        newsAdapter.differ.submitList(it.articles?.toList())
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

    private fun initRecyclerView() {
        binding.rvNews.adapter = newsAdapter
        binding.rvNews.setHasFixedSize(true)
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun showProgressBar(){
        binding.progressBar.viewVisible()
    }

    private fun hideProgressBar(){
        binding.progressBar.viewGone()
    }
}