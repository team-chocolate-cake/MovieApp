package com.chocolatecake.movieapp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.databinding.FragmentSearchBinding
import com.chocolatecake.movieapp.ui.base.BaseFragment


class SearchFragment() : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
}