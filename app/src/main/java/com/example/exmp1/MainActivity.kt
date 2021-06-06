package com.example.exmp1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.exmp1.db.BdActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStorage: Button = findViewById(R.id.button_storage)
        buttonStorage.setOnClickListener{
            val intent = Intent(this, BdActivity::class.java)
            startActivity(intent)
        }

        val buttonReport: Button = findViewById(R.id.button_report)
        buttonReport.setOnClickListener{
            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }

        val buttonArchive: Button = findViewById(R.id.button_archive)
        buttonArchive.setOnClickListener{
            val intent = Intent(this, BdActivity::class.java)
            startActivity(intent)
        }

        val buttonAnalysis: Button = findViewById(R.id.button_analysis)
        buttonAnalysis.setOnClickListener{
            val intent = Intent(this, BdActivity::class.java)
            startActivity(intent)
        }

        val buttonInfo: Button = findViewById(R.id.button_info)
        buttonInfo.setOnClickListener{
            val intent = Intent(this, BdActivity::class.java)
            startActivity(intent)
        }

        val buttonSetting: Button = findViewById(R.id.button_setting)
        buttonSetting.setOnClickListener{
            val intent = Intent(this, BdActivity::class.java)
            startActivity(intent)
        }
    }
}