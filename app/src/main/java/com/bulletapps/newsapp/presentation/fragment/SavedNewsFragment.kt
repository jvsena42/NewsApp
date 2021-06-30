package com.bulletapps.newsapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bulletapps.newsapp.R
import com.bulletapps.newsapp.databinding.FragmentSavedNewsBinding
import com.bulletapps.newsapp.presentation.activity.NewsActivity
import com.bulletapps.newsapp.presentation.adapter.NewsAdapter
import com.bulletapps.newsapp.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar


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
        initCallbackRecycler(view)
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

    private fun initCallbackRecycler(view: View) {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = newsAdapter.differ.currentList[position]
                viewModel.deleteArticle(article)
                Snackbar.make(view,"Deletado com sucesso",Snackbar.LENGTH_SHORT)
                    .apply {
                        setAction("Desfazer"){
                            viewModel.saveArticle(article)
                        }
                        show()
                    }
            }

        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvSaved)
        }
    }
}