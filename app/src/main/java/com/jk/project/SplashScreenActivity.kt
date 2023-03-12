package com.jk.project

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.gson.Gson

class SplashScreenActivity : AppCompatActivity() {

//    val datasource:MyDatasource = MyDatasource.getInstance()
    lateinit var sharedPrefs : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        sharedPrefs  = getSharedPreferences("datasource", MODE_PRIVATE)

        Handler().postDelayed({
            val gson = Gson()
            val json:String? = sharedPrefs.getString("datasource", "");
            val datasource:MyDatasource?  = gson.fromJson(json, MyDatasource::class.java)

            if(datasource == null){
                val intent = Intent(this@SplashScreenActivity, NameScreenActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this@SplashScreenActivity, WelcomeBack::class.java)
                startActivity(intent)
                finish()
            }

        },3000)
    }
}