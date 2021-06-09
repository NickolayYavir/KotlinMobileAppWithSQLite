package com.example.exmp1

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exmp1.db.DbData
import com.example.exmp1.db.MyDbManager
import com.example.exmp1.db.MyOrderDbManager
import kotlinx.android.synthetic.main.activity_order_item_selected.*
import kotlinx.android.synthetic.main.my_row_for_item_selected.*
import java.text.SimpleDateFormat
import java.util.*

class OrderItemSelected : AppCompatActivity() {

    private var allProductData : ArrayList<DbData> = arrayListOf()

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_order_item_selected)
        getAndSetIntentData()

        // calendar datePicker
        editTextDate.setText(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()))
        val myCalendar = Calendar.getInstance()
        val maxDate: Date? = null
        val datePickerOnDataSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                editTextDate.setText(sdf.format(myCalendar.time))
            }
        editTextDate.setOnClickListener {
            DatePickerDialog(
                this, datePickerOnDataSetListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).run {
                maxDate?.time?.also { datePicker.maxDate = it }
                show()
            }
        }
        // -------------------------------------

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

            val myDbManager = MyDbManager(this)

            allProductData[0].quantityData = (allProductData[0].quantityData.toInt() - Integer.parseInt(quantityInput.text.toString())).toString()
            myDbManager.openDb()
            myDbManager.updateData(allProductData[0].productId,allProductData[0].productData,allProductData[0].groupData,allProductData[0].quantityData.toInt(),
                allProductData[0].unitsData,allProductData[0].priceData.toFloat(),allProductData[0].extraChargeData.toInt())
            myDbManager.closeDb()

            val myOrderDbManager = MyOrderDbManager(this)
            myOrderDbManager.openDb()
            myOrderDbManager.insertToDb(allProductData[0].productData,allProductData[0].groupData,Integer.parseInt(quantityInput.text.toString()),
                allProductData[0].unitsData,allProductData[0].priceData.toFloat(),allProductData[0].extraChargeData.toInt(),
                ((SimpleDateFormat("yyyy-MM-dd").parse(editTextDate.text.toString()) as Date).time))
            myOrderDbManager.closeDb()

            Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }else if(Integer.parseInt(quantityInput.text.toString()) == 0){
            Toast.makeText(this, "Failed! You selected equals ZERO", Toast.LENGTH_SHORT).show()
        }
        else{  Toast.makeText(this, "Failed! Not enough goods", Toast.LENGTH_SHORT).show() }

    }

    fun onClickCancelOrder(view: View) {
        val intent = Intent(this, OrderActivity::class.java)
        startActivity(intent)
    }

}