package com.monkeys.test.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StoreView(val name : String, val storeId : Int) : Parcelable