package com.ruhul.letschatdemo.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class ModelMobile(
    var country: String="", var number: String=""): Parcelable
