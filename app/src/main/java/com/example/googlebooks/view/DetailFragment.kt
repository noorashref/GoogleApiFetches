package com.example.googlebooks.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.googlebooks.R
import com.example.googlebooks.databinding.BookDetailFragmentLayoutBinding
import com.example.googlebooks.databinding.BookDisplayFragmentLayoutBinding


import com.example.googlebooks.model.presentation.VolumeItem
import com.squareup.picasso.Picasso

class DetailFragment :Fragment() {

    private lateinit var binding: BookDetailFragmentLayoutBinding
    companion object{
        private const val TAG = "Detail tag"
        const val DETAIL_BOOK = "DetailBook"
        fun newInstance(book: VolumeItem) : DetailFragment{
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DETAIL_BOOK,book)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = BookDetailFragmentLayoutBinding.inflate(inflater,container,false)

        arguments?.getParcelable<VolumeItem>(DETAIL_BOOK)?.let{volumeItem ->
            binding.tvBookAuthors.text = binding.root.context.getString(R.string.book_authors,volumeItem.authors.toString())
            binding.tvBookTitle.text = binding.root.context.getString((R.string.book_title),volumeItem.title.toString())
            Picasso.get().load(volumeItem.imageLinks.thumbnail).into(binding.ivBookCover)
        }
        binding.btnBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStackImmediate();
        }
        return binding.root
    }
}