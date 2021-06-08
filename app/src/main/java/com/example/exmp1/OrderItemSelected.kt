package com.example.exmp1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exmp1.db.DbData
import kotlinx.android.synthetic.main.activity_order_item_selected.*
import kotlinx.android.synthetic.main.my_row_for_item_selected.*
import java.util.*

class OrderItemSelected : AppCompatActivity() {

    private var allProductData : ArrayList<DbData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_order_item_selected)
        getAndSetIntentData()
        val customAdapter = CustomAdapterForOrderItemSelected(this,allProductData)
        recyclerViewOrderItemSelected.adapter = customAdapter
        recyclerViewOrderItemSelected.layoutManager = LinearLayoutManager(this)

    }

    private fun getAndSetIntentData() {
        if(intent.hasExtra("productData")){
            // Getting data from intent
            val productData = intent.getParcelableExtra<DbData>("productData")
            allProductData.add(productData!!)

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show()
        }
    }

    fun onClickOrder(view: View) {

        if(Integer.parseInt(quantityInput.text.toString()) <= allProductData[0].quantityData.toInt() && Integer.parseInt(quantityInput.text.toString()) > 0){
            Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this, "Failed! Not enough goods", Toast.LENGTH_SHORT).show()
        }


    }

    fun onClickCancelOrder(view: View) {
        val intent = Intent(this, OrderActivity::class.java)
        startActivity(intent)
    }

}