package com.bulletapps.newsapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bulletapps.newsapp.R
import com.bulletapps.newsapp.databinding.FragmentSavedNewsBinding
import com.bulletapps.newsapp.presentation.activity.NewsActivity
import com.bulletapps.newsapp.presentation.adapter.NewsAdapter
import com.bulletapps.newsapp.presentation.viewmodel.NewsViewModel


class SavedNewsFragment : Fragment() {
    private lateinit var binding:FragmentSavedNewsBinding
    private lateinit var viewModel:NewsViewModel
    private lateinit var newsAdapter:NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedNewsBinding.bind(view)
        viewModel = (activity as NewsActivity).mainViewModel
        newsAdapter = (activity as NewsActivity).newsAdapter

        initRecyclerView()
        getNews()
    }

    private fun getNews() {
        viewModel.getSavedNews().observe(viewLifecycleOwner,{
            newsAdapter.differ.submitList(it)
        })
    }

    private fun initRecyclerView() {
        binding.rvSaved.adapter = newsAdapter
        binding.rvSaved.setHasFixedSize(true)
        binding.rvSaved.layoutManager = LinearLayoutManager(requireContext())

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply{
                putSerializable("selected_article",it)
            }
            findNavController().navigate(R.id.action_savedNewsFragment_to_infoFragment,bundle)
        }
    }
}