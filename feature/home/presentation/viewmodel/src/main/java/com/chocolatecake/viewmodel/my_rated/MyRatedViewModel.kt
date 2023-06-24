package com.chocolatecake.viewmodel.my_rated

import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.usecase.my_rated.GetMyRatedMoviesUseCase
import com.chocolatecake.usecase.my_rated.GetMyRatedTVShowsUseCase
import javax.inject.Inject

class MyRatedViewModel @Inject constructor(
    private val getRatedTVShowsUseCase: GetMyRatedTVShowsUseCase,
    private val getRatedMoviesUseCase: GetMyRatedMoviesUseCase,
) : BaseViewModel<MyRatedUiState, MyRatedEvents>(MyRatedUiState()) {


}