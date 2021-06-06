package com.example.exmp1.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import java.math.RoundingMode

class MyDbManager(private val context: Context) {

    private val myDbHelper = MyDbHelper(context)
    private var db: SQLiteDatabase? = null

    fun openDb(){
        db = myDbHelper.writableDatabase
    }
    fun insertToDb( product: String, groupName: String, quantity: Int, units: String, price: Float, extraCharge: Int){
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_PRODUCT, product)
            put(MyDbNameClass.COLUMN_NAME_GROUP, groupName)
            put(MyDbNameClass.COLUMN_NAME_QUANTITY, quantity)
            put(MyDbNameClass.COLUMN_NAME_UNITS, units)
            put(MyDbNameClass.COLUMN_NAME_PRICE, price)
            put(MyDbNameClass.COLUMN_NAME_EXTRA_CHARGE, extraCharge)
            put(MyDbNameClass.COLUMN_SELLING_PRICE, (price+(price*(extraCharge.toFloat() / 100.0f))).toBigDecimal().setScale(2,
                RoundingMode.HALF_EVEN).toFloat())
            put(MyDbNameClass.COLUMN_TOTAL_SELLING_PRICE, (quantity*(price+(price*(extraCharge.toFloat() /100.0f)))).toBigDecimal().setScale(2,
                RoundingMode.HALF_EVEN).toFloat())
            put(MyDbNameClass.COLUMN_TOTAL_BASIC_PRICE, (price * quantity).toBigDecimal().setScale(2,
                    RoundingMode.HALF_EVEN).toFloat())
        }
        val result = db?.insert(MyDbNameClass.TABLE_NAME,null,values)

        if(result!!.toInt() == -1){
            Toast.makeText(context, "Failed to Add", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Successfully Added!", Toast.LENGTH_SHORT).show()
        }
    }

    fun readData() : ArrayList<DbData>{
        val dataList = ArrayList<DbData>()
        val cursor = db?.query(MyDbNameClass.TABLE_NAME,null,null,null,null,null,null)


        while(cursor?.moveToNext()!!){
            val dataId = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_ID))
            val dataProduct = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_PRODUCT))
            val dataGroup = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_GROUP))
            val dataQuantity = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_QUANTITY))
            val dataUnits = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_UNITS))
            val dataPrice = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_PRICE))
            val dataCharge = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_EXTRA_CHARGE))
            val dataSellingPrice = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_SELLING_PRICE))
            val dataTotalSellingPrice = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_TOTAL_SELLING_PRICE))
            val dataTotalBasicPrice = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_TOTAL_BASIC_PRICE))
            dataList.add(DbData(dataId,dataProduct,dataGroup,dataQuantity,dataUnits,dataPrice,dataCharge,dataSellingPrice,dataTotalSellingPrice,dataTotalBasicPrice))
        }
        cursor.close()
        return dataList
    }

    fun sortData(sortedBy :String, sortedMethod :String) : ArrayList<DbData>{

        val dataList = ArrayList<DbData>()
        val cursor = db?.query(MyDbNameClass.TABLE_NAME, null, null, null, null, null, sortedBy + sortedMethod)
        while(cursor?.moveToNext()!!){
            val dataId = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_ID))
            val dataProduct = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_PRODUCT))
            val dataGroup = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_GROUP))
            val dataQuantity = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_QUANTITY))
            val dataUnits = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_UNITS))
            val dataPrice = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_PRICE))
            val dataCharge = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_EXTRA_CHARGE))
            val dataSellingPrice = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_SELLING_PRICE))
            val dataTotalSellingPrice = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_TOTAL_SELLING_PRICE))
            val dataTotalBasicPrice = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_TOTAL_BASIC_PRICE))
            dataList.add(DbData(dataId,dataProduct,dataGroup,dataQuantity,dataUnits,dataPrice,dataCharge,dataSellingPrice,dataTotalSellingPrice,dataTotalBasicPrice))
        }
        cursor.close()
        return dataList
    }

    fun updateData(row_id: String, product: String, groupName: String, quantity: Int, units: String, price: Float, extraCharge: Int) {
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_PRODUCT, product)
            put(MyDbNameClass.COLUMN_NAME_GROUP, groupName)
            put(MyDbNameClass.COLUMN_NAME_QUANTITY, quantity)
            put(MyDbNameClass.COLUMN_NAME_UNITS, units)
            put(MyDbNameClass.COLUMN_NAME_PRICE, price)
            put(MyDbNameClass.COLUMN_NAME_EXTRA_CHARGE, extraCharge)
            put(MyDbNameClass.COLUMN_SELLING_PRICE, (price+(price*(extraCharge.toFloat() / 100.0f))).toBigDecimal().setScale(2,
                RoundingMode.HALF_EVEN).toFloat())
            put(MyDbNameClass.COLUMN_TOTAL_SELLING_PRICE, (quantity*(price+(price*(extraCharge.toFloat() /100.0f)))).toBigDecimal().setScale(2,
                RoundingMode.HALF_EVEN).toFloat())
            put(MyDbNameClass.COLUMN_TOTAL_BASIC_PRICE, (price * quantity).toBigDecimal().setScale(2,
                RoundingMode.HALF_EVEN).toFloat())
        }
        val result = db?.update(MyDbNameClass.TABLE_NAME, values, "id=?", arrayOf(row_id))

        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteOneRow(row_id :String) {
        val result = db?.delete(MyDbNameClass.TABLE_NAME, "id=?", arrayOf(row_id))
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteAllData() {
        db?.execSQL("DELETE FROM ${MyDbNameClass.TABLE_NAME}")
    }

    fun closeDb(){
        myDbHelper.close()
    }
}