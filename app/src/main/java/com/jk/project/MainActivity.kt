package com.jk.project

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson
import com.jk.project.databinding.ActivityMainBinding
import com.jk.project.R


class MainActivity : AppCompatActivity() {
    val TAG = this@MainActivity.toString()

    lateinit var binding: ActivityMainBinding
    lateinit var classArrayList: ArrayList<Class>
    lateinit var classAdapter: ClassAdapter
    lateinit var sharedPrefs : SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs  = getSharedPreferences("datasource", MODE_PRIVATE)

    }

    override fun onStart() {
        super.onStart()

        val gson = Gson()
        val json:String? = sharedPrefs.getString("datasource", "");
        var datasource:MyDatasource  = gson.fromJson(json, MyDatasource::class.java)

//        Log.d(TAG, "onStart: Number of classes : ${datasource.classArrayList.count()}")

        // initialize the classList on the MainActivity with the classes from the MyDatasource
        classArrayList = datasource.classArrayList

//        Log.d(TAG, "onStart: classArrayList : ${classArrayList}")

        classAdapter = ClassAdapter(this, classArrayList)

        binding.lvClasses.adapter = classAdapter

        binding.lvClasses.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

                val selectedClass = classArrayList[position];
                Log.d(TAG, "onStart: user clicked on ${selectedClass.title}")

                // if Force Sequential Progression is on
                if (binding.switchSeqProgression.isChecked) {
                    if ((position > 0) && classArrayList[position - 1].isComplete) {

                        val intent = Intent(this, ClassDetails::class.java)
                        intent.putExtra("EXTRA_CLASS_ID", selectedClass.id)
                        startActivity(intent)

                    }
                    if(position == 0){
                        val intent = Intent(this, ClassDetails::class.java)
                        intent.putExtra("EXTRA_CLASS_ID", selectedClass.id)
                        startActivity(intent)
                    }
                } else {
                    val intent = Intent(this, ClassDetails::class.java)
                    intent.putExtra("EXTRA_CLASS_ID", selectedClass.id)
                    startActivity(intent)
                }
            }
    }
}







