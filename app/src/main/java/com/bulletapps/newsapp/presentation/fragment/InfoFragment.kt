package com.bulletapps.newsapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.bulletapps.newsapp.R
import com.bulletapps.newsapp.databinding.FragmentInfoBinding
import com.bulletapps.newsapp.presentation.activity.NewsActivity
import com.bulletapps.newsapp.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar


class InfoFragment : Fragment() {

    private lateinit var binding:FragmentInfoBinding
    private lateinit var viewModel:NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)
        val args : InfoFragmentArgs by navArgs()
        val article = args.selectedArticle

        viewModel = (activity as NewsActivity).mainViewModel

        binding.wvInfo.apply {
            webViewClient = WebViewClient()
            if (!article.url.isNullOrEmpty()){
                loadUrl( article.url!!)
            }

        }

        binding.fabSave.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view,"Salvo com sucesso!",Snackbar.LENGTH_SHORT).show()
        }

    }
}