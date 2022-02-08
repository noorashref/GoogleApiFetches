package com.example.googlebooks.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.googlebooks.R
import com.example.googlebooks.databinding.BookDisplayFragmentLayoutBinding
import com.example.googlebooks.model.presentation.BookResponse
import com.example.googlebooks.model.presentation.VolumeItem
import com.example.googlebooks.adapter.BookAdapter

class BookListFragment : Fragment() {

    companion object{
        const val BOOK_RESPONSE_DATA = "Book Response Data"
        fun newInstance(bookResponse: BookResponse) : BookListFragment{
            var fragment = BookListFragment()
            val bundle = Bundle()
            bundle.putParcelable(BOOK_RESPONSE_DATA,bookResponse)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding: BookDisplayFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = BookDisplayFragmentLayoutBinding.inflate(inflater,container,false)
        arguments?.let {bundle ->

            bundle.getParcelable<BookResponse>(BOOK_RESPONSE_DATA)?.let {
                initViews(it)
            }
        }
        return binding.root
    }

    private fun initViews(dataSet: BookResponse) {
        binding.bookListResult.layoutManager = GridLayoutManager(context,2)
        binding.bookListResult.adapter = BookAdapter(dataSet.items.map {
            VolumeItem(
                it.volumeInfo.title,
                it.volumeInfo.authors,
                it.volumeInfo.imageLinks
            )
        }){
            requireActivity().openDisplayFragment(it)
        }
    }
    private fun FragmentActivity.openDisplayFragment(book: VolumeItem){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_display,DetailFragment.newInstance(book))
            .addToBackStack(DetailFragment.javaClass.name)
            .commit()
    }
}
