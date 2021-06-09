package com.example.exmp1.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DbOrderData (var productId: String, var productData: String, var groupData: String, var quantityData: String, var unitsData: String,
                   var priceData: String, var extraChargeData: String, var sellingPriceData : String,
                   var sellingDate : String) : Parcelable