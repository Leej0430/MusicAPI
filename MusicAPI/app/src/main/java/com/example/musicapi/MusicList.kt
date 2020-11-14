package com.example.musicapi

import android.os.Parcelable
import android.os.Parcel
import kotlinx.android.parcel.Parcelize
@Parcelize
data class MusicList(
    val resultCount:Int,
    val results :List<Musicinfo>
):Parcelable


@Parcelize
data class Musicinfo(
    val artistName: String,
    val collectionName:String,
    val artworkUrl60: String,
    val trackPrice: String,
    val previewUrl:String
):Parcelable