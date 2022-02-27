package com.ravitej.dependenciesapp.ui.splash

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.ravitej.dependenciesapp.data.entities.CharacterDetail
import com.ravitej.dependenciesapp.databinding.ItemCharacterBinding

class CharacterListAdapter :
    ListAdapter<CharacterDetail, CharacterListAdapter.CharacterItemViewHolder>(CharacterDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterItemViewHolder(binding, binding.root)
    }

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CharacterItemViewHolder(
        private val binding: ItemCharacterBinding,
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(item: CharacterDetail) {
            binding.name.text = item.name
            binding.speciesAndStatus.text = "${item.species} - ${item.status}"
            Glide.with(binding.root)
                .load(item.image)
                .transform(CircleCrop())
                .into(binding.image)
        }
    }

    object CharacterDiff : DiffUtil.ItemCallback<CharacterDetail>() {
        override fun areContentsTheSame(oldItem: CharacterDetail, newItem: CharacterDetail): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: CharacterDetail, newItem: CharacterDetail): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
