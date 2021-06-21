package com.example.exmp1.db

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exmp1.R
import kotlinx.android.synthetic.main.activity_bd2.*
import java.util.*


class BdActivity : AppCompatActivity(), BdInterface{

    companion object{
        var sortMarkerColumn = "id"
        var sortMarkerMethod = " ASC"
    }

    private val  myDbManager = MyDbManager(this)

    private val allProductData : ArrayList<DbData> = arrayListOf()
    private val displayList : ArrayList<DbData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bd2)
        tvPositionFound.visibility = View.GONE
        tvFound.visibility = View.GONE
        myDbManager.openDb()
        storeDataInArrays(myDbManager.sortData(sortMarkerColumn, sortMarkerMethod))
        tvNumberOfPosition.text = allProductData.size.toString()
        
        //tvSellingPriceSum.text = totalSellingPrice.sum().toBigDecimal().setScale(2,RoundingMode.HALF_EVEN).toString()
        //tvSellingPriceSum.text = totalSellingPrice.sum().toBigDecimal().setScale(2,RoundingMode.HALF_EVEN).toString()
        val customAdapter = CustomAdapter(this,this, displayList)
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.my_menu, menu)

        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as? SearchView
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if(newText!!.isNotEmpty()){
                    displayList.clear()
                    val search = newText.lowercase(Locale.getDefault())
                    allProductData.forEach{
                        if(it.productData.lowercase().contains(search)){ displayList.add(it) }}
                    recyclerView.adapter!!.notifyDataSetChanged()
                    tvPositionFound.visibility = View.VISIBLE
                    tvFound.visibility = View.VISIBLE
                    tvPositionFound.text = displayList.size.toString()
                }else{
                    displayList.clear()
                    displayList.addAll(allProductData)
                    recyclerView.adapter!!.notifyDataSetChanged()
                    tvPositionFound.visibility = View.GONE
                    tvFound.visibility = View.GONE
                }
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_all){
            confirmDeleteAllDialog()
        }
        if(item.itemId == R.id.add_element){
            intent = Intent(this, AddItemToBdActivity::class.java)
            startActivity(intent)
        }
        if(item.itemId == R.id.sort_table){
            val dialog = SortDialogFragment()
            dialog.show(supportFragmentManager, "sortDialog")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmDeleteAllDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete All?")
        builder.setMessage("Are you sure you want to delete ALL data?")
        builder.setPositiveButton("Yes"){ _, _ ->
            myDbManager.closeDb()
            myDbManager.openDb()
            myDbManager.deleteAllData()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
            intent = Intent(this, BdActivity::class.java)
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("No"){ _, _ ->
        }
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            recreate()
        }
    }

    private fun storeDataInArrays(dataList :ArrayList<DbData>) {

        if(dataList.isEmpty()){
            imgNoData.visibility = View.VISIBLE
            tvNoData.visibility = View.VISIBLE
        }else{

            allProductData.clear()
            displayList.clear()
            allProductData.addAll(dataList)
            displayList.addAll(dataList)
            imgNoData.visibility = View.GONE
            tvNoData.visibility = View.GONE
        }
    }
    //rewrite StoreData
    override fun StoreData(sortColumn : String, sortMethod : String) {
        val method: String = if(sortMethod == "descending"){
            " DESC"
        }else{
            " ASC"
        }
        storeDataInArrays(myDbManager.sortData(sortColumn, method))
        recyclerView.adapter!!.notifyDataSetChanged()
        sortMarkerColumn = sortColumn
        sortMarkerMethod = method
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
}