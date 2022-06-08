package com.example.currency

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize

@VersionedParcelize
abstract class TargetP : Parcelable {
    var id : Int = 0
    var nama : String = ""
    var harga : String = ""
    var jangka : String = ""
}