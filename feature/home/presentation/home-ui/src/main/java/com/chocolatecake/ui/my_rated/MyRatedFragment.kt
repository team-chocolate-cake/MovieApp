package com.chocolatecake.ui.my_rated

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentMyRatedBinding
import com.chocolatecake.viewmodel.my_rated.MyRatedEvents
import com.chocolatecake.viewmodel.my_rated.MyRatedUiState
import com.chocolatecake.viewmodel.my_rated.MyRatedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyRatedFragment : BaseFragment<FragmentMyRatedBinding , MyRatedUiState , MyRatedEvents>() {

    override val layoutIdFragment: Int = R.layout.fragment_my_rated
    override val viewModel: MyRatedViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onEvent(event: MyRatedEvents) {
        when(event){
            is MyRatedEvents.NavigateToMovieDetails -> TODO()
            is MyRatedEvents.NavigateToTVShowDetails -> TODO()
            is MyRatedEvents.OnBackPressed -> TODO()
            is MyRatedEvents.ShowMyRatedMoviesPressed -> TODO()
            is MyRatedEvents.ShowMyRatedTvShowPressed -> TODO()
        }
    }


}