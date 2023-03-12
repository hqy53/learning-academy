package com.jk.project

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.jk.project.databinding.ActivityClassDetailsBinding
import java.io.Serializable

class ClassDetails : AppCompatActivity() {
    val TAG = this@ClassDetails.toString()
    lateinit var binding: ActivityClassDetailsBinding
    lateinit var datasource: MyDatasource
    lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityClassDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        sharedPrefs = getSharedPreferences("datasource", MODE_PRIVATE)
        val gson = Gson()
        val json: String? = sharedPrefs.getString("datasource", "");
        datasource = gson.fromJson(json, MyDatasource::class.java)


        if (intent != null) {

            val classId: Serializable? = intent.getSerializableExtra("EXTRA_CLASS_ID")
            if (classId != null) {

                // search teh datasource for a class with the provided id
                var currClass: Class? = null

                for (lesson in datasource.classArrayList) {
                    if (lesson.id == classId) {
                        currClass = lesson
                        break
                    }
                }

                // output either the class details or error message
                if (currClass == null) {
                    Log.d(
                        TAG,
                        "onCreate: Could not find a matching CLASS with ID ${classId}"
                    )
                } else {
                    binding.title.setText("${currClass.id}. ${currClass.title}")
                    binding.length.setText("${currClass.detail}")
                    binding.description.setText("${currClass.description}")
                    if (currClass.notes != null) {
                        binding.notes.setText("${currClass.notes}")
                    }
                    if (currClass.isComplete) {
                        binding.btnComplete.setText("COMPLETED")
                        binding.btnComplete.setBackgroundColor(Color.parseColor("#00008B"))
                    }
                    binding.btnVideo.setOnClickListener {
                        val i = Intent(Intent.ACTION_VIEW, Uri.parse(currClass.videoLink))
                        startActivity(i)
                    }
                }
            } else {
                Log.d(TAG, "onCreate: Could not find the EXTRA_CLASS_ID element in the intent")
            }

            binding.btnNotes.setOnClickListener {
                // get the value in the notes edit text
                val notes = binding.notes.text.toString()
                Log.d(TAG, "onCreate: Notes you enter is: $notes")
                // validation
                if (notes.isNullOrBlank()) {
                    binding.notesMessage.setText("Your notes can't be blank!")
                } else {
                    // save the value to the singleton's notes property
                    if (classId == 1) {
                        datasource.classArrayList[0].notes = notes
                    } else if (classId == 2) {
                        datasource.classArrayList[1].notes = notes
                    } else if (classId == 3) {
                        datasource.classArrayList[2].notes = notes
                    } else {
                        datasource.classArrayList[3].notes = notes
                    }
                    binding.notesMessage.setText("Your notes are saved!")

                    val prefsEditor: SharedPreferences.Editor = sharedPrefs.edit()
                    val json = gson.toJson(datasource)

                    prefsEditor.putString("datasource", json)
                    prefsEditor.commit()
                }
            }

            binding.btnComplete.setOnClickListener {
                if (classId == 1) {
                    datasource.classArrayList[0].isComplete = true
                } else if (classId == 2) {
                    datasource.classArrayList[1].isComplete = true
                } else if (classId == 3) {
                    datasource.classArrayList[2].isComplete = true
                } else {
                    datasource.classArrayList[3].isComplete = true
                }

                val prefsEditor: SharedPreferences.Editor = sharedPrefs.edit()
                val json = gson.toJson(datasource)

                prefsEditor.putString("datasource", json)
                prefsEditor.commit()
                finish()
            }
        }
    }
}