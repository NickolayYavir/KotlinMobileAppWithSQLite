package com.example.exmp1

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exmp1.db.DbData
import com.example.exmp1.db.DbOrderData
import com.example.exmp1.db.MyDbManager
import com.example.exmp1.db.MyOrderDbManager
import java.io.File
import java.util.*

class CreateFIleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_file)

    }

    fun onClickFile1(view: View) {
        val  myDbManager = MyDbManager(this)
        myDbManager.openDb()
        val allProductData : ArrayList<DbData> = arrayListOf()
        allProductData.addAll(myDbManager.readData())
        myDbManager.closeDb()

        File(applicationContext.filesDir, "LeftoverGoods.txt").printWriter().use { out ->
            out.println("Goods leftover list")
            allProductData.forEach {
                out.println("ID: ${it.productId}, Product: ${it.productData}, Group: ${it.groupData}, Quantity: ${it.quantityData}," +
                        " ${it.unitsData}, B.Price: ${it.priceData}, Extra Charge: ${it.extraChargeData}%, S.Price: ${it.sellingPriceData}, " +
                        "Totals: ${it.totalBasicPriceData}, ${it.totalSellingPriceData}")
            }
            Toast.makeText(this, "File Created", Toast.LENGTH_SHORT).show()
        }
    }
    fun onClickFile2(view: View) {
        val  myOrderDbManager = MyOrderDbManager(this)
        myOrderDbManager.openDb()
        val allProductData : ArrayList<DbOrderData> = arrayListOf()
        allProductData.addAll(myOrderDbManager.readData())
        myOrderDbManager.closeDb()

        File(applicationContext.filesDir, "OrderList.txt").printWriter().use { out ->
            out.println("Order list")
            allProductData.forEach {
                out.println("Product: ${it.productData}, Group: ${it.groupData},  Quantity: ${it.quantityData} ${it.unitsData}, Price: ${it.sellingPriceData}," +
                        "Selling Date: ${java.text.SimpleDateFormat("yyyy-MM-dd").format(java.util.Date(it.sellingDate))}")
            }
            Toast.makeText(this, "File Created", Toast.LENGTH_SHORT).show()
        }
    }
}