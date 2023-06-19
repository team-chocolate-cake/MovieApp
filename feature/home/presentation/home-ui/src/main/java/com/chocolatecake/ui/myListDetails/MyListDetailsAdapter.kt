package com.chocolatecake.ui.myListDetails
import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.common.model.MovieHorizontalUIState
import com.chocolatecake.viewmodel.myListDetails.MyListDetailsListener
import com.chocolatecake.viewmodel.myListDetails.MyListDetailsUiState


class  MyListDetailsAdapter( items: List<MyListDetailsUiState>, listener: MyListDetailsListener):
    BaseAdapter<MyListDetailsUiState>(items, listener) {

    override val layoutID = R.layout.item_my_list_details
}