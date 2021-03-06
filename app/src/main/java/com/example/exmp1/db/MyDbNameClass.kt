package com.example.exmp1.db

import android.provider.BaseColumns

object MyDbNameClass : BaseColumns {
    const val TABLE_NAME = "StorageTable"
    const val COLUMN_NAME_ID = "id"
    const val COLUMN_NAME_PRODUCT = "product"
    const val COLUMN_NAME_GROUP = "groupName"
    const val COLUMN_NAME_QUANTITY = "quantity"
    const val COLUMN_NAME_UNITS = "units"
    const val COLUMN_NAME_PRICE = "price"
    const val COLUMN_NAME_EXTRA_CHARGE = "extraCharge"
    const val COLUMN_SELLING_PRICE = "sellingPrice"
    const val COLUMN_TOTAL_BASIC_PRICE = "totalBasicPrice"
    const val COLUMN_TOTAL_SELLING_PRICE = "totalSellingPrice"


    const val DATABASE_VERSION = 4
    const val DATABASE_NAME = "StorageDB.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "$COLUMN_NAME_ID INTEGER PRIMARY KEY,"+
            "$COLUMN_NAME_PRODUCT TEXT,"+
            "$COLUMN_NAME_GROUP TEXT,"+
            "$COLUMN_NAME_QUANTITY INTEGER,"+
            "$COLUMN_NAME_UNITS TEXT,"+
            "$COLUMN_NAME_PRICE REAL,"+
            "$COLUMN_NAME_EXTRA_CHARGE INTEGER,"+
            "$COLUMN_SELLING_PRICE REAL,"+
            "$COLUMN_TOTAL_BASIC_PRICE REAL,"+
            "$COLUMN_TOTAL_SELLING_PRICE REAL)"


    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

}