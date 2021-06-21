package com.example.exmp1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import com.example.exmp1.db.BdActivity
import com.example.vicky.multilanguageexample.MyContextWrapper
import com.example.vicky.multilanguageexample.MyPreference

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDefaultNightMode(MODE_NIGHT_NO);
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
            val intent = Intent(this, ArchiveActivity::class.java)
            startActivity(intent)
        }

        val buttonAnalysis: Button = findViewById(R.id.button_analysis)
        buttonAnalysis.setOnClickListener{
            val intent = Intent(this, AnalysisActivity::class.java)
            startActivity(intent)
        }

        val buttonInfo: Button = findViewById(R.id.button_info)
        buttonInfo.setOnClickListener{
            val intent = Intent(this, CreateFIleActivity::class.java)
            startActivity(intent)
        }

        val buttonSetting: Button = findViewById(R.id.button_setting)
        buttonSetting.setOnClickListener{
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        val myPreference = MyPreference(newBase!!)
        val lang: String = myPreference.getLoginCount()
        super.attachBaseContext(MyContextWrapper.wrap(newBase,lang))
    }
}