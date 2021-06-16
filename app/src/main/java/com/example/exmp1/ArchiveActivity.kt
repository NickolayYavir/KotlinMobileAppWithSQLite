package com.example.exmp1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exmp1.db.DbOrderData
import com.example.exmp1.db.MyOrderDbManager
import kotlinx.android.synthetic.main.activity_archive.*
import kotlinx.android.synthetic.main.activity_bd2.imgNoData
import kotlinx.android.synthetic.main.activity_bd2.tvNoData
import java.util.*

class ArchiveActivity : AppCompatActivity() {

    private val myOrderDbManager = MyOrderDbManager(this)

    private val allArchiveData : ArrayList<DbOrderData> = arrayListOf()
    private val displayList : ArrayList<DbOrderData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archive)
        myOrderDbManager.openDb()
        storeDataInArrays(myOrderDbManager.sortData())
        displayList.addAll(myOrderDbManager.sortDataDesc())

        val customAdapter = CustomAdapterForArchive(this, displayList)
        recyclerViewArchive.adapter = customAdapter
        recyclerViewArchive.layoutManager = LinearLayoutManager(this)
    }

    private fun storeDataInArrays(dataList :ArrayList<DbOrderData>) {

        if(dataList.isEmpty()){
            imgNoData.visibility = View.VISIBLE
            tvNoData.visibility = View.VISIBLE
        }else{

            allArchiveData.clear()
            allArchiveData.addAll(dataList)
            imgNoData.visibility = View.GONE
            tvNoData.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myOrderDbManager.closeDb()
    }
}