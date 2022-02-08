package com.example.googlebooks.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.googlebooks.R
import com.example.googlebooks.databinding.ItemLayoutBinding
import com.example.googlebooks.model.presentation.VolumeItem
import com.squareup.picasso.Picasso

class BookViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)  {

    fun onBind(volumeItem: VolumeItem,callback:(VolumeItem)->Unit){

        binding.tvBookAuthors.text = binding.root.context.getString(R.string.book_authors,volumeItem.authors.toString())
        binding.tvBookTitle.text = binding.root.context.getString((R.string.book_title),volumeItem.title.toString())

        Picasso.get().load(volumeItem.imageLinks.thumbnail).into(binding.ivBookCover)
        binding.root.setOnClickListener{
            callback(volumeItem)
        }
    }
}