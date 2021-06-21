package com.example.exmp1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.archive_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_all){
            confirmDeleteAllDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmDeleteAllDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete All?")
        builder.setMessage("Are you sure you want to delete ALL data?")
        builder.setPositiveButton("Yes"){ _, _ ->
            myOrderDbManager.deleteAllData()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("No"){ _, _ ->
        }
        builder.show()
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