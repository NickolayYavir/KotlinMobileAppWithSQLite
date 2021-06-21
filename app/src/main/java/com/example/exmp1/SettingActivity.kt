package com.example.exmp1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.vicky.multilanguageexample.MyPreference
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    lateinit var myPreference: MyPreference
    var languageList = arrayOf("en","uk")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        myPreference = MyPreference(this)
        spinner.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,languageList)
        val lang:String = myPreference.getLoginCount()
        val index:Int = languageList.indexOf(lang)

        if(index >= 0){
            spinner.setSelection(index)
        }
    }



    fun OnClickSave(view: View) {
        myPreference.setLoginCount(languageList[spinner.selectedItemPosition])
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}