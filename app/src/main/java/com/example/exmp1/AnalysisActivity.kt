package com.example.exmp1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exmp1.db.DbOrderData
import com.example.exmp1.db.MyOrderDbManager
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_analysis.*


class AnalysisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analysis)
        setLineChartData()


    }

    fun setLineChartData() {

        val myOrderDbManager = MyOrderDbManager(this)
        myOrderDbManager.openDb()
        val allProductData : ArrayList<DbOrderData> = arrayListOf()
        allProductData.addAll(myOrderDbManager.sortData())
        myOrderDbManager.closeDb()




        val Date : ArrayList<String> = arrayListOf()
        for(i in allProductData.indices){
            Date.add(java.text.SimpleDateFormat("yyyy-MM-dd").format(java.util.Date(allProductData[i].sellingDate)))
        }
        val DateArray = LinkedHashSet<String>(Date)
        val xvalue = ArrayList<String>()
        xvalue.addAll(DateArray)

        var controler = java.text.SimpleDateFormat("yyyy-MM-dd").format(java.util.Date(allProductData[0].sellingDate))
        var dayQuantity = 0
        val quantity : ArrayList<Int> = arrayListOf()

        for(i in allProductData.indices){
            if(java.text.SimpleDateFormat("yyyy-MM-dd").format(java.util.Date(allProductData[i].sellingDate)) == controler){
                dayQuantity += allProductData[i].quantityData
            }else{
                controler = java.text.SimpleDateFormat("yyyy-MM-dd").format(java.util.Date(allProductData[i].sellingDate))
                quantity.add(dayQuantity)
                dayQuantity = allProductData[i].quantityData
            }
        }
        quantity.add(dayQuantity)
        val lineentry = ArrayList<Entry>()
        for (i in xvalue.indices){
            lineentry.add(Entry(quantity[i].toFloat(),i))
        }

        val linedataset = LineDataSet(lineentry, "Quantity")
        linedataset.color = resources.getColor(R.color.purple_700)

        val data = LineData(xvalue, linedataset)
        lineChart.data = data
        lineChart.animateXY(3000,3000)


    }
}