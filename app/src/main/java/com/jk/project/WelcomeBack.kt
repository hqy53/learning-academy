package com.jk.project

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.jk.project.databinding.ActivityWelcomeBackBinding

class WelcomeBack : AppCompatActivity() {

    lateinit var binding: ActivityWelcomeBackBinding
    val TAG = this@WelcomeBack.toString()
    lateinit var sharedPrefs : SharedPreferences
    lateinit var datasource: MyDatasource

    var numOfCourse = 0
    var courseCompleted : Int = 0
    var courseRemaining : Int = 0
    var coursePercentage : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefs  = getSharedPreferences("datasource", MODE_PRIVATE)

    }

    override fun onStart() {
        super.onStart()

        //Hardcode values for testing, remove later
//        datasource.classArrayList[1].isComplete = true
//        datasource.classArrayList[3].isComplete = true

        val gson = Gson()
        val json:String? = sharedPrefs.getString("datasource", "");
        datasource  = gson.fromJson(json, MyDatasource::class.java)

        numOfCourse = datasource.classArrayList.size

        val username = datasource.userName
        binding.tvUsername.setText("Welcome back, $username")

        courseCompleted = 0
        courseRemaining = 0
        checkCourseStatus()

        binding.tvCompletePercentage.setText("You've completed ${String.format("%.2f", coursePercentage)}% of the course!")
        binding.tvLessonCompleted.setText(("Lesson Completed: $courseCompleted"))
        binding.tvLessonRemaining.setText(("Lesson Remaining: $courseRemaining"))

        binding.btnContinue.setOnClickListener{
            Log.d(TAG, "Continue Button was pressed!")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnDelete.setOnClickListener{
            Log.d(TAG, "Resetting all data")
            for(i in 0..numOfCourse -1){
                datasource.classArrayList[i].isComplete = false
            }
            datasource.userName = null

            sharedPrefs.edit().remove("datasource").commit()

            val intent = Intent(this, NameScreenActivity::class.java)
            startActivity(intent)
        }

    }

    fun checkCourseStatus(){

        for(i in 0.. numOfCourse -1){
            if(datasource.classArrayList[i].isComplete == false){
                courseRemaining += 1
            } else {
                courseCompleted += 1
            }
        }
        if(courseCompleted != 0) {
            coursePercentage = (courseCompleted / numOfCourse.toDouble()) * 100
        }
    }
}