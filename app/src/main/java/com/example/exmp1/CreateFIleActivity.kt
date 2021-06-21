package com.example.exmp1

import android.content.Intent
import android.os.Bundle
import android.provider.DocumentsContract
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exmp1.db.DbData
import com.example.exmp1.db.DbOrderData
import com.example.exmp1.db.MyDbManager
import com.example.exmp1.db.MyOrderDbManager
import java.io.File
import java.io.IOException
import java.util.*

class CreateFIleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_file)

    }

   val CREATE_FILE1 = 40
   val CREATE_FILE2 = 41

    fun onClickFile1(view: View) {

        try{
            val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply{
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "text/plain"
                putExtra(Intent.EXTRA_TITLE, "LeftoverGoods.txt")
                putExtra(DocumentsContract.EXTRA_INITIAL_URI,"")
                }
                startActivityForResult(intent, CREATE_FILE1)

        } catch (e: IOException){
            e.printStackTrace()
            Toast.makeText(this, "Can't create file", Toast.LENGTH_SHORT).show()
        }
    }

    fun onClickFile2(view: View) {

        try{
            val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply{
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "text/plain"
                putExtra(Intent.EXTRA_TITLE, "OrderList.txt")
                putExtra(DocumentsContract.EXTRA_INITIAL_URI,"")
            }
            startActivityForResult(intent, CREATE_FILE2)

        } catch (e: IOException){
            e.printStackTrace()
            Toast.makeText(this, "Can't create file", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == CREATE_FILE1 && resultCode == RESULT_OK){
            val uri = data!!.data
            try {
                val  myDbManager = MyDbManager(this)
                myDbManager.openDb()
                val allProductData : ArrayList<DbData> = arrayListOf()
                allProductData.addAll(myDbManager.readData())
                myDbManager.closeDb()

                val file = File(applicationContext.filesDir, "LeftoverGoods.txt")
                file.printWriter().use { out ->
                    out.println("Goods leftover list")
                    allProductData.forEach {
                        out.println("ID: ${it.productId}, Product: ${it.productData}, Group: ${it.groupData}, Quantity: ${it.quantityData}," +
                                " ${it.unitsData}, B.Price: ${it.priceData}, Extra Charge: ${it.extraChargeData}%, S.Price: ${it.sellingPriceData}, " +
                                "Totals: ${it.totalBasicPriceData}, ${it.totalSellingPriceData}\n")
                    }
                }

                val text = file.readText()
                val outputStream = this.contentResolver.openOutputStream(uri!!)
                outputStream?.write(text.toByteArray())
                outputStream?.close()
                Toast.makeText(this, "File Created", Toast.LENGTH_SHORT).show()

            }catch (e:Exception){
                print(e.localizedMessage)
            }
        }else if(requestCode == CREATE_FILE2 && resultCode == RESULT_OK){
            val uri = data!!.data
            try {
                val  myOrderDbManager = MyOrderDbManager(this)
                myOrderDbManager.openDb()
                val allProductData : ArrayList<DbOrderData> = arrayListOf()
                allProductData.addAll(myOrderDbManager.readData())
                myOrderDbManager.closeDb()

                val file = File(applicationContext.filesDir, "OrderList.txt")
                file.printWriter().use { out ->
                    out.println("Order list")
                    allProductData.forEach {
                        out.println("Product: ${it.productData}, Group: ${it.groupData},  Quantity: ${it.quantityData} ${it.unitsData}, Price: ${it.sellingPriceData}," +
                                "Selling Date: ${java.text.SimpleDateFormat("yyyy-MM-dd").format(java.util.Date(it.sellingDate))}\n")
                    }
                }

                val text = file.readText()
                val outputStream = this.contentResolver.openOutputStream(uri!!)
                outputStream?.write(text.toByteArray())
                outputStream?.close()
                Toast.makeText(this, "File Created", Toast.LENGTH_SHORT).show()

            }catch (e:Exception){
                print(e.localizedMessage)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


}