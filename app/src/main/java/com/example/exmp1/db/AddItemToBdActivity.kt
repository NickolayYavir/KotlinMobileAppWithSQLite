package com.example.exmp1.db

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exmp1.R
import kotlinx.android.synthetic.main.activity_add_item_to_bd.*


class AddItemToBdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item_to_bd)
    }

    fun onClickSave(view: View) {
        val myDbManager = MyDbManager(this)

        myDbManager.closeDb()
        myDbManager.openDb()
        try {
            myDbManager.insertToDb(edProduct.text.toString(), edGroup.text.toString(), Integer.parseInt(edQuantity.text.toString()),
                edUnits.text.toString(), edPrice.text.toString().toFloat() , Integer.parseInt(edExtraCharge.text.toString()))
        }catch (e: Exception){
            //e.printStackTrace()
            Toast.makeText(this, "Wrong data input! Try again", Toast.LENGTH_SHORT).show()
        }

    }

}