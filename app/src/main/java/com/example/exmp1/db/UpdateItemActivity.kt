package com.example.exmp1.db

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.exmp1.R
import kotlinx.android.synthetic.main.activity_update_item.*

class UpdateItemActivity : AppCompatActivity() {

    private val myDbManager = MyDbManager(this)
    var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_item)
        getAndSetIntentData()

        val ab = supportActionBar
        ab?.title = edProductUpd.text.toString()
    }

    fun onClickUpdate(view: View) {
        myDbManager.closeDb()
        myDbManager.openDb()
        try {
            myDbManager.updateData(id,edProductUpd.text.toString(), edGroupUpd.text.toString(), Integer.parseInt(edQuantityUpd.text.toString()),
                edUnitsUpd.text.toString(), edPriceUpd.text.toString().toFloat() , Integer.parseInt(edExtraChargeUpd.text.toString()))
        }catch (e: Exception){
            Toast.makeText(this, "Failed! Wrong data input", Toast.LENGTH_SHORT).show()
        }
        val intent = Intent(this, BdActivity::class.java)
        startActivity(intent)
    }

    fun onClickDelete(view: View) {
        confirmDialog()
    }

    private fun getAndSetIntentData() {
        if(intent.hasExtra("id") &&intent.hasExtra("product") && intent.hasExtra("group") && intent.hasExtra("quantity") &&
            intent.hasExtra("units") && intent.hasExtra("price") && intent.hasExtra("charge")){
            // Getting data from intent
            id = intent.getStringExtra("id").toString()
            val product = intent.getStringExtra("product")
            val group = intent.getStringExtra("group")
            val quantity = intent.getStringExtra("quantity")
            val units = intent.getStringExtra("units")
            val price = intent.getStringExtra("price")
            val charge = intent.getStringExtra("charge")

            // Setting intent Data
            edProductUpd.setText(product)
            edGroupUpd.setText(group)
            edQuantityUpd.setText(quantity)
            edUnitsUpd.setText(units)
            edPriceUpd.setText(price)
            edExtraChargeUpd.setText(charge)

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun confirmDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete element?")
        builder.setMessage("Are you sure you want to delete this element?")
        builder.setPositiveButton("Yes"){ _, _ ->
            myDbManager.closeDb()
            myDbManager.openDb()
            myDbManager.deleteOneRow(id)
            intent = Intent(this, BdActivity::class.java)
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("No"){ _, _ ->
        }
        builder.show()
    }

}


