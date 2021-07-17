package com.example.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var fossVsFossID = HashMap<String, Int>()
        fossVsFossID.put("Java", 10)
        fossVsFossID.put("Cpp", 57)
        fossVsFossID.put("Python", 26)
        fossVsFossID.put("RDBMS", 92)


        var languageVsLanguageID = HashMap<String, Int>()
        languageVsLanguageID.put("English", 22)
        languageVsLanguageID.put("Hindi", 6)
        languageVsLanguageID.put("Gujarati", 5)
        languageVsLanguageID.put("Tamil", 18)
        languageVsLanguageID.put("Marathi", 12)
        languageVsLanguageID.put("Kannada", 7)

        var fossVsLanguage = HashMap<String, ArrayList<String>>()
        var availableLanguagesForFoss: ArrayList<String>

        availableLanguagesForFoss = arrayListOf("English", "Gujarati","Hindi",  "Kannada")
        fossVsLanguage.put("Java", availableLanguagesForFoss)

        availableLanguagesForFoss = arrayListOf("English", "Gujarati", "Hindi","Kannada", "Marathi", "Tamil")
        fossVsLanguage.put("Cpp", availableLanguagesForFoss)

        availableLanguagesForFoss = arrayListOf("English", "Hindi")
        fossVsLanguage.put("Python", availableLanguagesForFoss)

        availableLanguagesForFoss = arrayListOf("English")
        fossVsLanguage.put("RDBMS", availableLanguagesForFoss)

        var fossSpinner = findViewById<Spinner>(R.id.Spinner1)
        var languageSpinner = findViewById<Spinner>(R.id.Spinner2)

        var fossOptions = fossVsFossID.keys.toList()
        var languageOptions = languageVsLanguageID.keys.toMutableList()

        var selectedFoss = "none"
        var selectedLanguage = "none"

        var fossAdapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_list_item_activated_1, fossOptions)
        fossSpinner.adapter = fossAdapter

        var languageAdapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_list_item_activated_1, languageOptions)
        languageSpinner.adapter = languageAdapter

        fossSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long) {

                languageOptions.clear()

                selectedFoss = fossOptions.get(position)

                languageOptions.addAll(ArrayList<String>(fossVsLanguage.get(selectedFoss)!!))

                languageAdapter.notifyDataSetChanged()
            }
        }

        languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, position: Int, id: Long) {

                selectedLanguage = languageOptions.get(position)
            }
        }

        val searchButton = findViewById<Button>(R.id.searchButton)
        searchButton.setOnClickListener {
            if (selectedFoss == "none" || selectedLanguage == "none") {

                Toast.makeText(this, "Please select FOSS and Language both", Toast.LENGTH_SHORT).show()

            } else {

                var fossID = fossVsFossID.get(selectedFoss)
                var languageID = languageVsLanguageID.get(selectedLanguage)

                var searchIntent = Intent(this, SecondActivity::class.java).apply {
                    putExtra("fossID", "" + fossID)
                    putExtra("languageID", "" + languageID)
                }

                startActivity(searchIntent)
            }
        }

    }
}