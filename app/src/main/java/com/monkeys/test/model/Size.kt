package com.monkeys.test.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Size(val variantId: String, val name: String, val stockQty: Int) : Parcelable