package com.example.googlebooks.model.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookResponse(
    val items: List<BookItem>
) : Parcelable


@Parcelize
data class BookItem(
    val volumeInfo: VolumeItem
) : Parcelable

@Parcelize
data class VolumeItem(
    val title: String,
    val authors: List<String>,
    val imageLinks : ImageItem

) : Parcelable

@Parcelize
data class ImageItem(
    val thumbnail: String
) : Parcelable

