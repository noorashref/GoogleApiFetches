package com.example.googlebooks.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.googlebooks.MainActivity
import com.example.googlebooks.R
import com.example.googlebooks.databinding.BookSearchFragmentLayoutBinding
import com.example.googlebooks.hideKeyboard

class SearchBookFragment : Fragment() {

    private lateinit var binding: BookSearchFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = BookSearchFragmentLayoutBinding.inflate(inflater,container,false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.bookType.apply{
            adapter =
                ArrayAdapter<String>(
                    this@SearchBookFragment.requireContext(),
                    android.R.layout.simple_list_item_1,
                    this@SearchBookFragment.requireContext().resources.getStringArray(R.array.book_type)
                )
        }
        binding.btnBookSearch.setOnClickListener {
            it.hideKeyboard()
            requireActivity().doSearchBook(
                binding.tilBookSearch.editText?.text.toString(),
                binding.bookType.selectedItem.toString(),
                binding.maxSearch.value.toInt()
            )
        }
    }


    fun FragmentActivity?.doSearchBook(bookTitle:String, bookType: String, maxResult: Int){
        if(requireActivity() is MainActivity)
            (requireActivity() as MainActivity).executeRetrofit(bookTitle,bookType,maxResult)
    }
    }

