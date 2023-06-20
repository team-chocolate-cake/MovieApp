package com.chocolatecake.ui.myList

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.myList.MyListListener
import com.chocolatecake.viewmodel.myList.MyListUiState



class  MyListAdapter(items: List<MyListUiState>, listener: MyListListener):
    BaseAdapter<MyListUiState>(items, listener) {

    override val layoutID = R.layout.item_my_list
}