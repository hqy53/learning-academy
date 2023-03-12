package com.jk.project

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.gson.Gson
import com.jk.project.databinding.ActivityMainBinding
import com.jk.project.databinding.ActivityNameScreenBinding


class NameScreenActivity : AppCompatActivity() {

    // binding variables
    lateinit var binding: ActivityNameScreenBinding
    lateinit var sharedPrefs: SharedPreferences
    val TAG = this@NameScreenActivity.toString()

    val datasource: MyDatasource = MyDatasource.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPrefs = getSharedPreferences("datasource", MODE_PRIVATE)
        // initialize the binding variable
        binding = ActivityNameScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()

        binding.btnContinue.setOnClickListener {
            Log.d(TAG, "Continue Button was pressed!")

            if (binding.etUsername.text.toString().isNullOrBlank()) {
                binding.tvErrorMsg.setText("Please enter your name")
            } else {
                datasource.userName = binding.etUsername.text.toString()
                binding.tvErrorMsg.setText("")
                val prefsEditor: SharedPreferences.Editor = sharedPrefs.edit()
                val gson = Gson()
                val editjson = gson.toJson(datasource)

                prefsEditor.putString("datasource", editjson)
                prefsEditor.commit()

                // Intent object
                val intent = Intent(this, MainActivity::class.java)
                // begin navigation
                startActivity(intent)
            }

        }
    }
}