package com.example.exmp1.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import java.math.RoundingMode

class MyOrderDbManager(private val context: Context) {

    private val myDbHelper = MyOrderDbHelper(context)
    private var db: SQLiteDatabase? = null

    fun openDb(){
        db = myDbHelper.writableDatabase
    }
    fun insertToDb( product: String, groupName: String, quantity: Int, units: String, price: Float, extraCharge: Int, date: Long){
        val values = ContentValues().apply {
            put(MyDbOrderClass.COLUMN_NAME_PRODUCT, product)
            put(MyDbOrderClass.COLUMN_NAME_GROUP, groupName)
            put(MyDbOrderClass.COLUMN_NAME_QUANTITY, quantity)
            put(MyDbOrderClass.COLUMN_NAME_UNITS, units)
            put(MyDbOrderClass.COLUMN_NAME_PRICE, price)
            put(MyDbOrderClass.COLUMN_NAME_EXTRA_CHARGE, extraCharge)
            put(MyDbOrderClass.COLUMN_SELLING_PRICE, (price+(price*(extraCharge.toFloat() / 100.0f))).toBigDecimal().setScale(2,
                RoundingMode.HALF_EVEN).toFloat())
            put(MyDbOrderClass.COLUMN_SELLING_DATE, date)

        }
        val result = db?.insert(MyDbOrderClass.TABLE_NAME,null,values)

        if(result!!.toInt() == -1){
            Toast.makeText(context, "Failed to Add", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Successfully Added!", Toast.LENGTH_SHORT).show()
        }
    }

    fun readData() : ArrayList<DbOrderData>{
        val dataList = ArrayList<DbOrderData>()
        val cursor = db?.query(MyDbOrderClass.TABLE_NAME,null,null,null,null,null,null)


        while(cursor?.moveToNext()!!){
            val dataId = cursor.getString(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_ID))
            val dataProduct = cursor.getString(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_PRODUCT))
            val dataGroup = cursor.getString(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_GROUP))
            val dataQuantity = cursor.getInt(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_QUANTITY))
            val dataUnits = cursor.getString(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_UNITS))
            val dataPrice = cursor.getString(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_PRICE))
            val dataCharge = cursor.getString(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_EXTRA_CHARGE))
            val dataSellingPrice = cursor.getFloat(cursor.getColumnIndex(MyDbOrderClass.COLUMN_SELLING_PRICE))
            val dataSellingDate = cursor.getLong(cursor.getColumnIndex(MyDbOrderClass.COLUMN_SELLING_DATE))
            dataList.add(DbOrderData(dataId,dataProduct,dataGroup,dataQuantity,dataUnits,dataPrice,dataCharge,dataSellingPrice,dataSellingDate))
        }
        cursor.close()
        return dataList
    }

    fun sortData() : ArrayList<DbOrderData>{
        val dataList = ArrayList<DbOrderData>()
        val cursor = db?.query(MyDbOrderClass.TABLE_NAME,null,null,null,null,null,"${MyDbOrderClass.COLUMN_SELLING_DATE} ASC" )

        while(cursor?.moveToNext()!!){
            val dataId = cursor.getString(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_ID))
            val dataProduct = cursor.getString(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_PRODUCT))
            val dataGroup = cursor.getString(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_GROUP))
            val dataQuantity = cursor.getInt(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_QUANTITY))
            val dataUnits = cursor.getString(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_UNITS))
            val dataPrice = cursor.getString(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_PRICE))
            val dataCharge = cursor.getString(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_EXTRA_CHARGE))
            val dataSellingPrice = cursor.getFloat(cursor.getColumnIndex(MyDbOrderClass.COLUMN_SELLING_PRICE))
            val dataSellingDate = cursor.getLong(cursor.getColumnIndex(MyDbOrderClass.COLUMN_SELLING_DATE))
            dataList.add(DbOrderData(dataId,dataProduct,dataGroup,dataQuantity,dataUnits,dataPrice,dataCharge,dataSellingPrice,dataSellingDate))
        }
        cursor.close()
        return dataList
    }

    fun sortDataDesc() : ArrayList<DbOrderData>{
        val dataList = ArrayList<DbOrderData>()
        val cursor = db?.query(MyDbOrderClass.TABLE_NAME,null,null,null,null,null,"${MyDbOrderClass.COLUMN_SELLING_DATE} DESC" )

        while(cursor?.moveToNext()!!){
            val dataId = cursor.getString(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_ID))
            val dataProduct = cursor.getString(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_PRODUCT))
            val dataGroup = cursor.getString(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_GROUP))
            val dataQuantity = cursor.getInt(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_QUANTITY))
            val dataUnits = cursor.getString(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_UNITS))
            val dataPrice = cursor.getString(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_PRICE))
            val dataCharge = cursor.getString(cursor.getColumnIndex(MyDbOrderClass.COLUMN_NAME_EXTRA_CHARGE))
            val dataSellingPrice = cursor.getFloat(cursor.getColumnIndex(MyDbOrderClass.COLUMN_SELLING_PRICE))
            val dataSellingDate = cursor.getLong(cursor.getColumnIndex(MyDbOrderClass.COLUMN_SELLING_DATE))
            dataList.add(DbOrderData(dataId,dataProduct,dataGroup,dataQuantity,dataUnits,dataPrice,dataCharge,dataSellingPrice,dataSellingDate))
        }
        cursor.close()
        return dataList
    }

    fun updateData(row_id: String, product: String, groupName: String, quantity: Int, units: String, price: Float, extraCharge: Int, date: Long) {
        val values = ContentValues().apply {
            put(MyDbOrderClass.COLUMN_NAME_PRODUCT, product)
            put(MyDbOrderClass.COLUMN_NAME_GROUP, groupName)
            put(MyDbOrderClass.COLUMN_NAME_QUANTITY, quantity)
            put(MyDbOrderClass.COLUMN_NAME_UNITS, units)
            put(MyDbOrderClass.COLUMN_NAME_PRICE, price)
            put(MyDbOrderClass.COLUMN_NAME_EXTRA_CHARGE, extraCharge)
            put(MyDbOrderClass.COLUMN_SELLING_PRICE, (price+(price*(extraCharge.toFloat() / 100.0f))).toBigDecimal().setScale(2,
                RoundingMode.HALF_EVEN).toFloat())
            put(MyDbOrderClass.COLUMN_SELLING_DATE, date)

        }
        val result = db?.update(MyDbOrderClass.TABLE_NAME, values, "id=?", arrayOf(row_id))

        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteOneRow(row_id :String) {
        val result = db?.delete(MyDbOrderClass.TABLE_NAME, "id=?", arrayOf(row_id))
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteAllData() {
        db?.execSQL("DELETE FROM ${MyDbOrderClass.TABLE_NAME}")
    }

    fun closeDb(){
        myDbHelper.close()
    }
}