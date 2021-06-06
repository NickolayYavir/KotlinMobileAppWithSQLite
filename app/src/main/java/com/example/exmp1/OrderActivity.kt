package com.example.exmp1

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exmp1.db.DbData
import com.example.exmp1.db.MyDbManager
import kotlinx.android.synthetic.main.activity_bd2.*
import kotlinx.android.synthetic.main.activity_bd2.imgNoData
import kotlinx.android.synthetic.main.activity_bd2.tvFound
import kotlinx.android.synthetic.main.activity_bd2.tvNoData
import kotlinx.android.synthetic.main.activity_bd2.tvNumberOfPosition
import kotlinx.android.synthetic.main.activity_bd2.tvPositionFound
import kotlinx.android.synthetic.main.activity_order.*
import java.util.*

class OrderActivity : AppCompatActivity() {

    private val myDbManager = MyDbManager(this)
    private val allProductData : ArrayList<DbData> = arrayListOf()
    private val displayList : ArrayList<DbData> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        tvPositionFound.visibility = View.GONE
        tvFound.visibility = View.GONE
        myDbManager.openDb()
        storeDataInArrays(myDbManager.readData())

     /*   storeDataInArrays(myDbManager.sortData(
            BdActivity.sortMarkerColumn,
            BdActivity.sortMarkerMethod
        ))*/

        tvNumberOfPosition.text = allProductData.size.toString()

        val customAdapter = CustomAdapterForOrder(this,this,displayList)
        recyclerViewOrder.adapter = customAdapter
        recyclerViewOrder.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.order_menu, menu)

        val menuItem = menu?.findItem(R.id.menu_search_Order)
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

        if(item.itemId == R.id.sort_table_Order){
           // val dialog = SortDialogFragment()
            //dialog.show(supportFragmentManager, "sortDialog")
        }
        return super.onOptionsItemSelected(item)
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

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

}