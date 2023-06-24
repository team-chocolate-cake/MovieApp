package com.chocolatecake.ui.jigsaw_puzzle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.bases.BaseInteractionListener
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.ui.trivia.databinding.PickThumbnailCardRecyclerItemBinding
import com.chocolatecake.ui.trivia.databinding.PickThumbnailTitleRecyclerItemBinding
import com.chocolatecake.viewmodel.jigsaw_puzzle.PickAThumbnailRecyclerItem

class PickAThumbnailAdapter(
    var list: List<PickAThumbnailRecyclerItem>,
    listener: BaseInteractionListener
) : BaseAdapter<PickAThumbnailRecyclerItem>(list, listener) {
    override val layoutID = -1

    class TitleViewHolder(val binding: PickThumbnailTitleRecyclerItemBinding) :
        BaseViewHolder(binding)

    class CardViewHolder(val binding: PickThumbnailCardRecyclerItemBinding) :
        BaseViewHolder(binding)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            CARD_ITEM -> CardViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.pick_thumbnail_card_recycler_item,
                    parent,
                    false
                )
            )

            TITLE_ITEM -> TitleViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.pick_thumbnail_title_recycler_item,
                    parent,
                    false
                )
            )

            else -> throw Throwable("recycler item type not found")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is CardViewHolder -> bindCard(holder, list[position])
            is TitleViewHolder -> bindTitle(holder, list[position])
        }

    }

    private fun bindTitle(holder: TitleViewHolder, item: PickAThumbnailRecyclerItem) {
        holder.binding.item = (item as PickAThumbnailRecyclerItem.Title).text
    }

    private fun bindCard(holder: CardViewHolder, item: PickAThumbnailRecyclerItem) {
        holder.binding.item = (item as PickAThumbnailRecyclerItem.Card).item
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is PickAThumbnailRecyclerItem.Card -> CARD_ITEM
            is PickAThumbnailRecyclerItem.Title -> TITLE_ITEM
        }
    }
    override fun setItems(newItems: List<PickAThumbnailRecyclerItem>) {
        list = newItems
        super.setItems(newItems)
    }
    companion object {
        private const val CARD_ITEM = 214
        private const val TITLE_ITEM = 22141
    }


}