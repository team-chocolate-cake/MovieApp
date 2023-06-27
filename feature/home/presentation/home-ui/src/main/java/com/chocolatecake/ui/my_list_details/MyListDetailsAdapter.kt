package com.chocolatecake.ui.my_list_details
import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.my_list_details.MyListDetailsListener
import com.chocolatecake.viewmodel.my_list_details.MyListDetailsUiState



class  MyListDetailsAdapter(items: List<MyListDetailsUiState>, listener: MyListDetailsListener):
    BaseAdapter<MyListDetailsUiState>(items, listener) {

    override val layoutID = R.layout.item_my_list_details
}